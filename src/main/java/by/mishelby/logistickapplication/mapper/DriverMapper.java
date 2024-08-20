package by.mishelby.logistickapplication.mapper;


import by.mishelby.logistickapplication.domain.DriverDTO.DriverCreateDTO;
import by.mishelby.logistickapplication.domain.DriverDTO.DriverUpdateDTO;
import by.mishelby.logistickapplication.model.driver.Driver;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverMapper {
    Driver driverDTOToDriver(DriverCreateDTO driverDTO);
//    Driver driverUpdateDTOToDriver(DriverUpdateDTO driverUpdateDTO);
    DriverCreateDTO driverToDriverDTO(Driver driver);
}
