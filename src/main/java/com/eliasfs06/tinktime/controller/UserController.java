package com.eliasfs06.tinktime.controller;

import com.eliasfs06.tinktime.model.User;
import com.eliasfs06.tinktime.model.dto.RegisterDTO;
import com.eliasfs06.tinktime.repository.UserRepository;
import com.eliasfs06.tinktime.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController extends GenericController<User> {

    @Autowired
    private UserService userService;

    public UserController(UserRepository userRepository) {
        super(userRepository);
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "/login";
    }

    @PostMapping("/register")
    public String register(Model model, RegisterDTO userData){
        userService.registerUser(userData);
        return "/login";
    }

    @GetMapping("/register/form")
    public String registerFomr(Model model){
        model.addAttribute("user", new RegisterDTO());
        return "/register";
    }

}
