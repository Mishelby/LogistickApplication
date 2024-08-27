package by.mishelby.logistickapplication.service.CargoService;

import by.mishelby.logistickapplication.domain.CargoDTO.CargoCreateDTO;
import by.mishelby.logistickapplication.domain.CargoDTO.CargoUpdateDTO;
import by.mishelby.logistickapplication.model.cargo.Cargo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CargoService {
    List<Cargo> finAllCargos();

    Cargo findCargoById(int id);

    Cargo createCargo(CargoCreateDTO cargoCreateDTO);

    Cargo saveCargo(Cargo cargo);

    Cargo updateCargo(int id, CargoUpdateDTO cargoUpdateDTO);

    void deleteCargo(int id);
}
