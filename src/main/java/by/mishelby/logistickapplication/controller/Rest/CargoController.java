package by.mishelby.logistickapplication.controller.Rest;

import by.mishelby.logistickapplication.domain.CargoDTO.CargoCreateDTO;
import by.mishelby.logistickapplication.domain.CargoDTO.CargoUpdateDTO;
import by.mishelby.logistickapplication.exceptions.CargoException.CargoException;
import by.mishelby.logistickapplication.exceptions.Handler.ExceptionControllerAdvice;
import by.mishelby.logistickapplication.model.cargo.Cargo;
import by.mishelby.logistickapplication.service.CargoService.CargoDAO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cargos")
@RequiredArgsConstructor
public class CargoController {
    private final CargoDAO cargoDAO;

    @GetMapping
    public ResponseEntity<List<Cargo>> getAllCargos() {
        List<Cargo> cargos = cargoDAO.finAllCargos();
        if (cargos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cargos);
    }

    @PostMapping("/add")
    public ResponseEntity<Cargo> createCargo(@RequestBody @Valid CargoCreateDTO cargoCreateDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorResponse
                    = ExceptionControllerAdvice.getErrorResponse(bindingResult);

            throw new CargoException(errorResponse.getMessage());
        }

        Cargo cargo = cargoDAO.createCargo(cargoCreateDTO);
        return ResponseEntity.ok(cargo);
    }

    @PatchMapping("/update")
    public ResponseEntity<HttpStatus> updateCargo(@RequestBody @Valid CargoUpdateDTO cargoUpdateDTO,
                                                  BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorResponse
                    = ExceptionControllerAdvice.getErrorResponse(bindingResult);

            throw new CargoException(errorResponse.getMessage());
        }

        return cargoDAO.updateCargo(cargoUpdateDTO);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteCargo(@RequestParam int id) {
        cargoDAO.deleteCargo(id);
        return ResponseEntity.ok().build();
    }
}
