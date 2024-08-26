package by.mishelby.logistickapplication.service.DriverService;

import by.mishelby.logistickapplication.domain.DriverDTO.DriverCreateDTO;
import by.mishelby.logistickapplication.domain.DriverDTO.DriverUpdateDTO;
import by.mishelby.logistickapplication.model.driver.Driver;
import by.mishelby.logistickapplication.model.truck.Truck.Truck;

import java.util.Collection;
import java.util.List;

public interface DriverService {
    Collection<Driver> findAllDrivers();

    Driver findById(Integer id);

    Driver saveDriver(DriverCreateDTO driverDTO);

    Driver updateDriver(int driverId, DriverUpdateDTO updatedDriver);

    void deleteDriver(int id);
}
