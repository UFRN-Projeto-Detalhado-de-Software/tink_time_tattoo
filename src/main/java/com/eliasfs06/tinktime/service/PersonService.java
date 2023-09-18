package com.eliasfs06.tinktime.service;

import com.eliasfs06.tinktime.model.Person;
import com.eliasfs06.tinktime.repository.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService extends GenericService<Person>{
    public PersonService(GenericRepository<Person> repository) {
        super(repository);
    }
}
