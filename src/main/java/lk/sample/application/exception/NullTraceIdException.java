package lk.sample.application.exception;

public class NullTraceIdException extends RuntimeException {
    public NullTraceIdException() {
        super("Null Trace ID");
    }
}
