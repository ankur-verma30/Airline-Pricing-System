package com.aviator.location_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aviator.location_service.payload.request.CityRequest;
import com.aviator.location_service.payload.response.CityResponse;
import com.aviator.location_service.service.CityService;
import com.aviator.payload.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    private final Logger logger = LoggerFactory.getLogger(CityController.class);

    @PostMapping("/create")
    public ResponseEntity<CityResponse> createCity(@Valid @RequestBody CityRequest cityRequest) throws Exception {
        logger.info("Recieved request to create city:{}", cityRequest);
        return new ResponseEntity<>(cityService.createCity(cityRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityResponse> getCityById(@PathVariable Long id) throws Exception {
        return new ResponseEntity<>(cityService.getCityById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<CityResponse>> getAllCities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return new ResponseEntity<>(cityService.getAllCities(pageable), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CityResponse> updateCity(@PathVariable Long id, @Valid @RequestBody CityRequest cityRequest)
            throws Exception {
        return new ResponseEntity<>(cityService.updateCity(id, cityRequest), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCity(@PathVariable Long id) throws Exception {
        cityService.deleteCity(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("City deleted successfully"), HttpStatus.OK);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<Page<CityResponse>> searchCities(@PathVariable String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return new ResponseEntity<>(cityService.searchCities(keyword, pageable), HttpStatus.OK);
    }


    @GetMapping("/getCitiesByCountryCode/{countryCode}")
    public ResponseEntity<Page<CityResponse>> getCitiesByCountryCode(@PathVariable String countryCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection) {
        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);
        return new ResponseEntity<>(cityService.getCitiesByCountryCode(countryCode.toUpperCase(), pageable), HttpStatus.OK);
    }

    @GetMapping("/exists/{cityCode}")
    public ResponseEntity<Boolean> checkCityExists(@PathVariable String cityCode) {
        return new ResponseEntity<>(cityService.cityExists(cityCode.toUpperCase()), HttpStatus.OK);
    }
}
