package by.mishelby.logistickapplication.model.driver;

import by.mishelby.logistickapplication.model.driver.DriverStatus.DriverStatus;
import by.mishelby.logistickapplication.model.truck.Truck.Truck;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigInteger;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id", updatable = false, nullable = false)
    private BigInteger id;

    @Column(name = "first_name")
    @NotBlank(message = "First name should not be null or blank")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Last name should not be null or blank")
    private String lastName;

    @Column(name = "personal_number", unique = true)
    @NotBlank(message = "Personal number should not be null or blank")
    @Size(min = 10, max = 15, message = "Personal number should be between 10 and 15")
    private String personalNumber;

    @Column(name = "hours_worked")
    @Min(value = 0, message = "Hours worked should not be less than 0")
    @Max(value = 24, message = "Hours worked should not be more than 24")
    private Integer hoursWorked;

    @Enumerated(EnumType.STRING)
    @Column(name = "driver_status")
    private DriverStatus driverStatus;

    @Column(name = "current_city")
    @NotBlank(message = "Current city  should not be null or blank")
    private String currentCity;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "current_truck_id")
    @JsonIgnoreProperties("driver")
    @ToString.Exclude
    private Truck currentTruck;

}
