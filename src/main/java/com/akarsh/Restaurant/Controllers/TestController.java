package com.akarsh.Restaurant.Controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Test API")
public class TestController
{
    @GetMapping("/hello")
    private String Hello()
    {
        return "HELLO";
    }
}