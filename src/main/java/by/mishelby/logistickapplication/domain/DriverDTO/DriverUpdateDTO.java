package by.mishelby.logistickapplication.domain.DriverDTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DriverUpdateDTO {
    @NotNull(message = "Driver id should not be a null")
    private Integer id;

    @NotNull(message = "First name should not be null or blank")
    private String firstName;

    @NotNull(message = "Last name should not be null or blank")
    private String lastName;

    @NotNull(message = "Personal number should not be null or blank")
    @Size(min = 10, max = 15, message = "Personal number should be between 10 and 15")
    private String personalNumber;

    @NotNull(message = "Current city  should not be null or blank")
    private String currentCity;

    @NotNull(message = "New truck should not be a null")
    private Integer newTruck;
}
