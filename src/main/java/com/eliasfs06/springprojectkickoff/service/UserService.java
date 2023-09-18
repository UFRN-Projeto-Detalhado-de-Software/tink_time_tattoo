package com.eliasfs06.springprojectkickoff.service;

import com.eliasfs06.springprojectkickoff.model.Person;
import com.eliasfs06.springprojectkickoff.model.User;
import com.eliasfs06.springprojectkickoff.model.UserRole;
import com.eliasfs06.springprojectkickoff.model.dto.AuthenticationDTO;
import com.eliasfs06.springprojectkickoff.model.dto.RegisterDTO;
import com.eliasfs06.springprojectkickoff.repository.GenericRepository;
import com.eliasfs06.springprojectkickoff.repository.PersonRepository;
import com.eliasfs06.springprojectkickoff.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<User>{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonService personService;

    public UserService(GenericRepository<User> userRepository) {
        super(userRepository);
    }

    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void registerUser(RegisterDTO registerData){
        Person person = registerData.toPerson();
        User user = registerData.toUser();
        user.setPerson(person);
        encodePassword(user);

        if(user.getUserRole() == null)
            user.setUserRole(UserRole.USER);

        personService.create(person);
        create(user);
    }

    public void encodePassword(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
    }

}
