package by.mishelby.logistickapplication.mapper;

import by.mishelby.logistickapplication.domain.CargoDTO.CargoCreateDTO;
import by.mishelby.logistickapplication.domain.CargoDTO.CargoUpdateDTO;
import by.mishelby.logistickapplication.model.cargo.Cargo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CargoMapper {
    Cargo createCargoDTOToCargo(CargoCreateDTO cargoCreateDTO);

    Cargo updateCargoDTOToCargo(CargoUpdateDTO cargoUpdateDTO);
}
