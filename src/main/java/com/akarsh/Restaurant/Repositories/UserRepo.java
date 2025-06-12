package com.akarsh.Restaurant.Repositories;

import com.akarsh.Restaurant.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID>
{

    Optional<User> findByEmail(String username);
}