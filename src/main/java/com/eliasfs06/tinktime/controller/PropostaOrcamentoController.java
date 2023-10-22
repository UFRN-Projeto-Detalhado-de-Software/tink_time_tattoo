package com.eliasfs06.tinktime.controller;

import com.eliasfs06.tinktime.model.Artist;
import com.eliasfs06.tinktime.model.PropostaOrcamento;
import com.eliasfs06.tinktime.model.User;
import com.eliasfs06.tinktime.service.PropostaOrcamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/proposta-orcamento")
public class PropostaOrcamentoController {

    @Autowired
    private PropostaOrcamentoService propostaOrcamentoService;


    @GetMapping("/list")
    public ModelAndView listOrcamentos(){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PropostaOrcamento> propostasList = propostaOrcamentoService.listPropostasByUserID(user.getId());
        if (propostasList == null)
            propostasList =new ArrayList<>();

        modelAndView.addObject("propostasList", propostasList);
        modelAndView.setViewName("propostaOrcamento/list");
        return modelAndView;
    }

}
