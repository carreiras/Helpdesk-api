package carreiras.com.github.helpdeskapi.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> handlerObjectNotFoundException(
            ObjectNotFoundException ex,
            HttpServletRequest request) {
        StandardError standardError = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.NOT_FOUND.value(),
                "Object Not Found",
                ex.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(standardError);
    }

    @ExceptionHandler({ JdbcSQLIntegrityConstraintViolationException.class })
    public ResponseEntity<StandardError> handlerJdbcSQLIntegrityConstraintViolationException(
            JdbcSQLIntegrityConstraintViolationException e,
            HttpServletRequest request) {
        StandardError standardError = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Data Breach",
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler({ DataIntegrityViolationException.class })
    public ResponseEntity<StandardError> handlerDataIntegrityViolationException(
            DataIntegrityViolationException e,
            HttpServletRequest request) {
        StandardError standardError = new StandardError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Data Breach",
                e.getMessage(),
                request.getRequestURI());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(standardError);
    }

    @ExceptionHandler({ MethodArgumentNotValidException.class })
    public ResponseEntity<StandardError> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException e,
            HttpServletRequest request) {
        ValidationError validationError = new ValidationError(
                System.currentTimeMillis(),
                HttpStatus.BAD_REQUEST.value(),
                "Field validation error",
                "Erro na validação dos campos",
                request.getRequestURI());

        for (FieldError x : e.getBindingResult().getFieldErrors())
            validationError.addError(x.getField(), x.getDefaultMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationError);
    }

}
