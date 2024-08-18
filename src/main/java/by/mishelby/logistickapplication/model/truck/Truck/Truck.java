package by.mishelby.logistickapplication.model.truck.Truck;

import by.mishelby.logistickapplication.model.driver.Driver;
import by.mishelby.logistickapplication.model.truck.TruckStatus.TruckStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Truck")
public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "registration_number")
    @Pattern(regexp = "[A-Z]{2}\\d{5}")
    private String registrationNumber;

    @Column(name = "drivers_shift_hours")
    @NotBlank(message = "Drivers shift hours should not be a null or empty")
    private Integer driversShiftHours;

    @Column(name = "capacity")
    @NotBlank(message = "Truck capacity should not be a null or empty")
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "truck_status")
    @NotBlank(message = "Truck status should not be an empty or null")
    private TruckStatus truckStatus;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    @ToString.Exclude
    @JsonBackReference
    private Driver driver;

}
