package by.mishelby.logistickapplication.mapper;

import by.mishelby.logistickapplication.domain.TruckDTO.TruckCreateDTO;
import by.mishelby.logistickapplication.domain.TruckDTO.TruckUpdateDTO;
import by.mishelby.logistickapplication.model.truck.Truck.Truck;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TruckMapper {
    Truck truckDTOToTruck(TruckCreateDTO truckCreateDTO);
    Truck truckUpdateDTOToTruck(TruckUpdateDTO  truckUpdateDTO);
}
