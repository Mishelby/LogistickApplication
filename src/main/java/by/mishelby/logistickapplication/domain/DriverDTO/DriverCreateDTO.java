package by.mishelby.logistickapplication.domain.DriverDTO;

import by.mishelby.logistickapplication.model.driver.DriverStatus.DriverStatus;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class DriverCreateDTO {
    @NotBlank(message = "First name should not be null or blank")
    private String firstName;

    @NotBlank(message = "Last name should not be null or blank")
    private String lastName;

    @NotBlank(message = "Personal number should not be null or blank")
    @Size(min = 10, max = 15, message = "Personal number should be between 10 and 15")
    private String personalNumber;

    @Min(value = 0, message = "Hours worked should not be less than 0")
    @Max(value = 24, message = "Hours worked should not be more than 24")
    private Integer hoursWorked;

    @NotNull(message = "Driver status should not be null")
    private DriverStatus driverStatus;

    @NotNull(message = "Current city should not be null")
    private String currentCity;

    @NotNull(message = "Truck id should not be null")
    private Integer truckId;

}
