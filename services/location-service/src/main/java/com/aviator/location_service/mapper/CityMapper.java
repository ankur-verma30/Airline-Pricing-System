package com.aviator.location_service.mapper;

import com.aviator.location_service.model.City;
import com.aviator.location_service.payload.request.CityRequest;
import com.aviator.location_service.payload.response.CityResponse;

public class CityMapper {

    public static City CityRequestToEntity(CityRequest cityRequest) {
        if (cityRequest == null)
            return null;

        return City.builder()
                .name(cityRequest.getName())
                .cityCode(cityRequest.getCityCode())
                .countryCode(cityRequest.getCountryCode())
                .countryName(cityRequest.getCountryName())
                .regionCode(cityRequest.getRegionCode())
                .timeZoneId(cityRequest.getTimeZoneId())
                .build();
    }

    public static CityResponse CityToCityResponse(City city) {
        if (city == null)
            return null;

        return CityResponse.builder()
                .id(city.getId())
                .name(city.getName())
                .cityCode(city.getCityCode())
                .countryCode(city.getCountryCode())
                .countryName(city.getCountryName())
                .regionCode(city.getRegionCode())
                .build();
    }

    public static City updateCityFromRequest(City city, CityRequest cityRequest) {
        if (cityRequest.getName() != null)
            city.setName(cityRequest.getName().trim());

        if (cityRequest.getCityCode() != null)
            city.setCityCode(cityRequest.getCityCode().toUpperCase().trim());

        if (cityRequest.getCountryCode() != null)
            city.setCountryCode(cityRequest.getCountryCode().toUpperCase().trim());

        if (cityRequest.getCountryName() != null)
            city.setCountryName(cityRequest.getCountryName().trim());

        if (cityRequest.getRegionCode() != null)
            city.setRegionCode(cityRequest.getRegionCode().toUpperCase().trim());

        return city;
    }

}
