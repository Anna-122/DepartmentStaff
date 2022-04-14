package edu.goncharova.exception;

import java.util.Map;

public class ValidateException extends Exception {

    private final Map<String, String> errors;

    public ValidateException(Map<String, String> errors) {
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }
}
