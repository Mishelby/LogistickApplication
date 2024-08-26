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
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CargoController {
    private final CargoDAO cargoDAO;

    @GetMapping("/cargos")
    public ResponseEntity<List<Cargo>> getAllCargos() {
        List<Cargo> cargos = cargoDAO.finAllCargos();
        if (cargos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(cargos);
    }

    @PostMapping("/cargo")
    public ResponseEntity<Cargo> createCargo(@RequestBody @Valid CargoCreateDTO cargoCreateDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorResponse
                    = ExceptionControllerAdvice.getErrorResponse(bindingResult);

            throw new CargoException(errorResponse.getMessage());
        }

        Cargo cargo = cargoDAO.createCargo(cargoCreateDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(cargo.getId())
                .toUri();

        return ResponseEntity.created(location).body(cargo);
    }

    @PatchMapping("/cargo]/{id}")
    public ResponseEntity<Cargo> updateCargo(@PathVariable("id") int id,
                                             @RequestBody @Valid CargoUpdateDTO cargoUpdateDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorResponse
                    = ExceptionControllerAdvice.getErrorResponse(bindingResult);

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorResponse.getMessage());
        }

        Cargo cargo = cargoDAO.updateCargo(id, cargoUpdateDTO);

        return ResponseEntity.ok().body(cargo);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteCargo(@RequestParam int id) {
        cargoDAO.deleteCargo(id);
        return ResponseEntity.ok().build();
    }
}
