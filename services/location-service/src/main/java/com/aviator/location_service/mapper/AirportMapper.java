package com.aviator.location_service.mapper;

import com.aviator.location_service.model.Airport;
import com.aviator.location_service.payload.request.AirportRequest;
import com.aviator.location_service.payload.response.AirportResponse;

public class AirportMapper {

    public static Airport AirportRequestToAirportEntity(AirportRequest request) {
        if (request == null)
            return null;

        return Airport.builder().iataCode(request.getIataCode())
                .name(request.getName()).timezoneId(request.getTimeZoneId()).address(request.getAddress())
                .geoCode(request.getGeoCode()).build();
    }

    public static AirportResponse AirportEntityToAirportResponse(Airport airport) {
        if (airport == null)
            return null;

        return AirportResponse.builder().id(airport.getId()).iataCode(airport.getIataCode())
                .name(airport.getName()).timeZoneId(airport.getTimezoneId()).address(airport.getAddress())
                .geoCode(airport.getGeoCode()).build();
    }
}
