package com.aviator.location_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aviator.location_service.model.Airport;
import java.util.List;
import java.util.Optional;


public interface AirportRepository extends JpaRepository<Airport, Long> {

    public Optional<Airport> findByIataCode(String iataCode);

    Optional<Airport> findByIataCodeAndIdNot(String iataCode, Long id);

    public List<Airport> findByCityId(Long id);


}
