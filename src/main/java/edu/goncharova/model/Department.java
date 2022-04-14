package edu.goncharova.model;

import edu.goncharova.validator.UniqueDepartmentName;
import lombok.*;
import net.sf.oval.constraint.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    private Integer id;

    @MinLength(value = 4, message = "Department name need minimum 4")
    @MaxLength(value = 20, message = "Department name need maximum 20 letters")
    @NotBlank(message = "Department name can't be empty")
    @MatchPattern(pattern = "^([А-Я0-9]{1}[а-яё0-9]{1,23}|[A-Z0-9]{1}[a-z0-9]{1,23})$", message = "Department name is not valid")
    @CheckWith(value = UniqueDepartmentName.class, message = "Department with this name is exist")
    private String departmentName;
}
