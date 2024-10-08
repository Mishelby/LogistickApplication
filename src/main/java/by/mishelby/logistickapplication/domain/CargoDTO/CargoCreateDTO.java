package by.mishelby.logistickapplication.domain.CargoDTO;

import by.mishelby.logistickapplication.model.cargoStatus.CargoStatus;
import by.mishelby.logistickapplication.model.route_point.RoutePoint.RoutePoint;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CargoCreateDTO {
    @NotNull(message = "Cargo number should not be null")
    private Integer number;

    @NotNull(message = "Description should not be null")
    private String description;

    @Column(name = "weight")
    @NotNull(message = "Weight should not be an empty")
    private Integer weight;

    @NotNull(message = "Cargo status should not be an empty")
    private CargoStatus cargoStatus;

}
