package edu.goncharova.validator;

import edu.goncharova.model.Department;
import edu.goncharova.services.DepartmentService;
import net.sf.oval.Validator;
import net.sf.oval.constraint.CheckWithCheck;
import net.sf.oval.context.OValContext;

public class UniqueDepartmentName implements CheckWithCheck.SimpleCheck {
    private final DepartmentService departmentService = new DepartmentService();

    @Override
    public boolean isSatisfied(Object validatedObject, Object value, OValContext context, Validator validator) {
        Department department = (Department) validatedObject;
        String departmentName = department.getDepartmentName();
        Department name = departmentService.getByName(departmentName);
        return name == null || name.getId().equals(department.getId());
    }
}