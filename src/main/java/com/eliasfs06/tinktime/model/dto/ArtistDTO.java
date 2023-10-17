package com.eliasfs06.tinktime.model.dto;

import com.eliasfs06.tinktime.model.Artist;
import com.eliasfs06.tinktime.model.TattoStyle;
import com.eliasfs06.tinktime.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ArtistDTO {

    @NotBlank(message = "{aboutMe.not.blank}")
    private String aboutMe;
    private List<TattoStyle> styles;
    private User user;
    private Long id;

    public ArtistDTO(){}

    public ArtistDTO(Artist artist) {
        this.aboutMe = artist.getAboutMe();
        this.styles = artist.getStyles();
        this.user = artist.getUser();
        this.id = artist.getId();
    }

    public Artist toArtist(){
        Artist artist = new Artist();
        artist.setAboutMe(this.aboutMe);
        artist.setStyles(this.styles);
        artist.setUser(this.user);
        artist.setId(this.id);
        return artist;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public List<TattoStyle> getStyles() {
        return styles;
    }

    public void setStyles(List<TattoStyle> styles) {
        this.styles = styles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
