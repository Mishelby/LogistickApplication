package by.mishelby.logistickapplication.exceptions.CargoException;

public class CargoException extends RuntimeException {
    public CargoException() {
    }

    public CargoException(Throwable cause) {
        super(cause);
    }

    public CargoException(String message) {
        super(message);
    }

    public CargoException(String message, Throwable cause) {
        super(message, cause);
    }

    public CargoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
