package by.mishelby.logistickapplication.service.CityService;

import by.mishelby.logistickapplication.domain.CityDTO.CityCreateDTO;
import by.mishelby.logistickapplication.domain.CityDTO.CityUpdateDTO;
import by.mishelby.logistickapplication.exceptions.CityException.CityException;
import by.mishelby.logistickapplication.mapper.CityMapper;
import by.mishelby.logistickapplication.model.city.City;
import by.mishelby.logistickapplication.model.country_map.CountryMap;
import by.mishelby.logistickapplication.repository.CityRepository.CityRepository;
import by.mishelby.logistickapplication.service.CountryMap.CountryMapDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CityDAO implements CityService {
    private final CityRepository cityRepository;
    private final CityMapper cityMapper;
    private final CountryMapDAO countryMapDAO;

    @Override
    public Collection<City> findAll() {
        Collection<City> allCities = cityRepository.findAll();
        if (allCities.isEmpty()) {
            return Collections.emptyList();
        }
        return cityRepository.findAll();
    }

    @Override
    public City findById(int cityId) {
        return cityRepository.findById(cityId).orElseThrow(()
                -> new CityException("City not found by id: " + cityId));
    }

    @Override
    public City findByName(String name) {
        return cityRepository.findByName(name).orElseThrow(()
                -> new CityException("City not found by name: " + name));
    }

    @Override
    public City createCity(CityCreateDTO cityCreateDTO) {
        if (cityCreateDTO == null) {
            throw new CityException("CityCreateDTO is null");
        }

        City city = cityMapper.cityDTOToCity(cityCreateDTO);
        CountryMap countryMap = countryMapDAO.findById(city.getCountryMap().getId());

        boolean cityExists = cityRepository.existsByNameAndCountryMap(city.getName(), countryMap);

        if (cityExists) {
            throw new CityException("City already exists");
        }

        cityRepository.save(city);

        return city;
    }


    @Override
    public ResponseEntity<HttpStatus> updateCity(CityUpdateDTO cityUpdateDTO) {
        // todo
        return null;
    }

    @Override
    public void deleteCity(int cityId) {
        City city = this.findById(cityId);
        cityRepository.delete(city);
    }
}
