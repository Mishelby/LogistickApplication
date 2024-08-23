package by.mishelby.logistickapplication.service.CityService;

import by.mishelby.logistickapplication.domain.CityDTO.CityCreateDTO;
import by.mishelby.logistickapplication.domain.CityDTO.CityUpdateDTO;
import by.mishelby.logistickapplication.model.city.City;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CityService {
    List<City> findAll();

    City findById(int id);

    City findByName(String name);

    City createCity(CityCreateDTO cityCreateDTO);

    ResponseEntity<HttpStatus> updateCity(CityUpdateDTO cityUpdateDTO);

    void deleteCity(int cityId);
}
