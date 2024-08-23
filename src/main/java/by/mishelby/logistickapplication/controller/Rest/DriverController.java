package by.mishelby.logistickapplication.controller.Rest;

import by.mishelby.logistickapplication.exceptions.Handler.ExceptionControllerAdvice;
import by.mishelby.logistickapplication.domain.DriverDTO.DriverCreateDTO;
import by.mishelby.logistickapplication.domain.DriverDTO.DriverUpdateDTO;
import by.mishelby.logistickapplication.model.driver.Driver;
import by.mishelby.logistickapplication.service.DriverService.DriverDAO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverDAO driverDAO;

    @GetMapping("/all")
    public ResponseEntity<List<Driver>> getDrivers() {
        List<Driver> allDrivers = driverDAO.findAllDrivers();

        if (allDrivers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(allDrivers);
    }

    @PostMapping("/add")
    public ResponseEntity<Driver> createDriver(@RequestBody @Valid DriverCreateDTO driverDTO,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorResponse
                    = ExceptionControllerAdvice.getErrorResponse(bindingResult);

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorResponse.getMessage());
        }

        Driver driver = driverDAO.saveDriver(driverDTO);
        return new ResponseEntity<>(driver, HttpStatus.CREATED);

    }

    @PatchMapping("/update")
    public ResponseEntity<HttpStatus> updateDriver(@RequestBody @Valid DriverUpdateDTO updatedDriver,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorResponse
                    = ExceptionControllerAdvice.getErrorResponse(bindingResult);

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorResponse.getMessage());
        }
        driverDAO.updateDriver(updatedDriver);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteDriver(@RequestParam int driverId) {
        driverDAO.deleteDriver(driverId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
