package com.project.seenit.repositories;

import com.project.seenit.models.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<Users, String> {

    Users findUserByUsername(String username);

    Optional<Users> findByUsername(String username);
}
