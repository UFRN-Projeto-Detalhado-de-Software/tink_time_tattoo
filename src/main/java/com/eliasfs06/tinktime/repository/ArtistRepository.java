package com.eliasfs06.tinktime.repository;

import com.eliasfs06.tinktime.model.Artist;
import com.eliasfs06.tinktime.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends GenericRepository<Artist> {

    @Query(value = "SELECT a FROM Artist a WHERE a.user = ?1 AND a.active = true", nativeQuery = false)
    Artist findByUser(User user);
}
