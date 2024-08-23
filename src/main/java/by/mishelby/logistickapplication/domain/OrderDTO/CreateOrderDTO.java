package by.mishelby.logistickapplication.domain.OrderDTO;

import by.mishelby.logistickapplication.model.driver.Driver;
import by.mishelby.logistickapplication.model.order.OrderStatus.OrderStatus;
import by.mishelby.logistickapplication.model.route_point.RoutePoint.RoutePoint;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDTO {
    @NotNull(message = "Unique number should not be a null")
    private String uniqueNumber;

    @NotNull(message = "Order status should not be a null")
    private OrderStatus status;

    @NotNull(message = "Route point should not be a null")
    private List<RoutePoint> routePoints;

    @NotNull(message = "Truck should not be a null")
    private Integer id;

    @NotNull(message = "Driver should not be a null")
    private List<Driver> drivers;
}
