package edu.goncharova.controller.deparment;

import edu.goncharova.controller.Command;
import edu.goncharova.model.Department;
import edu.goncharova.services.DepartmentService;
import edu.goncharova.utils.NumberUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteDepartmentController implements Command {

    private final DepartmentService departmentService = new DepartmentService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = NumberUtils.parseNumber(request.getParameter("id"));

        if (id != null) {
            Department department = new Department();
            department.setId(id);
            departmentService.delete(department);
            response.sendRedirect("/");
        }
    }
}
