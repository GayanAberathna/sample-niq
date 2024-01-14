package lk.sample.application.util;

import lk.sample.application.exception.CommonException;
import lk.sample.application.exception.InvalidTraceIdException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;

import static lk.sample.application.constant.AppConstants.REQUESTED_USER;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TraceIdUtils {
    public static void validateTraceId(String traceId) {
        if (traceId == null) {
            throw new InvalidTraceIdException("Trace id can not be null");
        }
        if (!traceId.matches("^[a-zA-Z]{3}\\d{1,12}$")) {
            throw new InvalidTraceIdException("Invalid trace id found");
        }
    }

    public static void validateOriginalUser(String user) {
        if (StringUtils.isEmpty(StringUtils.trim(user))) {
            throw new CommonException(REQUESTED_USER + " cannot be empty", "0001", "Validation",
                                      HttpStatus.BAD_REQUEST);
        }
    }
}
