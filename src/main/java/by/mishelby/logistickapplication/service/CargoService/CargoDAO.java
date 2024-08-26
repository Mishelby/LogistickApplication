package by.mishelby.logistickapplication.service.CargoService;

import by.mishelby.logistickapplication.domain.CargoDTO.CargoCreateDTO;
import by.mishelby.logistickapplication.domain.CargoDTO.CargoUpdateDTO;
import by.mishelby.logistickapplication.exceptions.NoChangesDetectedException;
import by.mishelby.logistickapplication.mapper.CargoMapper;
import by.mishelby.logistickapplication.model.cargo.Cargo;
import by.mishelby.logistickapplication.model.route_point.RoutePoint.RoutePoint;
import by.mishelby.logistickapplication.repository.CargoRepository.CargoRepository;
import by.mishelby.logistickapplication.service.RoutePointsService.RoutePointsDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CargoDAO implements CargoService {
    private final CargoRepository cargoRepository;
    private final CargoMapper cargoMapper;
    private final RoutePointsDAO routePointsDAO;

    @Override
    public List<Cargo> finAllCargos() {
        List<Cargo> allCargos = cargoRepository.findAll();
        if (allCargos.isEmpty()) {
            log.info("Cargo list is empty");
            return Collections.emptyList();
        }
        return allCargos;
    }

    @Override
    public Cargo createCargo(CargoCreateDTO cargoCreateDTO) {
        if (cargoCreateDTO == null) {
            log.info("Cargo DTO to Cargo is null");
            throw new IllegalArgumentException("Cargo DTO cannot be null");
        }

        Cargo cargoDTOToCargo = cargoMapper.createCargoDTOToCargo(cargoCreateDTO);

        return cargoRepository.save(cargoDTOToCargo);
    }

    @Override
    public Cargo findCargoById(int id) {
        return cargoRepository.findById(BigInteger.valueOf(id)).orElseThrow(()
                -> new RuntimeException("Cargo with id " + id + " not found"));
    }

    @Override
    public Cargo updateCargo(int id, CargoUpdateDTO cargoUpdateDTO) {
        if (cargoUpdateDTO == null) {
            throw new IllegalArgumentException("Cargo DTO cannot be null");
        }

        Cargo cargo = this.findCargoById(cargoUpdateDTO.getCargoId());

        boolean extracted = updateCargoFields(cargoUpdateDTO, cargo);

        if (!extracted) {
            throw new NoChangesDetectedException("No changes detected for Cargo with id: " + id);
        }

        return cargoRepository.save(cargo);
    }

    private boolean updateCargoFields(CargoUpdateDTO cargoUpdateDTO, Cargo cargo) {
        boolean isUpdated = false;
        RoutePoint newRoutePoint = routePointsDAO.findById(cargoUpdateDTO.getRoutePointId());

        if (!Objects.equals(cargo.getNumber(), cargoUpdateDTO.getNumber())) {
            cargo.setNumber(cargoUpdateDTO.getNumber());
            isUpdated = true;
        }

        if (!Objects.equals(cargo.getDescription(), cargoUpdateDTO.getDescription())) {
            cargo.setDescription(cargoUpdateDTO.getDescription());
            isUpdated = true;
        }

        if (!Objects.equals(cargo.getCargoStatus(), cargoUpdateDTO.getCargoStatus())) {
            cargo.setCargoStatus(cargoUpdateDTO.getCargoStatus());
            isUpdated = true;
        }

        if (!Objects.equals(cargo.getRoutePoint(), newRoutePoint)) {
            cargo.setRoutePoint(newRoutePoint);
            isUpdated = true;
        }

        if (!Objects.equals(cargo.getWeight(), cargoUpdateDTO.getWeight())) {
            cargo.setWeight(cargoUpdateDTO.getWeight());
            isUpdated = true;
        }

        return isUpdated;
    }


    @Override
    public void deleteCargo(int id) {
        Cargo cargo = this.findCargoById(id);
        cargoRepository.delete(cargo);
    }
}
