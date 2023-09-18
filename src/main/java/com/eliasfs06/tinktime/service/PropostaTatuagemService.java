package com.eliasfs06.tinktime.service;

import com.eliasfs06.tinktime.model.PropostaTatuagem;
import com.eliasfs06.tinktime.model.User;
import com.eliasfs06.tinktime.model.dto.PropostaTatuagemDTO;
import com.eliasfs06.tinktime.model.exceptionsHandler.BusinessException;
import com.eliasfs06.tinktime.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class PropostaTatuagemService extends GenericService<PropostaTatuagem> {

    @Autowired
    private GenericRepository<PropostaTatuagem> propostaTatuagemRepository;

    @Autowired
    private GenericRepository<User> userRepository;

    public PropostaTatuagemService(GenericRepository<PropostaTatuagem> repository) {
        super(repository);
    }

    public PropostaTatuagemDTO findById(Long id){
        return new PropostaTatuagemDTO(Objects.requireNonNull(propostaTatuagemRepository.findById(id).orElse(null)));
    }

    @Transactional
    public void create(PropostaTatuagemDTO propostaTatuagemDTO) throws BusinessException {
        PropostaTatuagem propostaTatuagem = new PropostaTatuagem();
        User cliente = userRepository.findById(propostaTatuagemDTO.getCliente().getId()).orElse(null);
        User tatuador = userRepository.findById(propostaTatuagemDTO.getTatuador().getId()).orElse(null);

        if (cliente == null || tatuador == null) {
            throw new BusinessException("Cliente ou tatuador não encontrado");
        }

        propostaTatuagem.setCliente(cliente);
        propostaTatuagem.setTatuador(tatuador);
        propostaTatuagem.setDescricao(propostaTatuagemDTO.getDescricao());
        create(propostaTatuagem);
    }

}
