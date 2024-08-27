package by.mishelby.logistickapplication.service.OrderService;

import by.mishelby.logistickapplication.domain.CargoDTO.CargoDTO;
import by.mishelby.logistickapplication.domain.DriverDTO.DriverDTO;
import by.mishelby.logistickapplication.domain.OrderDTO.CreateOrderDTO;
import by.mishelby.logistickapplication.domain.OrderDTO.OrderDTO;
import by.mishelby.logistickapplication.domain.OrderDTO.OrderUpdateDTO;
import by.mishelby.logistickapplication.domain.RoutePointDTO;
import by.mishelby.logistickapplication.domain.TruckDTO.TruckDTO;
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
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    private final CargoDAO cargoDAO;

    @Override
    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        if (orderList.isEmpty()) {
            log.info("No orders found");
            return Collections.emptyList();
        }
        return orderList;
    }

    @Override
    @Transactional(readOnly = true)
    public Order findById(int orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(()
                -> new RuntimeException("Order not found"));

        Hibernate.initialize(order.getTruck());
        Hibernate.initialize(order.getDriver());
        Hibernate.initialize(order.getCargo());
        Hibernate.initialize(order.getRoutePoints());

        for (RoutePoint routePoint : order.getRoutePoints()) {
            Hibernate.initialize(routePoint.getCity());
        }

        return order;
    }

    @Override
    public Order createOrder(CreateOrderDTO createOrderDTO) {

        if (createOrderDTO == null) {
            throw new IllegalArgumentException("createOrderDTO cannot be null");
        }

        Truck truck = truckDAO.findTruckById(createOrderDTO.getTruckId());
        Driver driver = driverDAO.findById(createOrderDTO.getDriverId());
        City city = cityDAO.findByName(createOrderDTO.getCityName());

        RoutePoint routePoint = new RoutePoint();
        Cargo cargo = new Cargo();

        cargo.setCargoStatus(createOrderDTO.getCargoCreateDTO().getCargoStatus());
        cargo.setNumber(createOrderDTO.getCargoCreateDTO().getNumber());
        cargo.setWeight(createOrderDTO.getCargoCreateDTO().getWeight());
        cargo.setDescription(createOrderDTO.getCargoCreateDTO().getDescription());
        cargo.setRoutePoint(routePoint);

        routePoint.setCity(city);
        routePoint.setType(createOrderDTO.getType());
        routePoint.setCargo(new ArrayList<>());
        routePoint.setCargo(new ArrayList<>(Collections.singletonList(cargo)));
        routePoint.setOrder(null);

        routePointsDAO.createRoutePoint(routePoint);
        cargoDAO.saveCargo(cargo);

        Order order = new Order();
        order.setStatus(createOrderDTO.getStatus());
        order.setUniqueNumber(createOrderDTO.getUniqueNumber());
        order.setStatus(createOrderDTO.getStatus());
        order.setTruck(truck);
        order.setDriver(driver);
        order.setRoutePoints(new ArrayList<>(Collections.singletonList(routePoint)));
        order.setCargo(cargo);
        order.getRoutePoints().add(routePoint);


        return orderRepository.save(order);
    }

    @Override
    public OrderDTO convertToDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId(order.getId());
        orderDTO.setUniqueNumber(order.getUniqueNumber());
        orderDTO.setStatus(order.getStatus());

        if (order.getTruck() != null) {
            orderDTO.setTruckId(order.getTruck().getId());
        }

        if (order.getDriver() != null) {
            orderDTO.setDriverId(order.getDriver().getId());
        }

        if (order.getCargo() != null) {
            orderDTO.setCargoId(order.getCargo().getId());
        }

        if (order.getRoutePoints() != null) {
            List<BigInteger> routePointIds = order.getRoutePoints()
                    .stream()
                    .map(RoutePoint::getId)
                    .toList();
            orderDTO.setRoutePoints(routePointIds);
        }

        return orderDTO;
    }


    @Override
    public void updateOrder(OrderUpdateDTO orderUpdateDTO) {
        if (orderUpdateDTO == null) {
            throw new IllegalArgumentException("orderUpdateDTO cannot be null");
        }
        Order updateOrder = orderMapper.orderUpdateToOrder(orderUpdateDTO);
        orderRepository.save(updateOrder);

    }

    public void deleteOrder() {
        deleteOrder(0);
    }

    @Override
    public void deleteOrder(int orderId) {
        Order order = this.findById(orderId);
        orderRepository.delete(order);
    }


}
