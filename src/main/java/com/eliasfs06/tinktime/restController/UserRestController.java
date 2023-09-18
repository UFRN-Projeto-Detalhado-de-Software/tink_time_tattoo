package com.eliasfs06.tinktime.restController;

import com.eliasfs06.tinktime.model.User;
import com.eliasfs06.tinktime.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserRestController extends GenericRestController<User> {

    public UserRestController(UserRepository userRepository) {
        super(userRepository);
    }


}
