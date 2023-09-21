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

    private final int status;
    private final String instance;
    private String stackTrace;
    private List<ValidationError> detail;

    public boolean hasErrors() {
        if(detail != null){
            return true;
        }else{
            return false;
        }
    }

    @Getter
    @Setter
    @RequiredArgsConstructor
    private class ValidationError{
        private final String field;
        private final String errorMessage;

    }

    public void addValidationError(String field, String errorMessage){
        if(Objects.isNull(detail)){
            detail = new ArrayList<>();
        }
        detail.add(new ValidationError(field, errorMessage));
    }



}
