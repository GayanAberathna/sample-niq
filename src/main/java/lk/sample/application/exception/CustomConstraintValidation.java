package lk.sample.application.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ResponseStatus(HttpStatus.BAD_REQUEST)
@Getter
public class CustomConstraintValidation extends RuntimeException{

    private final Map<String, String> errors;

    public CustomConstraintValidation(String message, Map<String, String> errors) {

        super(message);
        this.errors = errors;
    }
}
