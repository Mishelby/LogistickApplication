package by.mishelby.logistickapplication.repository.CityRepository;

import by.mishelby.logistickapplication.model.city.City;

import by.mishelby.logistickapplication.model.country_map.CountryMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
    Optional<City> findByName(String name);
    boolean existsByNameAndCountryMap(String name, CountryMap countryMap);
}
