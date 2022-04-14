package edu.goncharova.services;

import edu.goncharova.dao.DepartmentDAO;
import edu.goncharova.model.Department;
import edu.goncharova.exception.ValidateException;

import java.util.List;

public class DepartmentService implements ServiceFactory<Department, Integer> {

    private final DepartmentDAO dao = new DepartmentDAO();

    @Override
    public Department save(Department department) throws ValidateException {
        validator.validate(department);
        return dao.save(department);
    }

    @Override
    public List<Department> getAllRecords() {
        return dao.getAll();
    }

    @Override
    public void delete(Department department) {
        dao.delete(department);
    }

    @Override
    public Department getById(Integer id) {
        return dao.getById(id);
    }

    public Department getByName(String name) {
        return dao.getByName(name);
    }
}
