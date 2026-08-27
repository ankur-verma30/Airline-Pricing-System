package com.aviator.location_service.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.aviator.location_service.exceptions.ResourceNotFoundException;
import com.aviator.location_service.mapper.AirportMapper;
import com.aviator.location_service.model.Airport;
import com.aviator.location_service.model.City;
import com.aviator.location_service.payload.request.AirportRequest;
import com.aviator.location_service.payload.response.AirportResponse;
import com.aviator.location_service.repository.AirportRepository;
import com.aviator.location_service.repository.CityRepository;
import com.aviator.location_service.service.AirportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AirportServiceImpl implements AirportService {

    private final AirportRepository airportRepository;
    private final CityRepository cityRepository;

    @Override
    public AirportResponse createAirport(AirportRequest airportRequest) {
        airportRepository.findByIataCode(airportRequest.getIataCode())
                .ifPresent(airport -> {
                    throw new IllegalArgumentException(
                            "The " + airport.getName() + " already exists for this IATA code");
                });

        City city = cityRepository.findById(airportRequest.getCityID())
                .orElseThrow(
                        () -> new ResourceNotFoundException(
                                "City with id " + airportRequest.getCityID() + " not found"));
        Airport saveAirport = AirportMapper.AirportRequestToAirportEntity(airportRequest);
        saveAirport.setCity(city);
        airportRepository.save(saveAirport);
        return AirportMapper.AirportEntityToAirportResponse(saveAirport);
    }

    @Override
    public AirportResponse getAirportById(Long id) {
        return AirportMapper.AirportEntityToAirportResponse(airportRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("The Airport do not exist")));
    }

    @Override
    public AirportResponse updateAirport(Long id, AirportRequest airportRequest) {
        Airport airport = airportRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("The Airport do not exist"));

        if (airportRequest.getIataCode() != null && !airport.getIataCode().equals(airportRequest.getIataCode())
                && airportRepository.findByIataCode(airportRequest.getIataCode()).isPresent()) {
            throw new IllegalArgumentException(
                    "The " + airport.getName() + " already exists for this IATA code");
        }

        airport.setIataCode(
                airportRequest.getIataCode() != null ? airportRequest.getIataCode() : airport.getIataCode());
        airport.setName(airportRequest.getName() != null ? airportRequest.getName() : airport.getName());
        airport.setTimezoneId(
                airportRequest.getTimeZoneId() != null ? airportRequest.getTimeZoneId() : airport.getTimezoneId());
        airport.setAddress(airportRequest.getAddress() != null ? airportRequest.getAddress() : airport.getAddress());
        airport.setGeoCode(airportRequest.getGeoCode() != null ? airportRequest.getGeoCode() : airport.getGeoCode());
        airportRepository.save(airport);

        return AirportMapper.AirportEntityToAirportResponse(airport);

    }

    @Override
    public List<AirportResponse> getAllAirports() {
        return airportRepository.findAll().stream().map(AirportMapper::AirportEntityToAirportResponse).toList();
    }

    @Override
    public void deleteAirport(Long id) {
        airportRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("The Airport do not exist"));
        airportRepository.deleteById(id);
    }

    @Override
    public List<AirportResponse> getAirportByCityId(Long id) {
        return airportRepository.findByCityId(id).stream().map(AirportMapper::AirportEntityToAirportResponse).toList();
    }

}
