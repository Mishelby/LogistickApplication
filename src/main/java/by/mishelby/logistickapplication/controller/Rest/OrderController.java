package by.mishelby.logistickapplication.controller.Rest;

import by.mishelby.logistickapplication.domain.OrderDTO.CreateOrderDTO;
import by.mishelby.logistickapplication.domain.OrderDTO.OrderDTO;
import by.mishelby.logistickapplication.exceptions.Handler.ExceptionControllerAdvice;
import by.mishelby.logistickapplication.model.order.Order.Order;
import by.mishelby.logistickapplication.service.OrderService.OrderDAO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Collection;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {
    private final OrderDAO orderDAO;

    @GetMapping("/orders")
    public ResponseEntity<Iterable<Order>> getAllOrders() {
        Collection<Order> orders = orderDAO.getAllOrders();
        if (orders.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok().body(orders);
    }

    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable int id) {
        Order order = orderDAO.findById(id);
        OrderDTO orderDTO = orderDAO.convertToDTO(order);
        return ResponseEntity.ok().body(orderDTO);
    }

    @PostMapping("/order")
    public ResponseEntity<Order> createOrder(@RequestBody @Valid CreateOrderDTO createOrderDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorResponse
                    = ExceptionControllerAdvice.getErrorResponse(bindingResult);

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorResponse.getMessage());
        }

        Order order = orderDAO.createOrder(createOrderDTO);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(order.getId())
                .toUri();

        return ResponseEntity.created(location).body(order);
    }
}
