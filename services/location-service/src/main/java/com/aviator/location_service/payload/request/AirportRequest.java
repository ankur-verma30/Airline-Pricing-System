package com.aviator.location_service.payload.request;

import com.aviator.embeddable.Address;
import com.aviator.embeddable.GeoCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AirportRequest {

    @NotBlank(message = "IATA code is required")
    @Size(min=3, max=3, message="IATA code must be 3 characters long")
    private String iataCode;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Address is required")
    private Address address;

    @NotNull(message = "GeoCode is required")
    private GeoCode geoCode;

    @NotBlank(message = "Timezone ID is required")
    private String timeZoneId;

    @NotNull(message = "City ID is required")
    private Long cityID;
}
