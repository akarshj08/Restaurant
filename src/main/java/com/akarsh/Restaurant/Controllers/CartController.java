package com.akarsh.Restaurant.Controllers;

import com.akarsh.Restaurant.Entities.CartItem;
import com.akarsh.Restaurant.Exceptions.InvalidIDException;
import com.akarsh.Restaurant.Exceptions.InvalidOperationException;
import com.akarsh.Restaurant.Services.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import java.util.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController
{

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartItem> addCartItem(@RequestBody @Valid CartItem cartItem) throws InvalidIDException
    {
        CartItem savedItem = cartService.addCart(cartItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedItem);
    }

    @GetMapping("/get/{userId}")
    private ResponseEntity<List<CartItem>> getCartItem(@PathVariable UUID userId)
    {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.fetchUserCartItems(userId));
    }

    @DeleteMapping("/delete/{userId}/{cartItemId}")
    private ResponseEntity<CartItem> deleteCartItemById(@PathVariable UUID userId, @PathVariable Long cartItemId) throws InvalidOperationException, InvalidIDException
    {
       CartItem deletedCart = cartService.removeCartItem(cartItemId, userId);
       return  ResponseEntity.status(HttpStatus.OK).body(deletedCart);
    }

    @PostMapping("/update")
    private ResponseEntity<CartItem> updateCartItem(@RequestBody CartItem cartItem) throws InvalidOperationException, InvalidIDException
    {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.updateCartItem(cartItem));
    }

}