package com.aviator.user_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aviator.payload.response.ApiResponse;

@RestController
@RequestMapping("/users/home")
public class HomeController {

    @GetMapping
    public ApiResponse healthCheck(){
        return new ApiResponse("User Service is up and running!");
    }
}
