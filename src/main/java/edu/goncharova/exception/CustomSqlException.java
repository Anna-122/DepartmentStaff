package edu.goncharova.exception;


public class CustomSqlException extends RuntimeException {

    public CustomSqlException() {
        super();
    }

    public CustomSqlException(String message) {
        super(message);
    }

    public CustomSqlException(Throwable cause) {
        super(cause);
    }
}
