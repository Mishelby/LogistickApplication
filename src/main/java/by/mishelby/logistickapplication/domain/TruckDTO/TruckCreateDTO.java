package by.mishelby.logistickapplication.domain.TruckDTO;

import by.mishelby.logistickapplication.model.truck.TruckStatus.TruckStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class TruckCreateDTO {
    @Pattern(regexp = "[A-Z]{2}\\d{5}", message = "Invalid registration number format")
    private String registrationNumber;

    @NotNull(message = "Drivers shift hours should not be null")
    private Integer driversShiftHours;

    @NotNull(message = "Truck capacity should not be null")
    private Integer capacity;

    @NotNull(message = "Truck status should not be null")
    private TruckStatus truckStatus;

    @Column(name = "current_city", nullable = false)
    private String currentCity;
}
