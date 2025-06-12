package com.akarsh.Restaurant.Repositories;

import com.akarsh.Restaurant.Entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role,Long>
{
    Optional<Role> findByRole(String roleName);
}