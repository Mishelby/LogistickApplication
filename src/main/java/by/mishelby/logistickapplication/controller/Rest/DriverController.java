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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DriverController {
    private final DriverDAO driverDAO;

    @GetMapping("/drivers")
    public ResponseEntity<Iterable<Driver>> getDrivers() {
        Collection<Driver> allDrivers = driverDAO.findAllDrivers();
        if (allDrivers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(allDrivers);
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<Driver> getDriver(@PathVariable("id") int id) {
        Driver driver = driverDAO.findById(id);
        return ResponseEntity.ok().body(driver);
    }

    @PostMapping("/driver")
    public ResponseEntity<Driver> createDriver(@RequestBody @Valid DriverCreateDTO driverDTO,
                                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorResponse
                    = ExceptionControllerAdvice.getErrorResponse(bindingResult);

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorResponse.getMessage());
        }

        Driver driver = driverDAO.saveDriver(driverDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(driver.getId())
                .toUri();

        return ResponseEntity.created(location).body(driver);
    }

    @PatchMapping("/driver/{id}")
    public ResponseEntity<HttpStatus> updateDriver(@PathVariable("id") int id,
                                                   @RequestBody @Valid DriverUpdateDTO updatedDriver,
                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorResponse
                    = ExceptionControllerAdvice.getErrorResponse(bindingResult);

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorResponse.getMessage());
        }

        driverDAO.updateDriver(id, updatedDriver);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/driver/{id}")
    public ResponseEntity<HttpStatus> deleteDriver(@PathVariable("id") int driverId) {
        driverDAO.deleteDriver(driverId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
