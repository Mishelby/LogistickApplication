package by.mishelby.logistickapplication.service.CountryMap;

import by.mishelby.logistickapplication.model.country_map.CountryMap;
import by.mishelby.logistickapplication.repository.CountryMapRepository.CountryMapRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class CountryMapDAO implements CountryMapService {
    private final CountryMapRepository countryMapRepository;

    @Override
    public List<CountryMap> findAll() {
        List<CountryMap> allCountriesMap = countryMapRepository.findAll();
        if (allCountriesMap.isEmpty()) {
            return Collections.emptyList();
        }

        return allCountriesMap;
    }

    @Override
    public CountryMap findById(BigInteger id) {
        return countryMapRepository.findById(id).orElseThrow(()
                -> new RuntimeException("Country not found with id: " + id));
    }

    @Override
    public CountryMap save(CountryMap countryMap) {
        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> update(CountryMap countryMap) {
        return null;
    }

    @Override
    public void delete(CountryMap countryMap) {

    }
}
