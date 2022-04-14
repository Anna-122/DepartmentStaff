package edu.goncharova.services;

import edu.goncharova.dao.EmployeeDAO;
import edu.goncharova.model.Employee;
import edu.goncharova.exception.ValidateException;

import java.util.List;

public class EmployeeService implements ServiceFactory<Employee, Integer> {

    private final EmployeeDAO dao = new EmployeeDAO();

    @Override
    public Employee save(Employee employee) throws ValidateException {
        validator.validate(employee);
        return dao.save(employee);
    }

    @Override
    public void delete(Employee employee) {
        dao.delete(employee);
    }

    @Override
    public Employee getById(Integer id) {
        return dao.getById(id);
    }

    @Override
    public List<Employee> getAllRecords() {
        return dao.read();
    }

    public List<Employee> getAllEmployeesByDepartmentId(Integer id) {
        return dao.read(id);
    }

    public Employee getByName(String name) {
        return dao.getByName(name);
    }

    public Employee getByPhoneNumber(String phone) {
        return dao.getByPhoneNumber(phone);
    }
}
