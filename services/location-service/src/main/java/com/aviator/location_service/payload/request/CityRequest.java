package com.aviator.location_service.payload.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CityRequest {

    @NotBlank(message = "City name is required")
    @Size(max = 100, message = "City name must not exceed 100 characters")
    private String name;

    @NotBlank(message = "City code is required")
    @Size(max = 10, message = "City code must not exceed 10 characters")
    private String cityCode;

    @NotBlank(message = "Country code is required")
    @Size(max = 5, message = "Country code must not exceed 5 characters")
    private String countryCode;

    @NotBlank(message = "Region code is required")
    @Size(max = 10, message = "Region code must not exceed 10 characters")
    private String regionCode;

    @NotBlank(message = "Country name is required")
    @Size(max = 100, message = "Country name must not exceed 100 characters")
    private String countryName;
}
