package by.mishelby.logistickapplication.service.OrderService;

import by.mishelby.logistickapplication.domain.OrderDTO.CreateOrderDTO;
import by.mishelby.logistickapplication.domain.OrderDTO.OrderUpdateDTO;
import by.mishelby.logistickapplication.mapper.CargoMapper;
import by.mishelby.logistickapplication.mapper.CityMapper;
import by.mishelby.logistickapplication.mapper.OrderMapper;
import by.mishelby.logistickapplication.model.cargo.Cargo;
import by.mishelby.logistickapplication.model.city.City;
import by.mishelby.logistickapplication.model.driver.Driver;
import by.mishelby.logistickapplication.model.order.Order.Order;
import by.mishelby.logistickapplication.model.route_point.RoutePoint.RoutePoint;
import by.mishelby.logistickapplication.model.truck.Truck.Truck;
import by.mishelby.logistickapplication.repository.OrderRepository.OrderRepository;
import by.mishelby.logistickapplication.service.CargoService.CargoDAO;
import by.mishelby.logistickapplication.service.CityService.CityDAO;
import by.mishelby.logistickapplication.service.DriverService.DriverDAO;
import by.mishelby.logistickapplication.service.RoutePointsService.RoutePointsDAO;
import by.mishelby.logistickapplication.service.TruckService.TruckDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OrderDAO implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final TruckDAO truckDAO;
    private final DriverDAO driverDAO;
    private final RoutePointsDAO routePointsDAO;
    private final CityDAO cityDAO;
    private final CityMapper cityMapper;
    private final CargoDAO cargoDAO;
    private final CargoMapper cargoMapper;

    @Override
    public List<Order> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        if (orderList.isEmpty()) {
            log.info("No orders found");
            return Collections.emptyList();
        }
        return orderList;
    }

    @Override
    public Order createOrder(CreateOrderDTO createOrderDTO) {

        if (createOrderDTO == null) {
            throw new IllegalArgumentException("createOrderDTO cannot be null");
        }

        Order createOrder = orderMapper.createOrderToOrder(createOrderDTO);
        Truck truck = truckDAO.findTruckById(createOrderDTO.getId());
        createOrder.setTruck(truck);

        List<RoutePoint> routePoints = new ArrayList<>();
        for (RoutePoint rPoint : createOrder.getRoutePoints()) {
            City city = cityDAO.findById(rPoint.getCity().getId().intValue());
            List<Cargo> cargos = cargoDAO.finAllCargos();

            RoutePoint routePoint = RoutePoint.builder()
                    .city(rPoint.getCity())
                    .cargo(rPoint.getCargo())
                    .type(rPoint.getType())
                    .order(createOrder)
                    .build();

            routePoints.add(routePoint);
            routePoint.setCargo(cargos);
        }

        List<Driver> drivers = new ArrayList<>();
        for (Driver driver : createOrder.getDrivers()) {
            drivers.add(driverDAO.findById(driver.getId().intValue()));
        }

        createOrder.setDrivers(drivers);
        return orderRepository.save(createOrder);
    }

    @Override
    public Order findById(int orderId) {
        return orderRepository.findById(orderId).orElseThrow(()
                -> new RuntimeException("Order not found"));
    }

    @Override
    public void updateOrder(OrderUpdateDTO orderUpdateDTO) {
        if (orderUpdateDTO == null) {
            throw new IllegalArgumentException("orderUpdateDTO cannot be null");
        }
        Order updateOrder = orderMapper.orderUpdateToOrder(orderUpdateDTO);
        orderRepository.save(updateOrder);

    }

    @Override
    public void deleteOrder(int orderId) {
        Order order = this.findById(orderId);
        orderRepository.delete(order);
    }


}
