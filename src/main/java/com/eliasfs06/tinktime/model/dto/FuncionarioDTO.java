package com.eliasfs06.tinktime.model.dto;

import com.eliasfs06.tinktime.model.Agenda;
import com.eliasfs06.tinktime.model.Funcionario;
import com.eliasfs06.tinktime.model.User;
import jakarta.validation.constraints.NotBlank;

public class FuncionarioDTO {

    @NotBlank(message = "{aboutMe.not.blank}")
    private String aboutMe;
    private User user;
    private Long id;

    public FuncionarioDTO(){}

    public FuncionarioDTO(Funcionario funcionario) {
        this.aboutMe = funcionario.getAboutMe();
        this.user = funcionario.getUser();
        this.id = funcionario.getId();
    }

    public Funcionario toFuncionario(){
        Funcionario funcionario = new Funcionario();
        funcionario.setAboutMe(this.aboutMe);
        funcionario.setAgenda(new Agenda());
        funcionario.setUser(this.user);
        funcionario.setId(this.id);
        return funcionario;
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe;
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
