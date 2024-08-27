package by.mishelby.logistickapplication.domain.TruckDTO;

import by.mishelby.logistickapplication.model.driver.Driver;
import by.mishelby.logistickapplication.model.truck.TruckStatus.TruckStatus;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TruckDTO {
    private BigInteger id;

    @Pattern(regexp = "[A-Z]{2}\\d{5}", message = "Invalid registration number format")
    private String registrationNumber;

    @NotNull(message = "Drivers shift hours should not be null")
    private Integer driversShiftHours;

    @NotNull(message = "Truck capacity should not be null")
    private Integer capacity;

    @NotNull(message = "Truck status should not be null")
    private TruckStatus truckStatus;

    private String currentCity;

    private List<Driver> driver;
}
