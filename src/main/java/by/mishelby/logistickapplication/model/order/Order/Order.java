package by.mishelby.logistickapplication.model.order.Order;

import by.mishelby.logistickapplication.model.driver.Driver;
import by.mishelby.logistickapplication.model.order.OrderStatus.OrderStatus;
import by.mishelby.logistickapplication.model.route_point.RoutePoint.RoutePoint;
import by.mishelby.logistickapplication.model.truck.Truck.Truck;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", updatable = false, nullable = false)
    private BigInteger id;

    @Column(name = "unique_number", unique = true)
    private String uniqueNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private OrderStatus status;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RoutePoint> routePoints;

    @ManyToOne
    @JoinColumn(name = "truck_id", referencedColumnName = "truck_id")
    private Truck truck;

    @ManyToMany
    @JoinTable(
            name = "order_driver",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "driver_id")
    )
    private List<Driver> drivers;
}
