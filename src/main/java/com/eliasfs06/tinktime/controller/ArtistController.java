package com.eliasfs06.tinktime.controller;

import com.eliasfs06.tinktime.model.*;
import com.eliasfs06.tinktime.model.dto.ArtistDTO;
import com.eliasfs06.tinktime.model.dto.FormCadastroHorarios;
import com.eliasfs06.tinktime.repository.GenericRepository;
import com.eliasfs06.tinktime.service.AgendaService;
import com.eliasfs06.tinktime.service.ArtistService;
import com.eliasfs06.tinktime.service.DiaAgendaService;
import com.eliasfs06.tinktime.service.HorarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("artist")
public class ArtistController extends GenericController<Artist> {

    @Autowired
    private ArtistService artistService;

    @Autowired
    private AgendaService agendaService;

    @Autowired
    private DiaAgendaService diaAgendaService;

    @Autowired
    private HorarioService horarioService;

    public ArtistController(GenericRepository<Artist> repository) {
        super(repository);
    }

    @GetMapping("/profile")
    public String getProfile(Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ArtistDTO artist = artistService.findByUser(user);

        if(artist.getId() == null){
            artist.setUser(user);
        }

        model.addAttribute("artist", artist);
        model.addAttribute("allStyles", TattoStyle.getAllStyles());
        return "artist/profile";
    }

    @PostMapping("/profile")
    public String saveProfile(@ModelAttribute("artist") @Valid ArtistDTO artist, BindingResult br, Model model){

        if(br.hasErrors()){
            return "artist/profile";
        }

        artistService.save(artist.toArtist());
        model.addAttribute("artist", artist);
        model.addAttribute("allStyles", TattoStyle.getAllStyles());

        return "artist/profile";
    }

    @GetMapping("/list")
    public ModelAndView listArtists(){
        ModelAndView modelAndView = new ModelAndView();

        List<Artist> artistList = artistService.listActiveArtists();

        modelAndView.addObject("artistList", artistList);
        modelAndView.setViewName("artist/list");
        return modelAndView;
    }

    @GetMapping("/agenda")
    public String agenda(){
        return "/artist/agenda";
    }

    @GetMapping("/agenda-dia/{data}")
    public String agendaDia(@PathVariable String data, Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ArtistDTO artistDTO = artistService.findByUser(user);
        Artist artist = artistDTO.toArtist();
        Agenda agenda = agendaService.findByArtist(artist);
        DiaAgenda diaAgenda = diaAgendaService.findByDia(agenda, data);
        if(diaAgenda == null){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate dia = LocalDate.parse(data, formatter);
            diaAgenda =  agendaService.createDiaAgenda(agenda, dia);
        }
        model.addAttribute(diaAgenda);
        model.addAttribute(agenda);
        return "/fragments/agenda-dia :: agenda-dia";
    }

    @PostMapping("/salvar-horarios")
    public String salvarHorarios(@ModelAttribute FormCadastroHorarios formCadastroHorarios, Model model) {
        for (int i = 0; i < formCadastroHorarios.getHorariosId().size(); i++){
            Horario horario = horarioService.get(formCadastroHorarios.getHorariosId().get(i));
            horario.setStatusHorario(StatusHorario.ABERTO);
            horarioService.save(horario);
        }
        model.addAttribute("msgHorariosSalvos", "Horários salvos com sucesso");
        return "/artist/agenda";
    }
}
