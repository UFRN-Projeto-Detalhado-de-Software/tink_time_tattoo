package com.eliasfs06.tinktime.controller;

import ch.qos.logback.core.model.Model;
import com.eliasfs06.tinktime.exceptionsHandler.BusinessException;
import com.eliasfs06.tinktime.model.*;
import com.eliasfs06.tinktime.model.dto.PropostaDesenhoDTO;
import com.eliasfs06.tinktime.model.dto.PropostaOrcamentoDTO;
import com.eliasfs06.tinktime.model.dto.PropostaTatuagemDTO;
import com.eliasfs06.tinktime.model.enums.StatusAprovacao;
import com.eliasfs06.tinktime.service.ClientService;
import com.eliasfs06.tinktime.service.PropostaDesenhoService;
import com.eliasfs06.tinktime.service.PropostaOrcamentoService;
import com.eliasfs06.tinktime.service.PropostaTatuagemService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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

        List<String> imagensBase64 = new ArrayList<>();
        for (PropostaDesenho propostaDesenho : propostaDesenhos) {
            byte[] imagemByte = propostaDesenho.getDesenho();
            String imagemBase64 = java.util.Base64.getEncoder().encodeToString(imagemByte);
            imagensBase64.add(imagemBase64);
        }

        modelAndView.addObject("propostaDesenhos", propostaDesenhos);
        modelAndView.addObject("imagensBase64", imagensBase64);
        modelAndView.setViewName("propostaDesenho/list");
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

            PropostaTatuagem proposta = propostaDesenho.getPropostaOrcamento().getPropostaTatuagem();
            proposta.setNumeroSessoes(propostaDesenho.getNumeroSessoes());
            propostaTatuagemService.save(proposta);

        } catch (BusinessException e) {
            return "redirect:/index";
        }
        return "redirect:/index";
    }

    @GetMapping("/recusar/{id}")
    public String recusar(@PathVariable("id") Long id){
        try {
            propostaDesenhoService.recusar(id);
            return "redirect:/proposta-desenho/list";
        } catch (BusinessException e) {
            return "redirect:/index";
        }
    }

    @GetMapping("/aprovar/{id}")
    public String aprovar(@PathVariable("id") Long id){
        try {
            propostaDesenhoService.aprovar(id);
            return "redirect:/proposta-desenho/list";
        } catch (BusinessException e) {
            return "redirect:/index";
        }
    }

}
