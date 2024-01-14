package lk.sample.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
public class CommonException extends RuntimeException {
    private final String code;
    private final String type;
    private final HttpStatus status;

    /**
     * @param msg
     * @param code
     * @param type
     * @param status
     */

    public CommonException(String msg, String code, String type, HttpStatus status) {
        super(msg);
        this.code = code;
        this.type = type;
        this.status = status;

    }
}
