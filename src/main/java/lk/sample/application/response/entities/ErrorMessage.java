package lk.sample.application.response.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class ErrorMessage {
    private String message;
    private String code;
    private String traceId;
    private String type;
    private String origin;
    private String details;
    private String timestamp;
    private Object data;
}
