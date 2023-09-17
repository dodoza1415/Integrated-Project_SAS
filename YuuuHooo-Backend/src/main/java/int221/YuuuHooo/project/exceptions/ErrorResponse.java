package int221.YuuuHooo.project.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {

    private final String field;
    private final String errorMessage;

//    private List<ValidationError> errors;
//
//    @Getter
//    @Setter
//    @RequiredArgsConstructor
//    private class ValidationError{
//        private final String field;
//        private final String errorMessage;
//    }
//
//    public void addValidationError(String field, String message){
//        if(Objects.isNull(errors)){
//            errors = new ArrayList<>();
//        }
//        errors.add(new ValidationError(field, message));
//    }
}
