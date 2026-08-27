package com.aviator.location_service.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aviator.location_service.payload.request.AirportRequest;
import com.aviator.location_service.payload.response.AirportResponse;
import com.aviator.location_service.service.AirportService;
import com.aviator.payload.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/airports")
public class AirportController {

    private final AirportService airportService;

    @PostMapping("/create")
    ResponseEntity<AirportResponse> createAirport(@Valid @RequestBody AirportRequest airportRequest) throws Exception {
        return ResponseEntity.ok(airportService.createAirport(airportRequest));
    }

    @GetMapping("/{id}")
    ResponseEntity<AirportResponse> getAirportById(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(airportService.getAirportById(id));
    }

    @PutMapping("/{id}")
    ResponseEntity<AirportResponse> updateAirport(@PathVariable Long id,
            @Valid @RequestBody AirportRequest airportRequest) throws Exception {
        return ResponseEntity.ok(airportService.updateAirport(id, airportRequest));
    }

    @GetMapping("/all")
    ResponseEntity<List<AirportResponse>> getAllAirports() throws Exception {
        return ResponseEntity.ok(airportService.getAllAirports());
    }

    @GetMapping("/city/{id}")
    ResponseEntity<List<AirportResponse>> getAirportByCityId(@PathVariable Long id) throws Exception {
        return ResponseEntity.ok(airportService.getAirportByCityId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteAirport(@PathVariable Long id) throws Exception {
        airportService.deleteAirport(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Airport deleted successfully"), HttpStatus.OK);
    }
}
