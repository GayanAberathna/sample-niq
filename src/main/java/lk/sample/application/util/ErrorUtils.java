package lk.sample.application.util;

import lk.sample.application.exception.CustomConstraintValidation;
import lk.sample.application.exception.vm.ErrorField;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorUtils {
    public static List<ErrorField>  getValidationErrors(CustomConstraintValidation ex){
        Map<String, String> validationErrors=ex.getErrors();

        List<ErrorField> errorFields = new ArrayList<>();
        validationErrors.forEach((key, value) -> {
            ErrorField errorField = new ErrorField();
            errorField.setCode("0001");
            errorField.setField(key);
            errorField.setMessage(value);
            errorFields.add(errorField);
        });
        return errorFields;
    }
}
