package HospitalManagement.Application.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

//It has response body. it convert json to java and vice versa
@RestControllerAdvice
public class GlobalException {

    // 404 - Resource Not Found
    @ExceptionHandler(ResourceNotFound.class)
    public ResponseEntity<ErrorResponse> HandleNotFound(ResourceNotFound ex){
        ErrorResponse er = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(er,HttpStatus.NOT_FOUND);
    }

    // 400 - Validation Error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> HandleValidation(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        ErrorResponse er =new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getBindingResult().getFieldError().getDefaultMessage(), LocalDateTime.now());
        return  new ResponseEntity<>(er, HttpStatus.BAD_REQUEST);

    }

    // 409 - Duplicate Resource
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> HandleDuplicate(
            DuplicateResourceException ex) {

        ErrorResponse er = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                ex.getMessage(),
                LocalDateTime.now()
        );

        return new ResponseEntity<>(er, HttpStatus.CONFLICT);
    }
}
