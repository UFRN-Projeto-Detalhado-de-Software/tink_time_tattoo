package com.eliasfs06.tinktime.controller;

import ch.qos.logback.core.model.Model;
import com.eliasfs06.tinktime.exceptionsHandler.BusinessException;
import com.eliasfs06.tinktime.model.Artist;
import com.eliasfs06.tinktime.model.PropostaTatuagem;
import com.eliasfs06.tinktime.model.User;
import com.eliasfs06.tinktime.model.dto.PropostaTatuagemDTO;
import com.eliasfs06.tinktime.model.dto.UserDTO;
import com.eliasfs06.tinktime.service.ArtistService;
import com.eliasfs06.tinktime.service.PropostaTatuagemService;
import com.eliasfs06.tinktime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/proposta-tatuagem")
public class PropostaTatuagemController {

    @Autowired
    private PropostaTatuagemService propostaTatuagemService;

    @Autowired
    private ArtistService artistService;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ModelAndView listTatuagens(){
        ModelAndView modelAndView = new ModelAndView();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<PropostaTatuagem> propostasList = propostaTatuagemService.listPropostasByTatuadorID(user.getId());
        modelAndView.addObject("propostasList", propostasList);
        modelAndView.setViewName("propostaTatuagem/list");
        return modelAndView;
    }


    @GetMapping("/form")
    public ModelAndView form() {
        ModelAndView modelAndView = new ModelAndView();
        List<Artist> artistas = artistService.listActiveArtists();
        modelAndView.addObject("newTatuagem", new PropostaTatuagemDTO());
        modelAndView.addObject("artistas", artistas);
        modelAndView.setViewName("propostaTatuagem/form");
        return modelAndView;
    }

    @PostMapping("/create")
    public String create(@RequestParam(value="descricao", required = true) String Descricao, @RequestParam(value="tatuador", required = true) String Tatuador,
                         Model model) throws BusinessException {
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            User artist = userService.findByID(Long.parseLong(Tatuador));
            UserDTO userdto = new UserDTO(user);
            UserDTO artistdto = new UserDTO(artist);
            PropostaTatuagemDTO propostaTatuagemDTO = new PropostaTatuagemDTO();
            propostaTatuagemDTO.setCliente(userdto);
            propostaTatuagemDTO.setTatuador(artistdto);
            propostaTatuagemDTO.setDescricao(Descricao);
            propostaTatuagemService.create(propostaTatuagemDTO);
        } catch (BusinessException e) {
            return "redirect:/index";
        }
        return "redirect:/index";
    }
}
