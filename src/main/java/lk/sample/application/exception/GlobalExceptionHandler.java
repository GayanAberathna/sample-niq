package lk.sample.application.exception;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import lk.sample.application.exception.vm.ErrorField;
import lk.sample.application.response.entities.ErrorMessage;
import lk.sample.application.util.ErrorUtils;
import lk.sample.application.util.ResponseUtils;
import lk.sample.application.constant.AppConstants;
import lk.sample.application.constant.ErrorConstants;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.NestedServletException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LogManager.getLogger("Error");

    private final ResponseUtils responseUtils;

    public GlobalExceptionHandler(ResponseUtils responseUtils) {
        this.responseUtils = responseUtils;
    }


    /**
     * Catch HttpRequestMethodNotSupportedException
     *
     * @param @{@link HttpRequestMethodNotSupportedException}
     * @param @{@link HttpServletRequest}
     * @return @{@link ErrorMessage}
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorMessage> methodNotAllowedExceptionHandler(HttpRequestMethodNotSupportedException ex,
                                                                         HttpServletRequest request) {
        LOGGER.error("HttpRequestMethodNotSupportedException : traceId={}|{}", request.getHeader(AppConstants.TRACE_ID), ex);

        return responseUtils.wrapErrors(
                Collections.singletonList(
                        ErrorField.of(ErrorConstants.METHOD_NOT_ALLOWED_ERROR_CODE, null, ErrorConstants.METHOD_NOT_ALLOWED_ERROR_MESSAGE)),
                ErrorConstants.METHOD_NOT_ALLOWED_ERROR_CODE,
                ErrorConstants.METHOD_NOT_ALLOWED_ERROR_TYPE,
                ErrorConstants.METHOD_NOT_ALLOWED_ERROR_MESSAGE,
                HttpStatus.METHOD_NOT_ALLOWED);


    }


    /**
     * Catch NullPointerException
     *
     * @param @{@link NullPointerException}
     * @param @{@link HttpServletRequest}
     * @return @{@link ErrorMessage}
     */
    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorMessage> nullPointerExceptionHandler(NullPointerException ex,
                                                                    HttpServletRequest request) {
        LOGGER.error("NullPointerException : traceId={}|{}", request.getHeader(AppConstants.TRACE_ID), ex);
        return responseUtils.wrapErrors(
                Collections.singletonList(ErrorField.of(ErrorConstants.NULL_POINT_ERROR_CODE, null, ErrorConstants.NULL_POINT_ERROR_MESSAGE)),
                ErrorConstants.NULL_POINT_ERROR_CODE,
                ErrorConstants.VALIDATION_ERROR_TYPE,
                ErrorConstants.NULL_POINT_ERROR_MESSAGE,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }


    /**
     * Catch SQLException
     *
     * @param @{@link SQLException}
     * @param @{@link HttpServletRequest}
     * @return @{@link ErrorMessage}
     */
    @ExceptionHandler(SQLException.class)
    public ResponseEntity<ErrorMessage> sqlExceptionHandler(SQLException ex, HttpServletRequest request) {
        LOGGER.error("SQLException : traceId={}|{}", request.getHeader(AppConstants.TRACE_ID), ex);
        return responseUtils.wrapErrors(
                Collections.singletonList(ErrorField.of(ErrorConstants.SQL_EXCEPTION_ERROR_CODE, null, ErrorConstants.SQL_EXCEPTION_ERROR_MESSAGE)),
                ErrorConstants.SQL_EXCEPTION_ERROR_CODE,
                ErrorConstants.DB_ERROR_TYPE,
                ErrorConstants.SQL_EXCEPTION_ERROR_MESSAGE,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Catch NoHandlerFoundException
     *
     * @param @{@link NoHandlerFoundException}
     * @param @{@link HttpServletRequest}
     * @return @{@link ErrorMessage}
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorMessage> noHandlerFoundExceptionExceptionHandler(NoHandlerFoundException ex,
                                                                                HttpServletRequest request) {
        LOGGER.error("NoHandlerFoundException : traceId={}|{}", request.getHeader(AppConstants.TRACE_ID), ex);
        return responseUtils.wrapErrors(
                Collections.singletonList(ErrorField.of(ErrorConstants.INVALID_URL_ERROR_CODE, null, ErrorConstants.NO_HANDLER_EXCEPTION_ERROR_MESSAGE)),
                ErrorConstants.INVALID_URL_ERROR_CODE,
                ErrorConstants.VALIDATION_ERROR_TYPE,
                ErrorConstants.NO_HANDLER_EXCEPTION_ERROR_MESSAGE,
                HttpStatus.NOT_FOUND);
    }

    /**
     * Catch MissingServletRequestParameterException
     *
     * @param @{@link MissingServletRequestParameterException}
     * @param @{@link HttpServletRequest}
     * @return @{@link ErrorMessage}
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorMessage> missingServletRequestParameterExceptionHandler(
            MissingServletRequestParameterException ex, HttpServletRequest request) {
        LOGGER.error("MissingServletRequestParameterException : traceId={}|{}", request.getHeader(AppConstants.TRACE_ID), ex);
        return responseUtils.wrapErrors(
                Collections.singletonList(ErrorField.of(ErrorConstants.NO_HANDLER_EXCEPTION_ERROR_CODE, null, ex.getMessage())),
                ErrorConstants.NO_HANDLER_EXCEPTION_ERROR_CODE,
                ErrorConstants.VALIDATION_ERROR_TYPE,
                ErrorConstants.VALIDATION_FAILURE_MESSAGE,
                HttpStatus.BAD_REQUEST);
    }


    /**
     * Catch NestedServletException
     *
     * @param @{@link NestedServletException}
     * @param @{@link HttpServletRequest}
     * @return @{@link ErrorMessage}
     */
    @ExceptionHandler(NestedServletException.class)
    public ResponseEntity<ErrorMessage> nestedServletExceptionHandler(NestedServletException ex,
                                                                      HttpServletRequest request) {
        LOGGER.error("NestedServletException : traceId={}|{}", request.getHeader(AppConstants.TRACE_ID), ex);
        return responseUtils.wrapErrors(
                Collections.singletonList(ErrorField.of("0002", null, ErrorConstants.DATA_TYPE_MISMATCH_MESSAGE)),
                "0002",
                ErrorConstants.VALIDATION_ERROR_TYPE,
                ErrorConstants.DATA_TYPE_MISMATCH_MESSAGE,
                HttpStatus.BAD_REQUEST);
    }


    /**
     * Catch SQLIntegrityConstraintViolationException
     *
     * @param @{@link SQLIntegrityConstraintViolationException}
     * @param @{@link HttpServletRequest}
     * @return @{@link ErrorMessage}
     */
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> sqLIntegrityConstraintViolationExceptionHandler(
            SQLIntegrityConstraintViolationException ex, HttpServletRequest request) {
        LOGGER
                .error("SQLIntegrityConstraintViolationException : traceId={}|{}", request.getHeader(AppConstants.TRACE_ID), ex);
        return responseUtils.wrapErrors(
                Collections.singletonList(ErrorField.of("0002", null, ex.getMessage())),
                "0002",
                ErrorConstants.DB_ERROR_TYPE,
                ErrorConstants.SQL_EXCEPTION_ERROR_MESSAGE,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Catch MissingRequestHeaderException
     *
     * @param @{@link MissingRequestHeaderException}
     * @param @{@link HttpServletRequest}
     * @return @{@link ErrorMessage}
     */
    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ErrorMessage> missingRequestHeaderExceptionHandler(MissingRequestHeaderException ex,
                                                                             HttpServletRequest request) {
        LOGGER.error("MissingRequestHeaderException : traceId={}|{}", request.getHeader(AppConstants.TRACE_ID), ex);
        return responseUtils.wrapErrors(
                Collections.singletonList(ErrorField.of("0002", null, ex.getMessage())),
                "0002",
                ErrorConstants.VALIDATION_ERROR_TYPE,
                ErrorConstants.MISSING_REQUEST_HEADER_MESSAGE,
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Catch NestedServletException
     *
     * @param @{@link ConstraintViolationException}
     * @param @{@link HttpServletRequest}
     * @return @{@link ErrorMessage}
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorMessage> constraintViolationHandler(ConstraintViolationException ex,
                                                                   HttpServletRequest request) {
        LOGGER.error("ConstraintViolationException : traceId={}|{}", request.getHeader(AppConstants.TRACE_ID), ex);
        Set<ConstraintViolation<?>> constraintViolations = ex.getConstraintViolations();
        List<ErrorField> errorFields = new ArrayList<>();
        constraintViolations.forEach(error -> {
            final String[] mValues;
            if (error.getMessage().contains(":")) {
                mValues = error.getMessage().split(":");
            } else {
                mValues = new String[]{"0001", error.getMessage()};
            }
            ErrorField errorField = new ErrorField();
            errorField.setCode(mValues[0]);
            String path = error.getPropertyPath().toString();
            errorField.setField(path.substring(path.indexOf('.') + 1));
            errorField.setMessage(mValues[1]);
            errorFields.add(errorField);
        });


        return responseUtils.wrapErrors(
                errorFields,
                ErrorConstants.VALIDATION_FAILURE_CODE,
                ErrorConstants.VALIDATION_ERROR_TYPE,
                ErrorConstants.VALIDATION_FAILURE_MESSAGE,
                HttpStatus.BAD_REQUEST);
    }


    /**
     * Catch ResourceNotFoundException
     *
     * @param @{@link ResourceNotFoundException}
     * @param @{@link HttpServletRequest}
     * @param request
     * @return @{@link ErrorMessage}
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFound(ResourceNotFoundException ex,
                                                               HttpHeaders headers,
                                                               HttpStatus status, HttpServletRequest request) {
        return responseUtils.wrapErrors(
                Collections.singletonList(ErrorField.of("0002", null, ex.getMessage())),
                ErrorConstants.RESOURCE_NOT_FOUND_ERROR_CODE,
                ErrorConstants.VALIDATION_ERROR_TYPE,
                ErrorConstants.RESOURCE_NOT_FOUND_MESSAGE,
                HttpStatus.NOT_FOUND);
    }

    /**
     * Catch InsufficientAuthenticationException
     *
     * @param @{@link InsufficientAuthenticationException}
     * @param @{@link HttpServletRequest}
     * @param request
     * @return @{@link ErrorMessage}
     */
    @ExceptionHandler(InsufficientAuthenticationException.class)
    public ResponseEntity<ErrorMessage> handleInsufficientAuthenticationException(InsufficientAuthenticationException ex, HttpServletRequest request) {
        LOGGER.error("InsufficientAuthenticationException : traceId={}|{}", request.getHeader(AppConstants.TRACE_ID), ex);
        final String incorrect_credentials = "Not authorized";
        return responseUtils.wrapErrors(
                Collections.singletonList(ErrorField.of("9999", null, incorrect_credentials)),
                "9999",
                ErrorConstants.VALIDATION_ERROR_TYPE,
                incorrect_credentials,
                HttpStatus.BAD_REQUEST);
    }

/**
     * Catch BadCredentialsException
     *
     * @param @{@link BadCredentialsException}
     * @param @{@link HttpServletRequest}
     * @param request
     * @return @{@link ErrorMessage}
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorMessage> handleBadCredentialsException(BadCredentialsException ex, HttpServletRequest request) {
        LOGGER.error("BadCredentialsException : traceId={}|{}", request.getHeader(AppConstants.TRACE_ID), ex);
        final String incorrect_credentials = "Bad credentials";
        return responseUtils.wrapErrors(
                Collections.singletonList(ErrorField.of("9999", null, incorrect_credentials)),
                "9999",
                ErrorConstants.VALIDATION_ERROR_TYPE,
                incorrect_credentials,
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Catch CommonException
     *
     * @param @{@link CommonException}
     * @param @{@link HttpServletRequest}
     * @return @{@link ErrorMessage}
     */
    @ExceptionHandler(CommonException.class)
    public ResponseEntity<ErrorMessage> commonExceptionHandler(CommonException ex, HttpServletRequest request) {
        LOGGER.error("CommonException : traceId={}|{}", request.getHeader(AppConstants.TRACE_ID), ex);
        String msg;
        if (ex.getMessage().contains(ErrorConstants.TRACE_ID_NULL_MESSAGE)) {
            msg = ErrorConstants.NULL_POINT_ERROR_MESSAGE;
        } else if (ex.getMessage().equals("Invalid Trace ID")) {
            msg = ErrorConstants.VALIDATION_FAILURE_MESSAGE;
        } else {
            msg = ex.getMessage();
        }

        return responseUtils.wrapErrors(
                Collections
                        .singletonList(ErrorField.of(ex.getCode(), msg.contains("Trace ID") ? "TRACE_ID" : null, msg)),
                ex.getCode(),
                ex.getType(),
                msg,
                ex.getStatus());
    }

    /**
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(InvalidTraceIdException.class)
    public ResponseEntity<ErrorMessage> invalidTraceIdExceptionHandler(InvalidTraceIdException ex,
                                                                       HttpServletRequest request) {
        LOGGER.error("InvalidTraceIdException : traceId={}|{}", request.getHeader(AppConstants.TRACE_ID), ex);
        return responseUtils.wrapErrors(
                Collections.singletonList(ErrorField.of("0002", "TRACE_ID", ex.getMessage())),
                ErrorConstants.INVALID_DATA_FORMAT_ERROR_CODE,
                ErrorConstants.VALIDATION_ERROR_TYPE,
                ErrorConstants.INVALID_TRACE_ID_MESSAGE,
                HttpStatus.BAD_REQUEST);

    }

    /**
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(NullTraceIdException.class)
    public ResponseEntity<ErrorMessage> nullTraceIdExceptionHandler(NullTraceIdException ex,
                                                                    HttpServletRequest request) {
        LOGGER.error("NullTraceIdException : traceId={}|orderId={}|{}", request.getHeader(AppConstants.TRACE_ID),
                request.getHeader("TRACE_ID"), ex);
        return responseUtils.wrapErrors(
                Collections.singletonList(
                        ErrorField.of(ErrorConstants.MANDATORY_FIELD_MISSING_CODE, "TRACE_ID", ErrorConstants.NULL_TRACE_ID_EXCEPTION_ERROR_MESSAGE)),
                ErrorConstants.MANDATORY_FIELD_MISSING_CODE,
                ErrorConstants.VALIDATION_ERROR_TYPE,
                ErrorConstants.NULL_TRACE_ID_EXCEPTION_ERROR_MESSAGE,
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Catch MethodArgumentNotValidException
     *
     * @param @{@link MethodArgumentNotValidException}
     * @param @{@link HttpServletRequest}
     * @return @{@link ErrorMessage}
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessage> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex,
                                                                               HttpServletRequest request) {
        LOGGER.error("MethodArgumentNotValidException : traceId={}|{}", request.getHeader(AppConstants.TRACE_ID), ex);
        List<ErrorField> errorFields = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            String defaultMessage = error.getDefaultMessage();
            if (Objects.isNull(defaultMessage)) {
                break;
            }
            final String[] mValues = defaultMessage.split(":");
            ErrorField errorField = new ErrorField();
            errorField.setCode(mValues.length > 0 ? mValues[0] : ErrorConstants.VALIDATION_ERROR_CODE);
            errorField.setField(error.getField());
            errorField.setMessage(mValues.length > 1 ? mValues[1] : defaultMessage);
            errorFields.add(errorField);
        }
        return responseUtils.wrapErrors(
                errorFields,
                ErrorConstants.VALIDATION_FAILURE_CODE,
                ErrorConstants.VALIDATION_ERROR_TYPE,
                ErrorConstants.VALIDATION_FAILURE_MESSAGE,
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Catch HttpMessageNotReadableException
     *
     * @param @{@link HttpMessageNotReadableException}
     * @param @{@link HttpServletRequest}
     * @return @{@link ErrorMessage}
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorMessage> httpMessageNotReadableExceptionHandler(@NonNull HttpMessageNotReadableException ex,
                                                                               HttpServletRequest request) {
        LOGGER.error("HttpMessageNotReadableException : traceId={}| {}", request.getHeader(AppConstants.TRACE_ID), ex);

        final String message = Optional.ofNullable(ex.getMessage()).orElse("");
        String[] errors =  message.split(";");
        String msg = errors.length > 1 ? errors[0] : ErrorConstants.MESSAGE_NOT_READABLE_MESSAGE;
        if (msg.contains("`java.time.LocalDateTime`")) {
            msg = msg.split(": Failed to deserialize java.time.LocalDateTime")[0];
        }
        return responseUtils.wrapErrors(
                Collections.singletonList(
                        ErrorField.of("0002", null, msg)),
                "0002",
                ErrorConstants.VALIDATION_ERROR_TYPE,
                ErrorConstants.VALIDATION_FAILURE_MESSAGE,
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Catch MismatchedInputException
     *
     * @param @{@link MismatchedInputException}
     * @param @{@link HttpServletRequest}
     * @return @{@link ErrorMessage}
     */
    @ExceptionHandler(MismatchedInputException.class)
    public ResponseEntity<ErrorMessage> mismatchedInputExceptionHandler(MismatchedInputException ex,
                                                                        HttpServletRequest request) {
        LOGGER.error("MismatchedInputException : traceId={}|{}", request.getHeader(AppConstants.TRACE_ID), ex);

        String msg = ex.getOriginalMessage().split(": Failed to deserialize java.time.LocalDateTime:")[0];
        return responseUtils.wrapErrors(
                Collections.singletonList(ErrorField.of(ErrorConstants.INVALID_DATA_FORMAT_ERROR_CODE, null, msg)),
                ErrorConstants.VALIDATION_FAILURE_CODE,
                ErrorConstants.VALIDATION_ERROR_TYPE,
                ErrorConstants.VALIDATION_FAILURE_MESSAGE,
                HttpStatus.BAD_REQUEST);
    }

    /**
     * Catch Global Exceptions
     *
     * @param @{@link Exception}
     * @param @{@link HttpServletRequest}
     * @return @{@link ErrorMessage}
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> globalExceptionHandler(Exception ex, HttpServletRequest request) {
        LOGGER.error("Exception : traceId={}|{}", request.getHeader(AppConstants.TRACE_ID), ex);
        return responseUtils.wrapErrors(
                Collections.singletonList(
                        ErrorField.of(ErrorConstants.GLOBAL_ERROR_CODE, null, ErrorConstants.GLOBAL_ERROR_MESSAGE)),
                ErrorConstants.GLOBAL_ERROR_CODE,
                ErrorConstants.GLOBAL_ERROR_TYPE,
                ErrorConstants.GLOBAL_ERROR_MESSAGE,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomConstraintValidation.class)
    public ResponseEntity<ErrorMessage> customConstraintValidation(CustomConstraintValidation ex, HttpServletRequest request) {
        LOGGER.error("customConstraintValidation : traceId={}", request.getHeader(AppConstants.TRACE_ID), ex);

        List<ErrorField> errorFields = ErrorUtils.getValidationErrors(ex);

        return responseUtils.wrapErrors(
                errorFields,
                ErrorConstants.VALIDATION_ERROR_CODE,
                ErrorConstants.VALIDATION_ERROR_TYPE,
                ex.getMessage(),
                HttpStatus.BAD_REQUEST);
    }


}
