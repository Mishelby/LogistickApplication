package by.mishelby.logistickapplication.service.TruckService;

import by.mishelby.logistickapplication.exceptions.NoChangesDetectedException;
import by.mishelby.logistickapplication.exceptions.ResourceNotFoundException;
import by.mishelby.logistickapplication.exceptions.TruckExceptions.TruckException;
import by.mishelby.logistickapplication.domain.TruckDTO.TruckCreateDTO;
import by.mishelby.logistickapplication.domain.TruckDTO.TruckUpdateDTO;
import by.mishelby.logistickapplication.mapper.TruckMapper;
import by.mishelby.logistickapplication.model.truck.Truck.Truck;
import by.mishelby.logistickapplication.repository.TruckRepository.TruckRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class TruckDAO implements TruckService {
    private final TruckRepository truckRepository;
    private final TruckMapper truckMapper;

    @Override
    @Transactional(readOnly = true)
    public Collection<Truck> findAllTrucks() {
        Collection<Truck> allTrucks = truckRepository.findAll();
        if (allTrucks.isEmpty()) {
            return new ArrayList<>();
        }

        return allTrucks;
    }

    @Override
    public Truck findTruckById(int id) {
        return truckRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Truck not found by id: " + id)
        );
    }

    @Override
    public Truck saveTruck(TruckCreateDTO truckDTO) {
        if (truckDTO == null) {
            throw new IllegalArgumentException("Truck DTO is null");
        }

        Truck truck = truckMapper.truckDTOToTruck(truckDTO);
        return truckRepository.save(truck);
    }

    @Override
    public Truck updateTruck(int id, TruckUpdateDTO truckUpdateDTO) {
        if (truckUpdateDTO == null) {
            throw new IllegalArgumentException("Truck DTO is null");
        }

        Truck truck = this.findTruckById(id);
        boolean isNew = isNew(truckUpdateDTO, truck);

        if (!isNew) {
            throw new NoChangesDetectedException("No changed detected for truck with id: " + id);
        }

        truckRepository.save(truck);
        return truck;
    }

    private boolean isNew(TruckUpdateDTO truckUpdateDTO, Truck truck) {
        boolean isNew = false;

        if (!Objects.equals(truck.getRegistrationNumber(), truckUpdateDTO.getRegistrationNumber())) {
            truck.setRegistrationNumber(truckUpdateDTO.getRegistrationNumber());
            isNew = true;
        }

        if (!Objects.equals(truck.getCurrentCity(), truckUpdateDTO.getCurrentCity())) {
            truck.setCurrentCity(truckUpdateDTO.getCurrentCity());
            isNew = true;
        }

        if (!Objects.equals(truck.getDriversShiftHours(), truckUpdateDTO.getDriversShiftHours())) {
            truck.setDriversShiftHours(truckUpdateDTO.getDriversShiftHours());
            isNew = true;
        }

        if (!Objects.equals(truck.getTruckStatus(), truckUpdateDTO.getTruckStatus())) {
            truck.setTruckStatus(truckUpdateDTO.getTruckStatus());
            isNew = true;
        }
        return isNew;
    }

    @Override
    public void deleteTruck(int id) {
        Truck truck = this.findTruckById(id);
        truckRepository.delete(truck);
    }
}
