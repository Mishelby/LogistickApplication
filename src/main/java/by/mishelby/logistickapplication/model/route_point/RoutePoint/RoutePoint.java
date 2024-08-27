package by.mishelby.logistickapplication.model.route_point.RoutePoint;

import by.mishelby.logistickapplication.model.cargo.Cargo;
import by.mishelby.logistickapplication.model.city.City;
import by.mishelby.logistickapplication.model.order.Order.Order;
import by.mishelby.logistickapplication.model.route_point.RoutePointType.RoutePointType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Builder
@Table(name = "route_point")
@AllArgsConstructor
@NoArgsConstructor
public class RoutePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id", unique = true, nullable = false)
    private BigInteger id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    private City city;

    @OneToMany(mappedBy = "routePoint", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Cargo> cargo;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private RoutePointType type;

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id")
    @JsonBackReference
    private Order order;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoutePoint that = (RoutePoint) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}

