package lk.sample.application.response.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class SuccessMessage {
    private String code;
    private String message;
    private String traceId;
    private String timestamp;
    private Object data;
}
