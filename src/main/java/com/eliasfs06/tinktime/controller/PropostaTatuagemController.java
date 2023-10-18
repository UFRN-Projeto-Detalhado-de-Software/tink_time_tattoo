package com.eliasfs06.tinktime.controller;

import com.eliasfs06.tinktime.model.dto.PropostaTatuagemDTO;
import com.eliasfs06.tinktime.service.PropostaTatuagemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proposta-tatuagem")
public class PropostaTatuagemController {

    private PropostaTatuagemService propostaTatuagemService;

    @RequestMapping("/form")
    public String form() {
        return "propostaTatuagem/form";
    }

//    @PostMapping("/create")
//    public String create(@ModelAttribute("propostaTatuagem") PropostaTatuagemDTO propostaTatuagemDTO) {
//
//    }
}
