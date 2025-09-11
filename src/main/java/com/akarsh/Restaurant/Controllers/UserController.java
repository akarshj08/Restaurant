package com.akarsh.Restaurant.Controllers;

import java.util.List;
import com.akarsh.Restaurant.Entities.User;
import com.akarsh.Restaurant.Exceptions.InvalidIDException;
import com.akarsh.Restaurant.Exceptions.InvalidOperationException;
import com.akarsh.Restaurant.Services.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@Tag(name = "User APIs")
public class UserController
{

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    ResponseEntity<UUID> addUser(@Valid @RequestBody User user) throws InvalidOperationException
    {
        UUID id = userService.saveUser(user);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity<List<User>> getAllUser()
    {
        List<User> userList = userService.getAllUser();
        if(userList.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(userList,HttpStatus.FOUND);
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<User> getById(@PathVariable UUID id) throws InvalidIDException
    {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteById(@PathVariable UUID id) throws InvalidIDException
    {
        User user = userService.getUserById(id);
        userService.deleteUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update")
    ResponseEntity<User> updateUser(@Valid @RequestBody User user) throws InvalidIDException, InvalidOperationException
    {
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }
}