package by.mishelby.logistickapplication.repository.CountryMapRepository;

import by.mishelby.logistickapplication.model.country_map.CountryMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface CountryMapRepository extends JpaRepository<CountryMap, BigInteger> {
}
