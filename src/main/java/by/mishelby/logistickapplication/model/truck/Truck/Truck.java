package by.mishelby.logistickapplication.model.truck.Truck;

import by.mishelby.logistickapplication.model.driver.Driver;
import by.mishelby.logistickapplication.model.truck.TruckStatus.TruckStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "truck")
public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "truck_id", updatable = false, nullable = false)
    private BigInteger id;

    @Column(name = "registration_number", unique = true)
    @Pattern(regexp = "[A-Z]{2}\\d{5}", message = "Invalid registration number format")
    private String registrationNumber;

    @Column(name = "drivers_shift_hours")
    @NotNull(message = "Drivers shift hours should not be null")
    private Integer driversShiftHours;

    @Column(name = "capacity")
    @NotNull(message = "Truck capacity should not be null")
    private Integer capacity;

    @Enumerated(EnumType.STRING)
    @Column(name = "truck_status")
    @NotNull(message = "Truck status should not be null")
    private TruckStatus truckStatus;

    @Column(name = "current_city", nullable = false)
    private String currentCity;

    @OneToMany(mappedBy = "currentTruck", cascade = CascadeType.ALL)
    private List<Driver> driver;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Truck truck = (Truck) o;
        return Objects.equals(id, truck.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}

