package by.mishelby.logistickapplication.domain.CargoDTO;

import by.mishelby.logistickapplication.model.cargoStatus.CargoStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CargoDTO {
    private BigInteger id;

    @NotNull(message = "Cargo number should not be null")
    private Integer number;

    @NotNull(message = "Description should not be null")
    private String description;

    @NotNull(message = "Weight should not be an empty or null")
    private Integer weight;

    @NotNull(message = "Cargo status should not be an empty or null")
    private CargoStatus cargoStatus;

}
