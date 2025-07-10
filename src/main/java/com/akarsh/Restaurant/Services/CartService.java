package com.akarsh.Restaurant.Services;

import java.util.*;
import com.akarsh.Restaurant.Entities.CartItem;
import com.akarsh.Restaurant.Entities.FoodItem;
import com.akarsh.Restaurant.Entities.User;
import com.akarsh.Restaurant.Exceptions.InvalidIDException;
import com.akarsh.Restaurant.Exceptions.InvalidOperationException;
import com.akarsh.Restaurant.Repositories.CartRepo;
import com.akarsh.Restaurant.Repositories.FoodItemRepo;
import com.akarsh.Restaurant.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService
{
    @Autowired
    private CartRepo cartRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private FoodItemRepo foodItemRepo;

    public List<CartItem> fetchUserCartItems(UUID userId)
    {
        return cartRepo.findByUserId(userId);
    }

    public CartItem addCart(CartItem cartItem) throws InvalidIDException
    {
        User user = userRepo.findById(cartItem.getUserId()).orElseThrow(
                () -> new InvalidIDException("User not Found with id "+cartItem.getUserId())
        );
        FoodItem foodItem = foodItemRepo.findById(cartItem.getItemId()).orElseThrow(
                () -> new InvalidIDException("Food Item not Found with id "+cartItem.getItemId())
        );
        cartItem.setItemName(foodItem.getName());
        cartItem.setPrice(foodItem.getPrice());
        return cartRepo.save(cartItem);
    }

    public CartItem removeCartItem(Long cartItemId,UUID userId) throws InvalidOperationException, InvalidIDException
    {
        CartItem cartItem = cartRepo.findById(cartItemId).orElseThrow(
                () -> new InvalidIDException("Invalid CartItem Id "+cartItemId)
        );

        if(!cartItem.getUserId().equals(userId))
        {
            throw new InvalidOperationException("User doesn't have this item in their Cart .");
        }

        cartRepo.deleteById(cartItemId);
        return cartItem;
    }

    public CartItem updateCartItem(CartItem cartItem) throws InvalidIDException, InvalidOperationException
    {
       CartItem cart = cartRepo.findById(cartItem.getId()).orElseThrow(
               () -> new InvalidIDException("Invalid Cart Item Id "+cartItem.getId())
       );

       if(!cartItem.getUserId().equals(cart.getUserId()))
       {
           throw new InvalidIDException("Invalid User Id");
       }

       if(!cartItem.getItemId().equals(cart.getItemId()))
       {
           throw new InvalidIDException("Invalid Item Id");
       }

       if(cart.getQuantity()==cartItem.getQuantity())
       {
           throw new InvalidOperationException("Quantity of Item is already "+cartItem.getQuantity());
       }

       cart.setQuantity(cartItem.getQuantity());
       return cartRepo.save(cart);
    }

}