package edu.goncharova.controller;

import edu.goncharova.controller.deparment.*;
import edu.goncharova.controller.employee.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private final static CommandFactory factory = new CommandFactory();

    private final Map<String, Command> commandMap = new HashMap<>();

    private CommandFactory() {
        commandMap.put("/department/save", new SaveDepartmentController());//
        commandMap.put("/department/delete", new DeleteDepartmentController());//
        commandMap.put("/department/employees", new ListEmployeeController());//
        commandMap.put("/employee/delete", new DeleteEmployeeController());
        commandMap.put("/employee/save", new SaveEmployeeController());
        commandMap.put("/employee/form", new DisplayEmployeeEditForm());
        commandMap.put("/department/list", new ListDepartmentController());//
        commandMap.put("/department/form", new DisplayDepartmentEditForm());//
    }

    public static CommandFactory getInstance() {
        return factory;
    }

    public Command getCommand(String command) {
        Command found = commandMap.get(command);
        return found != null ? found : commandMap.get("/department/list");
    }
}
