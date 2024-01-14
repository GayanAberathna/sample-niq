package lk.sample.application.util;

import lk.sample.application.response.entities.ErrorMessage;
import lk.sample.application.response.entities.SuccessMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Date;


@Component
public class ResponseUtils {
    public static final String CONFLUENCE_URL = "https://sample-application.lk/display/Error+Codes";
    private static final String SUCCESS_MESSAGE = "success";
    private static final String ERROR = "error";
    private static final String SUCCESS = "0000";
    private static final String TRACE_ID = "traceId";
    private final HttpServletRequest servletRequest;

    public ResponseUtils(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public ResponseEntity<SuccessMessage> wrapSuccess(Object value, HttpStatus httpStatus) {
        SuccessMessage successMessage = SuccessMessage
                .builder()
                .code(SUCCESS)
                .traceId(servletRequest.getHeader(TRACE_ID))
                .data(value)
                .message(SUCCESS_MESSAGE)
                .timestamp(DateTimeUtils.format(new Date()))
                .build();
        return ResponseEntity.status(httpStatus).body(successMessage);
    }

    public ResponseEntity<ErrorMessage> wrapErrors(Object value, String errorCode, String type, String globalMessage, HttpStatus httpStatus) {
        ErrorMessage errorMessage = ErrorMessage
                .builder()
                .code(errorCode)
                .type(type)
                .origin(servletRequest.getRequestURI())
                .traceId(servletRequest.getHeader(TRACE_ID))
                .message(globalMessage)
                .data(Collections.singletonMap(ERROR, value))
                .details(CONFLUENCE_URL)
                .timestamp(DateTimeUtils.format(new Date()))
                .build();
        return ResponseEntity.status(httpStatus).body(errorMessage);
    }

    public ResponseEntity<ErrorMessage> wrapEventErrors(Object value, String traceId, String errorCode, String type, String globalMessage, HttpStatus httpStatus) {
        ErrorMessage errorMessage = ErrorMessage
                .builder()
                .code(errorCode)
                .type(type)
                .traceId(traceId)
                .message(globalMessage)
                .data(Collections.singletonMap(ERROR, value))
                .details(CONFLUENCE_URL)
                .timestamp(DateTimeUtils.format(new Date()))
                .build();
        return ResponseEntity.status(httpStatus).body(errorMessage);
    }



}
