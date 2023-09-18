package com.eliasfs06.springprojectkickoff.service;

import com.eliasfs06.springprojectkickoff.model.Person;
import com.eliasfs06.springprojectkickoff.repository.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends GenericService<Person>{
    public PersonService(GenericRepository<Person> repository) {
        super(repository);
    }
}
