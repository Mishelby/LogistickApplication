package by.mishelby.logistickapplication.model.order.Order;

import by.mishelby.logistickapplication.model.cargo.Cargo;
import by.mishelby.logistickapplication.model.driver.Driver;
import by.mishelby.logistickapplication.model.order.OrderStatus.OrderStatus;
import by.mishelby.logistickapplication.model.route_point.RoutePoint.RoutePoint;
import by.mishelby.logistickapplication.model.truck.Truck.Truck;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", updatable = false, nullable = false)
    private BigInteger id;

    @Column(name = "unique_number", unique = true)
    @NotNull(message = "Unique number should not be a null")
    private String uniqueNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull(message = "Order status should not be a null")
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @NotNull(message = "Route point should not be a null")
    @ToString.Exclude
    private List<RoutePoint> routePoints;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "truck_id", referencedColumnName = "truck_id")
    @NotNull(message = "Truck should not be a null")
    @ToString.Exclude
    private Truck truck;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", referencedColumnName = "driver_id")
    @NotNull(message = "Driver should not be a null")
    @ToString.Exclude
    private Driver driver;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cargo_id", referencedColumnName = "cargo_id")
    @ToString.Exclude
    private Cargo cargo;
}
