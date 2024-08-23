package by.mishelby.logistickapplication.exceptions.OrderException;

public class OrderException extends RuntimeException {
    public OrderException() {
    }

    public OrderException(Throwable cause) {
        super(cause);
    }

    public OrderException(String message) {
        super(message);
    }
}
