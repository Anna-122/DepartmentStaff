package edu.goncharova.controller.employee;

import edu.goncharova.controller.Command;
import edu.goncharova.model.Employee;
import edu.goncharova.services.EmployeeService;
import edu.goncharova.utils.NumberUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteEmployeeController implements Command {

    private final EmployeeService employeeService = new EmployeeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = NumberUtils.parseNumber(request.getParameter("id"));
        Integer departmentId = NumberUtils.parseNumber(request.getParameter("departmentId"));

        if (id != null) {
            Employee employee = new Employee();
            employee.setId(id);
            employeeService.delete(employee);
            response.sendRedirect("/department/employees?id=" + departmentId);
        } else {
            response.sendRedirect("/");
        }
    }
}
