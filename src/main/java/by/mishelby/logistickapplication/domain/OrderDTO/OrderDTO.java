package by.mishelby.logistickapplication.domain.OrderDTO;

import by.mishelby.logistickapplication.model.order.OrderStatus.OrderStatus;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class OrderDTO {
    private BigInteger id;
    private String uniqueNumber;
    private OrderStatus status;

    private BigInteger truckId;
    private BigInteger driverId;
    private BigInteger cargoId;
    private List<BigInteger> routePoints;
}
