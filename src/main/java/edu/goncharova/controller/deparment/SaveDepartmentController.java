package edu.goncharova.controller.deparment;

import edu.goncharova.controller.Command;
import edu.goncharova.model.Department;
import edu.goncharova.services.DepartmentService;
import edu.goncharova.utils.NumberUtils;
import edu.goncharova.exception.ValidateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SaveDepartmentController implements Command {

    private final DepartmentService departmentService = new DepartmentService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Department department = new Department();
        department.setId(NumberUtils.parseNumber(request.getParameter("id")));
        department.setDepartmentName(request.getParameter("departmentName"));
        try {
            departmentService.save(department);
            response.sendRedirect("/department/list");
        } catch (ValidateException e) {
            request.setAttribute("errors", e.getErrors());
            request.setAttribute("department", department);
            request.getRequestDispatcher("/WEB-INF/jsp/department/departmentForm.jsp").forward(request, response);
        }
    }
}
