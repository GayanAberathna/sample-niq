package lk.sample.application.constant;

import java.net.URI;

public class ErrorConstants {
    /* Error Messages */
    public static final String NULL_POINT_ERROR_MESSAGE = "Null Pointer Exception Occurred";
    public static final String GLOBAL_ERROR_MESSAGE = "Global Exception Occurred";
    public static final String SQL_EXCEPTION_ERROR_MESSAGE = "SQL Exception Occurred";
    public static final String NO_HANDLER_EXCEPTION_ERROR_MESSAGE = "Invalid URL";
    public static final String METHOD_NOT_ALLOWED_ERROR_MESSAGE = "Method Not Allowed";
    public static final String VALIDATION_FAILURE_MESSAGE = "Validation failure(s)";
    public static final String DATA_TYPE_MISMATCH_MESSAGE = "Data type mismatch";
    public static final String RESOURCE_NOT_FOUND_MESSAGE = "Resource not found";
    public static final String MISSING_REQUEST_HEADER_MESSAGE = "Missing request header";
    public static final String TRACE_ID_NULL_MESSAGE = "Trace ID can not be null";
    public static final String INVALID_TRACE_ID_MESSAGE = "Invalid trace id";
    public static final String NULL_TRACE_ID_EXCEPTION_ERROR_MESSAGE = "Null Trace ID Exception Occurred";
    public static final String MESSAGE_NOT_READABLE_MESSAGE = "Http message not readable";
    /* Error Types */
    public static final String GLOBAL_ERROR_TYPE = "Global";
    public static final String VALIDATION_ERROR_TYPE = "Validation";
    public static final String DB_ERROR_TYPE = "DB";
    public static final String METHOD_NOT_ALLOWED_ERROR_TYPE = "Not Allowed";

    /* Error Codes */
    public static final String VALIDATION_ERROR_CODE = "0001";
    public static final String NULL_POINT_ERROR_CODE = "0001";
    public static final String INVALID_URL_ERROR_CODE = "0001";
    public static final String NO_HANDLER_EXCEPTION_ERROR_CODE = "0002";
    public static final String INVALID_DATA_FORMAT_ERROR_CODE = "0002";
    public static final String METHOD_NOT_ALLOWED_ERROR_CODE = "0005";
    public static final String GLOBAL_ERROR_CODE = "0006";
    public static final String SQL_EXCEPTION_ERROR_CODE = "0007";
    public static final String RESOURCE_NOT_FOUND_ERROR_CODE = "0002";
    public static final String MANDATORY_FIELD_MISSING_CODE = "0001";
    public static final String VALIDATION_FAILURE_CODE = "9000";

    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    public static final String ERR_VALIDATION = "error.validation";
    public static final String PROBLEM_BASE_URL = "https://localhost:8080/problem";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
    public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL + "/constraint-violation");
    public static final URI INVALID_PASSWORD_TYPE = URI.create(PROBLEM_BASE_URL + "/invalid-password");
    public static final URI LOGIN_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/login-already-used");
    private ErrorConstants() {
    }
}
