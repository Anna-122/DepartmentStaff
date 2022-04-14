package edu.goncharova.controller.employee;

import edu.goncharova.controller.Command;
import edu.goncharova.services.DepartmentService;
import edu.goncharova.services.EmployeeService;
import edu.goncharova.utils.NumberUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DisplayEmployeeEditForm implements Command {

    private final EmployeeService employeeService = new EmployeeService();
    private final DepartmentService departmentService = new DepartmentService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = NumberUtils.parseNumber(request.getParameter("id"));
        Integer departmentId = NumberUtils.parseNumber(request.getParameter("departmentId"));

        if (id != null && departmentId != null) {
            request.setAttribute("employee", employeeService.getById(id));
            request.setAttribute("departmentId", departmentId);
        }
        request.setAttribute("departments", departmentService.getAllRecords());
        request.getRequestDispatcher("/WEB-INF/jsp/employee/employeeForm.jsp").forward(request, response);
    }
}
