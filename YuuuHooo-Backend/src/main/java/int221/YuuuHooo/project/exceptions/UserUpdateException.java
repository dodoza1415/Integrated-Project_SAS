package int221.YuuuHooo.project.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

@Setter
@Getter
public class UserUpdateException extends RuntimeException{
    private final ErrorResponse errorResponse;

    public UserUpdateException(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

}
