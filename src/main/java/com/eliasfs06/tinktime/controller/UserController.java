package com.eliasfs06.tinktime.controller;

import com.eliasfs06.tinktime.model.User;
import com.eliasfs06.tinktime.repository.GenericRepository;
import com.eliasfs06.tinktime.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController extends GenericController<User> {

    public UserController(UserRepository userRepository) {
        super(userRepository);
    }

}
