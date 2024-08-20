package by.mishelby.logistickapplication.repository.TruckRepository;

import by.mishelby.logistickapplication.model.truck.Truck.Truck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TruckRepository extends JpaRepository<Truck, Integer> {
    Optional<Truck> findById(Integer id);
}
