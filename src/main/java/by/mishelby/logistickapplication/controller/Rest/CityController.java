package by.mishelby.logistickapplication.controller.Rest;

import by.mishelby.logistickapplication.domain.CityDTO.CityCreateDTO;
import by.mishelby.logistickapplication.exceptions.CityException.CityException;
import by.mishelby.logistickapplication.exceptions.Handler.ExceptionControllerAdvice;
import by.mishelby.logistickapplication.model.city.City;
import by.mishelby.logistickapplication.service.CityService.CityDAO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {
    private final CityDAO cityDAO;

    @GetMapping("/all")
    public ResponseEntity<List<City>> getAllCities() {
        List<City> allCities = cityDAO.findAll();
        if (allCities.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(allCities);
    }

    @PostMapping("/add")
    public ResponseEntity<City> createCity(@RequestBody @Valid CityCreateDTO cityCreateDTO,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorResponse
                    = ExceptionControllerAdvice.getErrorResponse(bindingResult);

            throw new CityException(errorResponse.getMessage());
        }
        City city = cityDAO.createCity(cityCreateDTO);

        return ResponseEntity.ok(city);
    }
}
