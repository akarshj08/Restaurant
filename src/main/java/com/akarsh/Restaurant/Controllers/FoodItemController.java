package com.akarsh.Restaurant.Controllers;

import com.akarsh.Restaurant.Entities.FoodItem;
import com.akarsh.Restaurant.Exceptions.InvalidIDException;
import com.akarsh.Restaurant.Services.FoodItemService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/foodItem")
@Tag(name = "FoodItem APIs")
public class FoodItemController
{

    @Autowired
    FoodItemService foodItemService;

    @PostMapping("/add")
    @PreAuthorize("hasRole('User')")
    ResponseEntity<UUID> addFoodItem(@Valid @RequestBody FoodItem foodItem)
    {
        UUID id =foodItemService.save(foodItem);
        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    ResponseEntity<List<FoodItem>> getAllFoodItems()
    {
        List<FoodItem> foodItems = foodItemService.getAllFoodItem();
        if(foodItems.isEmpty())
        {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            return new ResponseEntity<>(foodItems,HttpStatus.FOUND);
        }
    }

    @GetMapping("/get/{id}")
    ResponseEntity<FoodItem> getfoodItemById(@PathVariable UUID id) throws InvalidIDException
    {
        FoodItem foodItem = foodItemService.getFoodItemById(id);
        return new ResponseEntity<>(foodItem,HttpStatus.FOUND);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteFoodItem(@PathVariable UUID id) throws InvalidIDException
    {
        FoodItem foodItem = foodItemService.getFoodItemById(id);
        foodItemService.deleteFoodItem(foodItem);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update")
    ResponseEntity<FoodItem> updateFoodItem(@Valid @RequestBody FoodItem foodItem) throws InvalidIDException
    {
        FoodItem updatedFoodItem = foodItemService.updateFoodItem(foodItem);
        return new ResponseEntity<>(updatedFoodItem,HttpStatus.OK);
    }
}