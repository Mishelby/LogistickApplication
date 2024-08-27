package by.mishelby.logistickapplication.domain;

import by.mishelby.logistickapplication.model.cargo.Cargo;
import by.mishelby.logistickapplication.model.city.City;
import by.mishelby.logistickapplication.model.order.Order.Order;
import by.mishelby.logistickapplication.model.route_point.RoutePointType.RoutePointType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoutePointDTO {
    private BigInteger id;

    private City city;

    private List<Cargo> cargo;

    private RoutePointType type;

    private Order order;
}
