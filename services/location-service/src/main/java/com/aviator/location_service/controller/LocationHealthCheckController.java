package com.aviator.location_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aviator.payload.response.ApiResponse;

@RestController
public class LocationHealthCheckController {
    
    @GetMapping("/health")
    public String healthCheck() {
        ApiResponse response=new ApiResponse();
        response.setMessage("Location Service is up and running!");
        return response.getMessage();
    }

}
