package int221.nw2.project.yuuuhooo.advice;

import int221.nw2.project.yuuuhooo.exceptions.ErrorResponse;
import int221.nw2.project.yuuuhooo.exceptions.UserUpdateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class HandleValidationExceptions {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex, WebRequest request) {
//        List<ErrorResponse> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
//                .map(error -> new ErrorResponse(error.getField(), error.getDefaultMessage()))
//                .collect(Collectors.toList());
//        ErrorResponseWrapper wrapper = new ErrorResponseWrapper(fieldErrors);

        ErrorResponse er = new ErrorResponse(HttpStatus.BAD_REQUEST.value(), request.getDescription(false).substring(4));
        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            er.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserUpdateException.class)
    public ResponseEntity<ErrorResponse> UserUpdateException(UserUpdateException ex, WebRequest request) {
//        List<ErrorResponse> fieldErrors = ex.getBindingResult().getFieldErrors().stream()
//                .map(error -> new ErrorResponse(error.getField(), error.getDefaultMessage()))
//                .collect(Collectors.toList());
//        ErrorResponseWrapper wrapper = new ErrorResponseWrapper(fieldErrors);

        ErrorResponse er = ex.getErrorResponse();
//        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
//            er.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
//        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(er);
    }

}
