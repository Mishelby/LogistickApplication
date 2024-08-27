package by.mishelby.logistickapplication.repository.DriverRepository;

import by.mishelby.logistickapplication.model.driver.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Integer> {
    Optional<Driver> findById(Integer id);

}
