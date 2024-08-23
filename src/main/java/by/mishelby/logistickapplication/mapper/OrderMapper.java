package by.mishelby.logistickapplication.mapper;

import by.mishelby.logistickapplication.domain.OrderDTO.CreateOrderDTO;
import by.mishelby.logistickapplication.domain.OrderDTO.OrderUpdateDTO;
import by.mishelby.logistickapplication.model.order.Order.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order createOrderToOrder(CreateOrderDTO createOrderDTO);
    Order orderUpdateToOrder(OrderUpdateDTO orderUpdateDTO);
}
