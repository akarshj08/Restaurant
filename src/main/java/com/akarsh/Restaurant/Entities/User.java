package com.akarsh.Restaurant.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.*;

@Entity(name = "users")
public class User
{
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    UUID id;

    @NotBlank(message = "First Name is required cannot be empty")
    String first_name;

    String last_name;

    @Column(unique = true)
    @NotBlank(message = "Email is required cannot be empty")
    String email;

    @NotBlank(message = "Password can not be empty")
    String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    List<Role> roles;

    LocalDate created;
    LocalDate modified;

    public List<Role> getRoles()
    {
        return roles;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setRoles(List<Role> roles)
    {
        this.roles = roles;
    }

    public UUID getId()
    {
        return id;
    }

    public void setId(UUID id)
    {
        this.id = id;
    }

    public String getFirst_name()
    {
        return first_name;
    }

    public void setFirst_name(String first_name)
    {
        this.first_name = first_name;
    }

    public String getLast_name()
    {
        return last_name;
    }

    public void setLast_name(String last_name)
    {
        this.last_name = last_name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public LocalDate getCreated()
    {
        return created;
    }

    public void setCreated(LocalDate created)
    {
        this.created = created;
    }

    public LocalDate getModified()
    {
        return modified;
    }

    public void setModified(LocalDate modified)
    {
        this.modified = modified;
    }
}