package int221.YuuuHooo.project.advice;

import int221.YuuuHooo.project.exceptions.ErrorResponse;
import int221.YuuuHooo.project.exceptions.ErrorResponseWrapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class HandleValidationExceptions {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseWrapper> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        List<ErrorResponse> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorResponse(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());
        ErrorResponseWrapper wrapper = new ErrorResponseWrapper(fieldErrors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(wrapper);
    }

}
