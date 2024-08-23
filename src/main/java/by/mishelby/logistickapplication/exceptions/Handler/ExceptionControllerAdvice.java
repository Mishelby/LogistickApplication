package by.mishelby.logistickapplication.exceptions.Handler;

import by.mishelby.logistickapplication.exceptions.CargoException.CargoException;
import by.mishelby.logistickapplication.exceptions.DriverException.DriverDTOException;
import by.mishelby.logistickapplication.exceptions.ErrorResponce.ErrorResponse;
import by.mishelby.logistickapplication.exceptions.OrderException.OrderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public final class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception e) {
        var errorResponse = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DriverDTOException.class)
    public ResponseEntity<ErrorResponse> driverDTOException(DriverDTOException e) {
        var errorResponse = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<ErrorResponse> orderException(OrderException e) {
        var errorResponse = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CargoException.class)
    public ResponseEntity<ErrorResponse> cargoException(CargoException e) {
        var errorResponse = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    public static ErrorResponse getErrorResponse(BindingResult bindingResult) {
        StringBuilder errorMessage = new StringBuilder();

        bindingResult.getFieldErrors().forEach(error ->
                errorMessage.append(error.getField())
                        .append(": ")
                        .append(error.getDefaultMessage())
                        .append("; "));

        return new ErrorResponse(errorMessage.toString(), System.currentTimeMillis());
    }

}
