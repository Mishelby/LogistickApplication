package by.mishelby.logistickapplication.domain.CargoDTO;

import by.mishelby.logistickapplication.model.cargoStatus.CargoStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CargoUpdateDTO {

    @NotBlank(message = "Carog id should not be a null")
    private Integer cargoId;

    @NotBlank(message = "Cargo number should not be null or blank")
    private Integer number;

    @NotBlank(message = "Description should not be null or blank")
    private String description;

    @NotBlank(message = "Weight should not be an empty or null")
    private Integer weight;

    @NotBlank(message = "Cargo status should not be an empty or null")
    private CargoStatus cargoStatus;

    @NotNull(message = "Route point ID should not be null")
    private Integer routePointId;
}
