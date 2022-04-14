package edu.goncharova.validator;

import edu.goncharova.exception.ValidateException;
import net.sf.oval.ConstraintViolation;
import net.sf.oval.Validator;
import net.sf.oval.context.FieldContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomOValValidator {

    private final Validator validator;

    public CustomOValValidator() {
        validator = new Validator();
    }

    public void validate(Object target) throws ValidateException {
        Map<String, String> errors = new HashMap<>();
        List<ConstraintViolation> violations = validator.validate(target);
        violations.stream().filter(item -> item.getCheckDeclaringContext() instanceof FieldContext).forEach(item -> {
            String message = item.getMessage();
            String name = ((FieldContext) item.getCheckDeclaringContext()).getField().getName();
            errors.put(name, message);
        });
        if (!errors.isEmpty()) {
            throw new ValidateException(errors);
        }
    }
}
