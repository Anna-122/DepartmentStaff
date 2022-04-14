package edu.goncharova.validator;

import edu.goncharova.model.Employee;
import edu.goncharova.services.EmployeeService;
import net.sf.oval.Validator;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.context.OValContext;

public class UniqueEmployeeEmail implements CheckWithCheck.SimpleCheck {

    private final EmployeeService employeeService = new EmployeeService();

    @Override
    public boolean isSatisfied(Object validatedObject, Object value, OValContext context, Validator validator) {
        Employee employee = (Employee) validatedObject;
        String employeeEmail = employee.getEmployeeEmail();
        Employee employeeEmailFromDb = employeeService.getByName(employeeEmail);
        return employeeEmailFromDb == null || employeeEmailFromDb.getId().equals(employee.getId());
    }
}
