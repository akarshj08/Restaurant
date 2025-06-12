package com.akarsh.Restaurant.Services;

import com.akarsh.Restaurant.Entities.User;
import com.akarsh.Restaurant.Repositories.UserRepo;
import com.akarsh.Restaurant.Security.MyUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService
{
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<User> user = userRepo.findByEmail(username);
        if(user.isPresent())
        {
            return new MyUserDetails(user.get());
        }
        else
        {
            throw new UsernameNotFoundException("Invalid Username "+username);
        }
    }

}
