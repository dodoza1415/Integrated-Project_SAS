package int221.nw2.project.yuuuhooo.exceptions;

import lombok.Getter;
import lombok.Setter;

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
