package by.mishelby.logistickapplication.domain.TruckDTO;

import by.mishelby.logistickapplication.model.truck.TruckStatus.TruckStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TruckUpdateDTO {
    @NotNull(message = "Truck id should not be a null")
    private Integer id;

    @Pattern(regexp = "[A-Z]{2}\\d{5}", message = "Invalid registration number format")
    private String registrationNumber;

    @NotNull(message = "Drivers shift hours should not be a null")
    private Integer driversShiftHours;

    @NotNull(message = "Truck status should not be a null")
    private TruckStatus truckStatus;

    @NotNull(message = "Current city should not be a null")
    private String currentCity;

}
