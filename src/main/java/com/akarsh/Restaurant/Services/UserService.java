package com.akarsh.Restaurant.Services;

import java.time.LocalDate;
import java.util.*;

import com.akarsh.Restaurant.Entities.Role;
import com.akarsh.Restaurant.Entities.User;
import com.akarsh.Restaurant.Exceptions.InvalidIDException;
import com.akarsh.Restaurant.Exceptions.InvalidOperationException;
import com.akarsh.Restaurant.Repositories.RoleRepo;
import com.akarsh.Restaurant.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserService
{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    public UUID saveUser(User user) throws InvalidOperationException
    {
        user.setCreated(LocalDate.now());
        List<Role> roles = new ArrayList<>();

        for(Role role : user.getRoles())
        {
            Optional<Role> fetchedRole = roleRepo.findByRole(role.getRole());
            if(fetchedRole.isPresent())
            {
                roles.add(fetchedRole.get());
            }
            else
            {
                throw new InvalidOperationException("Role "+role.getRole()+" not found");
            }
        }

        user.setRoles(roles);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User saved = userRepo.save(user);
        return saved.getId();
    }

    public List<User> getAllUser()
    {
        return userRepo.findAll();
    }

    public User getUserById(UUID id) throws InvalidIDException
    {
        Optional<User> user = userRepo.findById(id);
        if(user.isEmpty())
        {
            throw new InvalidIDException("Invalid Id "+id);
        }
        else
        {
            return user.orElse(null);
        }
    }

    public void deleteUser(User user)
    {
        userRepo.delete(user);
    }

    public User updateUser(User user) throws InvalidIDException, InvalidOperationException
    {
        User savedUser = getUserById(user.getId());
        if(!savedUser.getEmail().equals(user.getEmail()))
        {
            throw new InvalidOperationException("Email cannot be Modified");
        }
        user.setModified(LocalDate.now());
        return userRepo.save(user);
    }
}