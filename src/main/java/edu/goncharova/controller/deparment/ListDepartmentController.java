package edu.goncharova.controller.deparment;

import edu.goncharova.controller.Command;
import edu.goncharova.model.Department;
import edu.goncharova.services.DepartmentService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class ListDepartmentController implements Command {
    private static final Logger D = LogManager.getLogger(ListDepartmentController.class);
    private final DepartmentService departmentService = new DepartmentService();

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("departments", departmentService.getAllRecords());
        request.getRequestDispatcher("/WEB-INF/jsp/department/departmentList.jsp").forward(request, response);
    }
}
