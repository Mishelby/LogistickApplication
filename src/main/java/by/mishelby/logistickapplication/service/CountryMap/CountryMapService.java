package by.mishelby.logistickapplication.service.CountryMap;

import by.mishelby.logistickapplication.model.country_map.CountryMap;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigInteger;
import java.util.List;

public interface CountryMapService {
    List<CountryMap> findAll();

    CountryMap findById(BigInteger id);

    CountryMap save(CountryMap countryMap);

    ResponseEntity<HttpStatus> update(CountryMap countryMap);

    void delete(CountryMap countryMap);
}
