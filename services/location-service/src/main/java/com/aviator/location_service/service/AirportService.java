package com.aviator.location_service.service;

import java.util.List;

import com.aviator.location_service.payload.request.AirportRequest;
import com.aviator.location_service.payload.response.AirportResponse;

public interface AirportService {

    AirportResponse createAirport(AirportRequest airportRequest);

    AirportResponse getAirportById(Long id);

    AirportResponse updateAirport(Long id, AirportRequest airportRequest);

    List<AirportResponse> getAllAirports();
    
    void deleteAirport(Long id);

    List<AirportResponse> getAirportByCityId(Long id);
}
