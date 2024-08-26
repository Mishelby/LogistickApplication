package by.mishelby.logistickapplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoChangesDetectedException extends RuntimeException {
    public NoChangesDetectedException(String message) {
        super(message);
    }
}
