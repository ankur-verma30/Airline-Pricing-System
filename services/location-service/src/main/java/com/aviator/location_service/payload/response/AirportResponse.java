package com.aviator.location_service.payload.response;

import com.aviator.embeddable.Address;
import com.aviator.embeddable.GeoCode;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportResponse {

    private Long id;
    private String iataCode;
    private String name;
    private Address address;
    private GeoCode geoCode;
    private String timeZoneId;
    private String city;
}
