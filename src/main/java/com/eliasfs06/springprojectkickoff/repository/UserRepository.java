package com.eliasfs06.springprojectkickoff.repository;

import com.eliasfs06.springprojectkickoff.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends GenericRepository<User> {
    User findByUsername(String username);
}
