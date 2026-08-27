package com.aviator.location_service.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.aviator.location_service.payload.request.CityRequest;
import com.aviator.location_service.payload.response.CityResponse;


public interface CityService {
    
    CityResponse createCity(CityRequest cityRequest);

    CityResponse getCityById(Long id);

    CityResponse updateCity(Long id, CityRequest cityRequest);

    void deleteCity(Long id);

    Page<CityResponse> getAllCities(Pageable pageable);

    Page<CityResponse> searchCities(String keyword, Pageable pageable);

    Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable);

    boolean cityExists(String cityCode);
}
