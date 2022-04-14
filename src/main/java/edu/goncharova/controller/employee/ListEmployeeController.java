package edu.goncharova.controller.employee;

import edu.goncharova.controller.Command;
import edu.goncharova.services.EmployeeService;
import edu.goncharova.utils.NumberUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListEmployeeController implements Command {
    private final EmployeeService employeeService = new EmployeeService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = NumberUtils.parseNumber(request.getParameter("id"));

        if (id != null) {
            request.setAttribute("employees", employeeService.getAllEmployeesByDepartmentId(id));
            request.setAttribute("departmentId", id);
            request.getRequestDispatcher("/WEB-INF/jsp/employee/employeeList.jsp").forward(request, response);
        } else {
            response.sendRedirect("/");
        }
    }
}
