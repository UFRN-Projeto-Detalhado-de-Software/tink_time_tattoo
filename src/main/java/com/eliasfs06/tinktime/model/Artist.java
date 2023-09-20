package com.eliasfs06.tinktime.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Artist extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aboutMe;

    @ElementCollection
    @CollectionTable(name = "styles", joinColumns = @JoinColumn(name = "artist_id"))
    private List<String> styles;

    @OneToOne
    private User user;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
    }

    public List<String> getStyles() {
        return styles;
    }

    public void setStyles(List<String> styles) {
        this.styles = styles;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
