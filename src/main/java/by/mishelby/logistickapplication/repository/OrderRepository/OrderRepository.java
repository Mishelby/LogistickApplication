package by.mishelby.logistickapplication.repository.OrderRepository;

import by.mishelby.logistickapplication.model.order.Order.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
