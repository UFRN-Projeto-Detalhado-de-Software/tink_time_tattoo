package com.eliasfs06.tinktime.service;

import com.eliasfs06.tinktime.model.Artist;
import com.eliasfs06.tinktime.repository.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class ArtistService extends GenericService<Artist>{
    public ArtistService(GenericRepository<Artist> repository) {
        super(repository);
    }
}
