package by.mishelby.logistickapplication.service.TruckService;


import by.mishelby.logistickapplication.domain.TruckDTO.TruckCreateDTO;
import by.mishelby.logistickapplication.domain.TruckDTO.TruckUpdateDTO;
import by.mishelby.logistickapplication.model.truck.Truck.Truck;

import java.util.List;

public interface TruckService {
    List<Truck> findAllTrucks();

    Truck findTruckById(int id);

    Truck saveTruck(TruckCreateDTO truckDTO);

    void updateTruck(TruckUpdateDTO truckUpdateDTO);

    void deleteTruck(int id);

}
