package by.mishelby.logistickapplication.domain.DriverDTO;

import by.mishelby.logistickapplication.model.driver.DriverStatus.DriverStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigInteger;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DriverDTO {
    private BigInteger id;

    @NotBlank(message = "First name should not be null or blank")
    private String firstName;

    @NotBlank(message = "Last name should not be null or blank")
    private String lastName;

    @NotBlank(message = "Personal number should not be null or blank")
    @Size(min = 10, max = 15, message = "Personal number should be between 10 and 15")
    private String personalNumber;

    private DriverStatus driverStatus;

    @NotBlank(message = "Current city  should not be null or blank")
    private String currentCity;

}
