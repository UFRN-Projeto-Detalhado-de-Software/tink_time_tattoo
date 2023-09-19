package com.eliasfs06.tinktime.restController;

import com.eliasfs06.tinktime.model.Artist;
import com.eliasfs06.tinktime.repository.GenericRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("artist")
public class ArtistRestController extends GenericRestController<Artist> {
    public ArtistRestController(GenericRepository<Artist> repository) {
        super(repository);
    }
}
