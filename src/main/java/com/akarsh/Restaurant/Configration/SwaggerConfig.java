package com.akarsh.Restaurant.Configration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig
{
    @Bean
    public OpenAPI MyCustomConfig()
    {
        return new OpenAPI().info(
                new Info().title("Restaurant App APIs Documentation")
                        .description("By Akarsh Jain")
        );
    }
}