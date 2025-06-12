package com.akarsh.Restaurant.Services;

import com.akarsh.Restaurant.Entities.FoodItem;
import com.akarsh.Restaurant.Exceptions.InvalidIDException;
import com.akarsh.Restaurant.Repositories.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class FoodItemService
{
    @Autowired
    FoodItemRepo foodItemRepo;

    public UUID save(FoodItem foodItem)
    {
        foodItem.setCreateDate(LocalDate.now());
        FoodItem saved = foodItemRepo.save(foodItem);
        return saved.getId();
    }

    public List<FoodItem> getAllFoodItem()
    {
        return foodItemRepo.findAll();
    }

    public FoodItem getFoodItemById(UUID id) throws InvalidIDException
    {
        Optional<FoodItem> foodItem = foodItemRepo.findById(id);
        if(foodItem.isEmpty())
        {
            throw new InvalidIDException("Invalid Id "+id);
        }
        else
        {
            return foodItem.orElse(null);
        }
    }

    public FoodItem updateFoodItem(FoodItem foodItem) throws InvalidIDException
    {
        FoodItem foodItem1 = getFoodItemById(foodItem.getId());
        foodItem.setUpdateDate(LocalDate.now());
        foodItemRepo.save(foodItem);
        return foodItem;
    }

    public void deleteFoodItem(FoodItem foodItem)
    {
        foodItemRepo.delete(foodItem);
    }
}