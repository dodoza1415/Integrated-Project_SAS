package int221.YuuuHooo.project.advice;

import int221.YuuuHooo.project.exceptions.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandlerInput {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErrorResponse>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){

//        ErrorResponse errorResponse = new ErrorResponse();
//        ex.getBindingResult().getFieldErrors().forEach(error -> {
//            ErrorResponse errorResponse = new ErrorResponse(error.getField(), error.getDefaultMessage());
//
//        });
//        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);

        List<ErrorResponse> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> new ErrorResponse(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());


        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fieldErrors);
    }
}
