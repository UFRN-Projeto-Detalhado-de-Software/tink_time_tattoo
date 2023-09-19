package com.eliasfs06.tinktime.restController;

import com.eliasfs06.tinktime.model.Client;
import com.eliasfs06.tinktime.repository.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("client")
public class ClienteRestController extends GenericRestController<Client> {
    public ClienteRestController(GenericRepository<Client> repository) {
        super(repository);
    }
}
