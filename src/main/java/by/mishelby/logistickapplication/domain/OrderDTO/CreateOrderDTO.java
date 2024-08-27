package by.mishelby.logistickapplication.domain.OrderDTO;

import by.mishelby.logistickapplication.domain.CargoDTO.CargoCreateDTO;
import by.mishelby.logistickapplication.model.cargoStatus.CargoStatus;
import by.mishelby.logistickapplication.model.city.City;
import by.mishelby.logistickapplication.model.driver.Driver;
import by.mishelby.logistickapplication.model.order.OrderStatus.OrderStatus;
import by.mishelby.logistickapplication.model.route_point.RoutePoint.RoutePoint;
import by.mishelby.logistickapplication.model.route_point.RoutePointType.RoutePointType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderDTO {
    @NotNull(message = "Unique number should not be a null")
    private String uniqueNumber;

    @NotNull(message = "Order status should not be a null")
    private OrderStatus status;

    @NotNull(message = "Truck should not be a null")
    private Integer truckId;

    @NotNull(message = "Driver should not be a null")
    private Integer driverId;

    @NotNull(message = "City should not be null")
    private String cityName;

    @NotNull(message = "Route Point Type  should not be null")
    private RoutePointType type;

    @NotNull(message = "Cargo number should not be null")
    private CargoCreateDTO cargoCreateDTO;
}
