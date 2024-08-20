package by.mishelby.logistickapplication.controller.Rest;

import by.mishelby.logistickapplication.exceptions.Handler.ExceptionControllerAdvice;
import by.mishelby.logistickapplication.domain.TruckDTO.TruckCreateDTO;
import by.mishelby.logistickapplication.domain.TruckDTO.TruckUpdateDTO;
import by.mishelby.logistickapplication.model.truck.Truck.Truck;
import by.mishelby.logistickapplication.service.TruckService.TruckDAO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/trucks")
@RequiredArgsConstructor
public class TruckController {
    private final TruckDAO truckDAO;

    @GetMapping("/all")
    public ResponseEntity<List<Truck>> getTrucks() {
        List<Truck> trucks = truckDAO.findAllTrucks();

        if (trucks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(trucks);
    }

    @PostMapping("/create")
    public ResponseEntity<Truck> createTruck(@RequestBody @Valid TruckCreateDTO truckDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorResponse
                    = ExceptionControllerAdvice.getErrorResponse(bindingResult);

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorResponse.getMessage());
        }
        Truck truck = truckDAO.saveTruck(truckDTO);
        return ResponseEntity.ok(truck);
    }

    @PatchMapping("/update")
    public ResponseEntity<HttpStatus> updateTruck(@RequestBody @Valid TruckUpdateDTO truckUpdateDTO,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorResponse
                    = ExceptionControllerAdvice.getErrorResponse(bindingResult);

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorResponse.getMessage());
        }
        truckDAO.updateTruck(truckUpdateDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteTruck(@RequestParam int truckId) {
        truckDAO.deleteTruck(truckId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
