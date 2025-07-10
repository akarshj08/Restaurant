package com.akarsh.Restaurant.Repositories;

import java.util.*;
import com.akarsh.Restaurant.Entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface CartRepo extends JpaRepository<CartItem,Long>
{
    List<CartItem> findByUserId(UUID userId);
}