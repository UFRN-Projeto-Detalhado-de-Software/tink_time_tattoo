package com.eliasfs06.tinktime.controller;

import com.eliasfs06.tinktime.exceptionsHandler.BusinessException;
import com.eliasfs06.tinktime.model.*;
import com.eliasfs06.tinktime.model.dto.ArtistDTO;
import com.eliasfs06.tinktime.model.dto.HorariosTatuagem;
import com.eliasfs06.tinktime.model.dto.PropostaTatuagemDTO;
import com.eliasfs06.tinktime.model.dto.UserDTO;
import com.eliasfs06.tinktime.service.AgendaService;
import com.eliasfs06.tinktime.service.ArtistService;
import com.eliasfs06.tinktime.service.PropostaTatuagemService;
import com.eliasfs06.tinktime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @Autowired
    private AgendaService agendaService;

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

    @GetMapping("/getPropostasByCliente")
    @ResponseBody
    public List<PropostaTatuagem> getPropostasByCliente(@RequestParam Long clienteId) {
        return propostaTatuagemService.listPropostasByClienteID(clienteId);
    }

    @GetMapping("/agendar-tatuagem/{id}")
    public String agendarTatuagem(@PathVariable Long id, Model model){
        PropostaTatuagem propostaTatuagem = propostaTatuagemService.get(id);
        ArtistDTO artistdto = artistService.findByUser(propostaTatuagem.getTatuador());
        Artist artist = artistdto.toArtist();
        Agenda agenda = agendaService.findByArtist(artist);

        List<HorariosTatuagem> horariosDisponveis = agendaService.sugerirHorarios(artist, propostaTatuagem.getNumeroSessoes());
        List<HorariosTatuagem> horariosDisponiveisFormatados = agendaService.formatarHorariosDisponiveis(horariosDisponveis, propostaTatuagem.getNumeroSessoes());

        model.addAttribute("horarios", horariosDisponiveisFormatados);
        return "/propostaTatuagem/agendar-tatuagem";
    }
}
