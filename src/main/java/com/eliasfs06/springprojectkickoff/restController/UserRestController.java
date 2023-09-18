package com.eliasfs06.springprojectkickoff.restController;

import com.eliasfs06.springprojectkickoff.model.User;
import com.eliasfs06.springprojectkickoff.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserRestController extends GenericRestController<User> {

    public UserRestController(UserRepository userRepository) {
        super(userRepository);
    }


}
