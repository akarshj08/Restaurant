package com.akarsh.Restaurant.Repositories;

import com.akarsh.Restaurant.Entities.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem,UUID>
{

}