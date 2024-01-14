package lk.sample.application.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AppConstants {

    public static final String REQUESTED_USER = "requestedUser";
    public static final String FALSE = "false";
    public static final String TRUE = "true";
    public static final int CONTRACT_SUCCESS_HTTP_STATUS = 200;
    public static final int CONTRACT_CREATED_HTTP_STATUS = 201;
    public static final String TRACE_ID = "trace-Id";

}
