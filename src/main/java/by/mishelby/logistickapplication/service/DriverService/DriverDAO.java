package by.mishelby.logistickapplication.service.DriverService;

import by.mishelby.logistickapplication.exceptions.DriverException.DriverDTOException;
import by.mishelby.logistickapplication.domain.DriverDTO.DriverCreateDTO;
import by.mishelby.logistickapplication.domain.DriverDTO.DriverUpdateDTO;
import by.mishelby.logistickapplication.exceptions.NoChangesDetectedException;
import by.mishelby.logistickapplication.exceptions.ResourceNotFoundException;
import by.mishelby.logistickapplication.exceptions.TruckExceptions.TruckException;
import by.mishelby.logistickapplication.mapper.DriverMapper;
import by.mishelby.logistickapplication.model.driver.Driver;
import by.mishelby.logistickapplication.model.truck.Truck.Truck;
import by.mishelby.logistickapplication.repository.DriverRepository.DriverRepository;
import by.mishelby.logistickapplication.service.TruckService.TruckDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DriverDAO implements DriverService {
    private final DriverRepository driverRepository;
    private final TruckDAO truckDAO;
    private final DriverMapper driverMapper;

    @Override
    public Collection<Driver> findAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Driver findById(Integer id) {
        return driverRepository.findById(id).orElseThrow(()
                -> new ResourceNotFoundException("Driver not found with id: " + id));
    }

    @Override
    public Driver updateDriver(int id, DriverUpdateDTO updatedDriver) {
        if (updatedDriver == null) {
            throw new IllegalArgumentException("Updated Driver cannot be null");
        }

        Driver driver = findById(id);
        Truck currentTruck = driver.getCurrentTruck();

        if (currentTruck == null) {
            currentTruck = truckDAO.findTruckById(updatedDriver.getNewTruck());
        }

        Truck newTruck = truckDAO.findTruckById(updatedDriver.getNewTruck());

        boolean isNew = isNew(updatedDriver, driver, currentTruck, newTruck);

        if (!isNew) {
            throw new NoChangesDetectedException("No changes detected for Driver with id: " + id);
        }

        driverRepository.save(driver);
        return driver;
    }

    private static boolean isNew(DriverUpdateDTO updatedDriver, Driver driver, Truck currentTruck, Truck newTruck) {
        boolean isNew = false;

        if (!Objects.equals(driver.getFirstName(), updatedDriver.getFirstName())) {
            driver.setFirstName(updatedDriver.getFirstName());
            isNew = true;
        }

        if (!Objects.equals(driver.getLastName(), updatedDriver.getLastName())) {
            driver.setLastName(updatedDriver.getLastName());
            isNew = true;
        }

        if (!Objects.equals(driver.getPersonalNumber(), updatedDriver.getPersonalNumber())) {
            driver.setPersonalNumber(updatedDriver.getPersonalNumber());
            isNew = true;
        }

        if (!Objects.equals(driver.getCurrentCity(), updatedDriver.getCurrentCity())) {
            driver.setCurrentCity(updatedDriver.getCurrentCity());
            isNew = true;
        }

        if (!Objects.equals(currentTruck.getId(), newTruck.getId())) {
            driver.setCurrentTruck(newTruck);
            if (newTruck.getDriver() == null) {
                newTruck.setDriver(new ArrayList<>());
            }
            newTruck.getDriver().add(driver);

            log.info("New Truck is: " + newTruck);
            isNew = true;
        }
        return isNew;
    }

    @Override
    public Driver saveDriver(DriverCreateDTO driverCreateDTO) {
        if (driverCreateDTO == null) {
            throw new DriverDTOException("Driver DTO cannot be null");
        }
        Driver driver = driverMapper.driverDTOToDriver(driverCreateDTO);
        Truck truck = truckDAO.findTruckById(driverCreateDTO.getTruckId());

        truck.getDriver().add(driver);
        driver.setCurrentTruck(truck);

        log.info("Driver truck list: " + driver.getCurrentTruck());
        return driverRepository.save(driver);
    }

    @Override
    public void deleteDriver(int id) {
        Driver driver = this.findById(id);
        driverRepository.delete(driver);
        log.info("Driver with id {} was successfully deleted.", id);
    }
}
