package by.mishelby.logistickapplication.model.cargo;

import by.mishelby.logistickapplication.model.cargoStatus.CargoStatus;
import by.mishelby.logistickapplication.model.order.Order.Order;
import by.mishelby.logistickapplication.model.route_point.RoutePoint.RoutePoint;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Entity
@Data
@Table(name = "cargo")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cargo_id", unique = true, nullable = false)
    private BigInteger id;

    @Column(name = "number")
    @NotNull(message = "Cargo number should not be null")
    private Integer number;

    @Column(name = "description")
    @NotNull(message = "Description should not be null")
    private String description;

    @Column(name = "weight")
    @NotNull(message = "Weight should not be an empty or null")
    private Integer weight;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull(message = "Cargo status should not be an empty or null")
    private CargoStatus cargoStatus;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id", referencedColumnName = "route_id")
    private RoutePoint routePoint;

    @OneToMany(mappedBy = "cargo",cascade = CascadeType.ALL)
    private List<Order> order;

}
