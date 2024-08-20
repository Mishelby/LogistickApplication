package by.mishelby.logistickapplication.exceptions.Handler;

import by.mishelby.logistickapplication.exceptions.DriverException.DriverDTOException;
import by.mishelby.logistickapplication.exceptions.ErrorResponce.ErrorResponse;
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
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DriverDTOException.class)
    public ResponseEntity<ErrorResponse> driverDTOException(DriverDTOException e) {
        var errorResponse = new ErrorResponse(e.getMessage(), System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
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
