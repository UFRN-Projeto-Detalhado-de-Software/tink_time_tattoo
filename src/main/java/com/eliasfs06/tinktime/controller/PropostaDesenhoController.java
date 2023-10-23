package com.eliasfs06.tinktime.controller;

import ch.qos.logback.core.model.Model;
import com.eliasfs06.tinktime.exceptionsHandler.BusinessException;
import com.eliasfs06.tinktime.model.Client;
import com.eliasfs06.tinktime.model.PropostaDesenho;
import com.eliasfs06.tinktime.model.PropostaOrcamento;
import com.eliasfs06.tinktime.model.User;
import com.eliasfs06.tinktime.model.dto.PropostaDesenhoDTO;
import com.eliasfs06.tinktime.model.dto.PropostaOrcamentoDTO;
import com.eliasfs06.tinktime.model.dto.PropostaTatuagemDTO;
import com.eliasfs06.tinktime.model.enums.StatusAprovacao;
import com.eliasfs06.tinktime.service.ClientService;
import com.eliasfs06.tinktime.service.PropostaDesenhoService;
import com.eliasfs06.tinktime.service.PropostaOrcamentoService;
import com.eliasfs06.tinktime.service.PropostaTatuagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/proposta-desenho")
public class PropostaDesenhoController {

    @Autowired
    private PropostaDesenhoService propostaDesenhoService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private PropostaTatuagemService propostaTatuagemService;

    @Autowired
    private PropostaOrcamentoService propostaOrcamentoService;

    @GetMapping("/list")
    public ModelAndView listDesenhos() {
        ModelAndView modelAndView = new ModelAndView();
        User cliente = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PropostaDesenho> propostaDesenhos = propostaDesenhoService.listPropostasByClienteID(cliente.getId());
        modelAndView.addObject("propostaDesenhos", propostaDesenhos);
        modelAndView.setViewName("propostaDesenho/form");
        return modelAndView;
    }

    @GetMapping("/form")
    public ModelAndView form() {
        ModelAndView modelAndView = new ModelAndView();
        List<Client> clientes = clientService.listClients();
        modelAndView.addObject("clientes", clientes);
        modelAndView.addObject("newDesenho", new PropostaDesenho());
        modelAndView.setViewName("propostaDesenho/form");
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@RequestParam(value="propostaOrcamento.propostaTatuagem", required = true) String tatuagem,
                         @RequestParam(value="propostaOrcamento", required = true) String orcamento,
                         PropostaDesenho propostaDesenho,
                         Model model) throws BusinessException {
        try {
            PropostaDesenhoDTO propostaDesenhoDTO = new PropostaDesenhoDTO();

            PropostaTatuagemDTO propostaTatuagem = propostaTatuagemService.findById(Long.parseLong(tatuagem));
            PropostaOrcamentoDTO propostaOrcamento = propostaOrcamentoService.findById(Long.parseLong(orcamento));

            propostaOrcamento.setPropostaTatuagem(propostaTatuagem);
            propostaDesenhoDTO.setPropostaOrcamento(propostaOrcamento);
            propostaDesenhoDTO.setDesenho(propostaDesenho.getDesenho());
            propostaDesenhoDTO.setStatusAprovacao(StatusAprovacao.PENDENTE.name());

            propostaDesenhoService.create(propostaDesenhoDTO);

        } catch (BusinessException e) {
            return "redirect:/index";
        }
        return "redirect:/index";
    }

}
