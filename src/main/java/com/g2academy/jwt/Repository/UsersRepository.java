package com.g2academy.jwt.Repository;

import com.g2academy.jwt.Entity.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<Users,Integer> {
    Users findUserByUsername(String username);

    Optional<Users> findByEmail(String email);
}
