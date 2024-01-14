package lk.sample.application.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class InvalidTraceIdException extends RuntimeException {
    public InvalidTraceIdException(String message) {
        super(message);
    }
}
