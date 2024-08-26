package by.mishelby.logistickapplication.service.TruckService;


import by.mishelby.logistickapplication.domain.TruckDTO.TruckCreateDTO;
import by.mishelby.logistickapplication.domain.TruckDTO.TruckUpdateDTO;
import by.mishelby.logistickapplication.model.truck.Truck.Truck;

import java.util.Collection;
import java.util.List;

public interface TruckService {
    Collection<Truck> findAllTrucks();

    Truck findTruckById(int id);

    Truck saveTruck(TruckCreateDTO truckDTO);

    Truck updateTruck(int id, TruckUpdateDTO truckUpdateDTO);

    void deleteTruck(int id);

}
