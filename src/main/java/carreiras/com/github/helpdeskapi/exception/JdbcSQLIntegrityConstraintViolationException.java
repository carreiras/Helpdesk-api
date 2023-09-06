package carreiras.com.github.helpdeskapi.exception;

public class JdbcSQLIntegrityConstraintViolationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

//    public ObjectNotFoundException(String message, Throwable cause) {
//        super(message, cause);
//    }

    public JdbcSQLIntegrityConstraintViolationException(String message) {
        super(message);
    }

}
