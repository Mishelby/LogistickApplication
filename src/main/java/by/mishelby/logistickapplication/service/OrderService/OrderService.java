package by.mishelby.logistickapplication.service.OrderService;


import by.mishelby.logistickapplication.domain.OrderDTO.CreateOrderDTO;
import by.mishelby.logistickapplication.domain.OrderDTO.OrderUpdateDTO;
import by.mishelby.logistickapplication.model.order.Order.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();

    Order findById(int orderId);

    Order createOrder(CreateOrderDTO createOrderDTO);

    void updateOrder(OrderUpdateDTO order);

    void deleteOrder(int orderId);
}
