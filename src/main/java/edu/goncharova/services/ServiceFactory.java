package edu.goncharova.services;
import edu.goncharova.validator.CustomOValValidator;
import edu.goncharova.exception.ValidateException;

import java.util.List;

public interface ServiceFactory<Entity, Key> {
    CustomOValValidator validator = new CustomOValValidator();

    Entity save(Entity model) throws ValidateException;

    void delete(Entity model);

    Entity getById(Key id);

    List<Entity> getAllRecords();
}
