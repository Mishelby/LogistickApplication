package by.mishelby.logistickapplication.model.order.Order;

import by.mishelby.logistickapplication.model.driver.Driver;
import by.mishelby.logistickapplication.model.order.OrderStatus.OrderStatus;
import by.mishelby.logistickapplication.model.route_point.RoutePoint.RoutePoint;
import by.mishelby.logistickapplication.model.truck.Truck.Truck;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private List<RoutePoint> routePoints;

    @ManyToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "truck_id", referencedColumnName = "truck_id")
    @NotNull(message = "Truck should not be a null")
    private Truck truck;

    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.LAZY, targetEntity = Driver.class)
    @JoinTable(
            name = "order_driver",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id")
    )
    @NotNull(message = "Driver should not be a null")
    private List<Driver> drivers;
}
