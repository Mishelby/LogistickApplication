package by.mishelby.logistickapplication.mapper;

import by.mishelby.logistickapplication.domain.CityDTO.CityCreateDTO;
import by.mishelby.logistickapplication.model.city.City;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CityMapper {
    City cityDTOToCity(CityCreateDTO cityCreateDTO);
}
