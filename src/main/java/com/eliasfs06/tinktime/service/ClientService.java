package com.eliasfs06.tinktime.service;

import com.eliasfs06.tinktime.model.Client;
import com.eliasfs06.tinktime.model.User;
import com.eliasfs06.tinktime.repository.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends GenericService<Client> {
    public ClientService(GenericRepository<Client> repository) {
        super(repository);
    }

    public void createClient(User user) {
        Client client = new Client();
        client.setUser(user);
        save(client);
    }
}
