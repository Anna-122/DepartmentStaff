package edu.goncharova.servlets;

import edu.goncharova.controller.Command;
import edu.goncharova.controller.CommandFactory;
import edu.goncharova.exception.ValidateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletDispatcher extends HttpServlet {

    private final CommandFactory factory = CommandFactory.getInstance();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {
        Command command = factory.getCommand(req.getRequestURI());
        try {
            command.execute(req, resp);
        } catch (ServletException | IOException | ValidateException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
