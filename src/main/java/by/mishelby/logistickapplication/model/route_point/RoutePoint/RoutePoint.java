package by.mishelby.logistickapplication.model.route_point.RoutePoint;

import by.mishelby.logistickapplication.model.city.City;
import by.mishelby.logistickapplication.model.order.Order.Order;
import by.mishelby.logistickapplication.model.route_point.RoutePointType.RoutePointType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoutePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id", unique = true, nullable = false)
    private BigInteger id;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    private City city;

    @Column(name = "cargo", nullable = false)
    private String cargo;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private RoutePointType type;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @JsonBackReference
    private Order order;
}

