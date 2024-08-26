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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class TruckController {
    private final TruckDAO truckDAO;

    @GetMapping("/trucks")
    public ResponseEntity<Iterable<Truck>> getTrucks() {
        Collection<Truck> trucks = truckDAO.findAllTrucks();
        if (trucks.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(trucks);
    }

    @GetMapping("/truck/{id}")
    public ResponseEntity<Truck> getTruck(@PathVariable("id") int id) {
        Truck truck = truckDAO.findTruckById(id);
        return ResponseEntity.ok().body(truck);
    }

    @PostMapping("/truck")
    public ResponseEntity<Truck> createTruck(@RequestBody @Valid TruckCreateDTO truckDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorResponse
                    = ExceptionControllerAdvice.getErrorResponse(bindingResult);

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorResponse.getMessage());
        }

        Truck truck = truckDAO.saveTruck(truckDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(truck.getId())
                .toUri();

        return ResponseEntity.created(location).body(truck);
    }

    @PatchMapping("/truck/{id}")
    public ResponseEntity<HttpStatus> updateTruck(@PathVariable("id") int id,
                                                  @RequestBody @Valid TruckUpdateDTO truckUpdateDTO,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorResponse
                    = ExceptionControllerAdvice.getErrorResponse(bindingResult);

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorResponse.getMessage());
        }

        truckDAO.updateTruck(id, truckUpdateDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @DeleteMapping("/truck/{id}")
    public ResponseEntity<HttpStatus> deleteTruck(@PathVariable("id") int truckId) {
        truckDAO.deleteTruck(truckId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
