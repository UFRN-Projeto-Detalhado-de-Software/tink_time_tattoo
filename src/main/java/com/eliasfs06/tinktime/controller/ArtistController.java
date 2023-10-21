package com.eliasfs06.tinktime.controller;

import com.eliasfs06.tinktime.model.Artist;
import com.eliasfs06.tinktime.model.TattoStyle;
import com.eliasfs06.tinktime.model.User;
import com.eliasfs06.tinktime.model.dto.ArtistDTO;
import com.eliasfs06.tinktime.repository.GenericRepository;
import com.eliasfs06.tinktime.service.ArtistService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("artist")
public class ArtistController extends GenericController<Artist> {

    @Autowired
    private ArtistService artistService;

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
}
