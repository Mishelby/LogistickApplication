package by.mishelby.logistickapplication.service.DriverService;

import by.mishelby.logistickapplication.domain.DriverDTO.DriverCreateDTO;
import by.mishelby.logistickapplication.domain.DriverDTO.DriverUpdateDTO;
import by.mishelby.logistickapplication.model.driver.Driver;

import java.util.List;

public interface DriverService {
    List<Driver> findAllDrivers();

    Driver findById(Integer id);

    void updateDriver(DriverUpdateDTO updatedDriver);

    Driver saveDriver(DriverCreateDTO driverDTO);

    void deleteDriver(int id);
}
