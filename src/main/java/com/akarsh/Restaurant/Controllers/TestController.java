package com.akarsh.Restaurant.Controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController
{
    @GetMapping("/hello")
    private String Hello()
    {
        return "HELLO";
    }
}