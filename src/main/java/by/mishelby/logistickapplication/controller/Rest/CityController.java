package by.mishelby.logistickapplication.controller.Rest;

import by.mishelby.logistickapplication.domain.CityDTO.CityCreateDTO;
import by.mishelby.logistickapplication.exceptions.Handler.ExceptionControllerAdvice;
import by.mishelby.logistickapplication.model.city.City;
import by.mishelby.logistickapplication.service.CityService.CityDAO;
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

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CityController {
    private final CityDAO cityDAO;

    @GetMapping("/cities")
    public ResponseEntity<Iterable<City>> getAllCities() {
        Collection<City> allCities = cityDAO.findAll();
        if (allCities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(allCities);
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<City> getCity(@PathVariable int id) {
        City city = cityDAO.findById(id);
        return ResponseEntity.ok().body(city);
    }

    @PostMapping("/city")
    public ResponseEntity<City> createCity(@RequestBody @Valid CityCreateDTO cityCreateDTO,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorResponse
                    = ExceptionControllerAdvice.getErrorResponse(bindingResult);

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorResponse.getMessage());
        }
        City city = cityDAO.createCity(cityCreateDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(city.getId())
                .toUri();

        return ResponseEntity.created(location).body(city);
    }
}
