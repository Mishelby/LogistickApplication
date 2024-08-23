package by.mishelby.logistickapplication.controller.Rest;

import by.mishelby.logistickapplication.domain.OrderDTO.CreateOrderDTO;
import by.mishelby.logistickapplication.exceptions.Handler.ExceptionControllerAdvice;
import by.mishelby.logistickapplication.model.order.Order.Order;
import by.mishelby.logistickapplication.service.OrderService.OrderDAO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderDAO orderDAO;

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody @Valid CreateOrderDTO createOrderDTO,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            var errorResponse
                    = ExceptionControllerAdvice.getErrorResponse(bindingResult);

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, errorResponse.getMessage());
        }
        Order order = orderDAO.createOrder(createOrderDTO);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
}
