package edu.goncharova.controller;

import edu.goncharova.exception.ValidateException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {
    void execute(HttpServletRequest request, HttpServletResponse response) throws ValidateException, ServletException, IOException;
}
