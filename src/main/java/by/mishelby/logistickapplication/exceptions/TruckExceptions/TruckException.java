package by.mishelby.logistickapplication.exceptions.TruckExceptions;

public class TruckException extends RuntimeException {
    public TruckException() {
    }

    public TruckException(Throwable cause) {
        super(cause);
    }

    public TruckException(String message) {
        super(message);
    }
}
