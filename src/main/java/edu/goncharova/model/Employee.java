package edu.goncharova.model;

import edu.goncharova.validator.UniqueEmployeeEmail;
import edu.goncharova.validator.UniquePhoneNumber;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.sf.oval.constraint.*;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    private Integer id;

    @NotBlank(message = "Name field is null or empty")
    @MaxLength(value = 20, message = " Name can have maximum 20 letters")
    @MatchPattern(pattern = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$", message = "Name is not valid")
    private String employeeName;

    @NotBlank(message = "Surname field is null or empty")
    @MaxLength(value = 20, message = " Surname can have maximum 20 letters")
    @MatchPattern(pattern = "^([А-Я]{1}[а-яё]{1,23}|[A-Z]{1}[a-z]{1,23})$", message = "Surname is not valid")
    private String employeeSurname;

    @NotBlank(message = "Phone Number field is null or empty")
    @CheckWith(value = UniquePhoneNumber.class, message = "Phone number is exist in database")
    @MatchPattern(pattern = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
            + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$", message = "Phone number is not valid")
    private String employeePhoneNumber;

    @NotBlank(message = "Email field is null or empty")
    @CheckWith(value = UniqueEmployeeEmail.class, message = "Email is exist in database")
    @Email(message = "Email is not valid.")
    @MatchPattern(pattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*" +
            "@" + "(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$", message = "Email is not valid.")
    private String employeeEmail;

    private Integer departmentId;

    @NotEmpty(message = "Date field is null or empty")
    private Date employeeBirthDate;

}
