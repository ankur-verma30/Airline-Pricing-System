package com.aviator.location_service.service.impl;

import com.aviator.location_service.repository.CityRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.aviator.location_service.exceptions.ResourceNotFoundException;
import com.aviator.location_service.mapper.CityMapper;
import com.aviator.location_service.model.City;
import com.aviator.location_service.payload.request.CityRequest;
import com.aviator.location_service.payload.response.CityResponse;
import com.aviator.location_service.service.CityService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(CityServiceImpl.class);

    @Override
    public CityResponse createCity(CityRequest cityRequest) throws ResourceNotFoundException {

        logger.info("Creating city with name: {}", cityRequest.getName());
        if (cityRepository.existsByCityCode(cityRequest.getCityCode())) {
            throw new ResourceNotFoundException("City with code " + cityRequest.getCityCode() + " already exists");
        }
        City city = CityMapper.CityRequestToEntity(cityRequest);
        city = cityRepository.save(city);
        logger.info("City created with id: {}", city.getId());
        return CityMapper.CityToCityResponse(city);
    }

    @Override
    public CityResponse getCityById(Long id) throws ResourceNotFoundException {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City with id " + id + " not found"));
        return CityMapper.CityToCityResponse(city);
    }

    @Override
    public CityResponse updateCity(Long id, CityRequest cityRequest) throws ResourceNotFoundException {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City with id " + id + " not found"));

        if (cityRepository.existsByCityCodeAndIdNot(cityRequest.getCityCode(), id)) {
            throw new ResourceNotFoundException("City with code " + cityRequest.getCityCode() + " already exists");
        }

        City updatedCity = CityMapper.updateCityFromRequest(city, cityRequest);
        updatedCity = cityRepository.save(updatedCity);
        return CityMapper.CityToCityResponse(updatedCity);

    }

    @Override
    public void deleteCity(Long id) throws ResourceNotFoundException {
        City city = cityRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("City with id " + id + " not found"));
        cityRepository.delete(city);
    }

    @Override
    public Page<CityResponse> getAllCities(Pageable pageable) {
        return cityRepository.findAll(pageable).map(CityMapper::CityToCityResponse);
    }

    @Override
    public Page<CityResponse> searchCities(String keyword, Pageable pageable) {
        return cityRepository.searchByKeyword(keyword, pageable).map(CityMapper::CityToCityResponse);
    }

    @Override
    public Page<CityResponse> getCitiesByCountryCode(String countryCode, Pageable pageable) {
        return cityRepository.findByCountryCodeIgnoreCase(countryCode, pageable).map(CityMapper::CityToCityResponse);
    }

    @Override
    public boolean cityExists(String cityCode) {
        return cityRepository.existsByCityCode(cityCode);
    }
}
