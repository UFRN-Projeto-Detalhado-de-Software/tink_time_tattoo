package com.eliasfs06.tinktime.service;

import com.eliasfs06.tinktime.model.Artist;
import com.eliasfs06.tinktime.model.User;
import com.eliasfs06.tinktime.model.dto.ArtistDTO;
import com.eliasfs06.tinktime.repository.ArtistRepository;
import com.eliasfs06.tinktime.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArtistService extends GenericService<Artist>{

    @Autowired
    private ArtistRepository repository;

    public ArtistService(GenericRepository<Artist> repository) {
        super(repository);
    }

    public ArtistDTO findByUser(User user) {
        Artist artist = repository.findByUser(user);
        return new ArtistDTO(artist);
    }

    public void createArtist(User user) {
        Artist artist = new Artist();
        artist.setUser(user);
        save(artist);
    }
}
