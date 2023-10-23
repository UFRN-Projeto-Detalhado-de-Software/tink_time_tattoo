package com.eliasfs06.tinktime.service;

import com.eliasfs06.tinktime.exceptionsHandler.BusinessException;
import com.eliasfs06.tinktime.model.PropostaOrcamento;
import com.eliasfs06.tinktime.model.PropostaStatus;
import com.eliasfs06.tinktime.model.PropostaTatuagem;
import com.eliasfs06.tinktime.model.User;
import com.eliasfs06.tinktime.model.dto.PropostaOrcamentoDTO;
import com.eliasfs06.tinktime.repository.GenericRepository;
import com.eliasfs06.tinktime.repository.PropostaOrcamentoRepository;
import com.eliasfs06.tinktime.repository.PropostaTatuagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PropostaOrcamentoService extends GenericService<PropostaOrcamento> {

    @Autowired
    private PropostaOrcamentoRepository propostaOrcamentoRepository;

    @Autowired
    private GenericRepository<User> userRepository;

    @Autowired
    private PropostaTatuagemRepository propostaTatuagemRepository;

    public PropostaOrcamentoService(GenericRepository<PropostaOrcamento> repository) {
        super(repository);
    }

    @Transactional
    public PropostaOrcamento create(PropostaOrcamentoDTO propostaOrcamentoDTO) throws BusinessException {
        PropostaOrcamento propostaOrcamento = new PropostaOrcamento();

        if (propostaOrcamentoDTO.getOrcamento() == null)
            throw new BusinessException("Orçamento não pode ser nulo");

        if (propostaOrcamentoDTO.getPropostaTatuagem().getCliente() == null || propostaOrcamentoDTO.getPropostaTatuagem().getTatuador() == null || propostaOrcamentoDTO.getPropostaTatuagem().getDescricao() == null)
            throw new BusinessException("Proposta de tatuagem do orçamento não está bem estruturada");

        User cliente = userRepository.findById(propostaOrcamentoDTO.getPropostaTatuagem().getCliente().getId()).orElse(null);
        User tatuador = userRepository.findById(propostaOrcamentoDTO.getPropostaTatuagem().getTatuador().getId()).orElse(null);
        String descricao = propostaOrcamentoDTO.getPropostaTatuagem().getDescricao();

        if (cliente == null || tatuador == null) {
            throw new BusinessException("Cliente ou tatuador inválidos");
        }

        PropostaTatuagem propostaTatuagem = propostaTatuagemRepository.findPropostaTatuagemByClienteAndTatuadorAndDescricao(cliente, tatuador, descricao);

        if (propostaTatuagem == null) {
            throw new BusinessException("Proposta de tatuagem inválida");
        }

        propostaOrcamento.setPropostaTatuagem(propostaTatuagem);
        propostaOrcamento.setOrcamento(propostaOrcamentoDTO.getOrcamento());
        propostaOrcamento.setAprovado(PropostaStatus.AVALIACAO);

        propostaOrcamentoRepository.save(propostaOrcamento);

        return propostaOrcamento;
    }

    public PropostaOrcamento aprovar(Long id) throws BusinessException {
        PropostaOrcamento propostaOrcamento = propostaOrcamentoRepository.findById(id).orElse(null);
        if (propostaOrcamento == null) {
            throw new BusinessException("Proposta de orçamento inválida");
        }

        propostaOrcamento.setAprovado(PropostaStatus.APROVADO);

        propostaOrcamentoRepository.save(propostaOrcamento);

        return propostaOrcamento;
    }

    public PropostaOrcamento recusar(Long id) throws BusinessException {
        PropostaOrcamento propostaOrcamento = propostaOrcamentoRepository.findById(id).orElse(null);
        if (propostaOrcamento == null) {
            throw new BusinessException("Proposta de orçamento inválida");
        }

        propostaOrcamento.setAprovado(PropostaStatus.RECUSADO);

        propostaOrcamentoRepository.save(propostaOrcamento);

        return propostaOrcamento;
    }

    public List<PropostaOrcamento> listPropostasByClienteID(Long id) {
        Optional<List<PropostaOrcamento>> propostaOrcamentoOptional = propostaOrcamentoRepository.findAllByClienteId(id);
        if (propostaOrcamentoOptional.isPresent()) {
            return propostaOrcamentoOptional.get();
        }
        return new ArrayList<>();
    }
}

