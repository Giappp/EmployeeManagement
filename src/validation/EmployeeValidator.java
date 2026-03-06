package validation;

import enums.ErrorCode;
import exception.EmployeeException;
import exception.InvalidDataException;
import model.Employee;

public class EmployeeValidator {
    public static void validate(Employee employee) {
        if (employee.getSalary().isNaN() || employee.getSalary() < 0) {
            throw new EmployeeException(ErrorCode.NEGATIVE_SALARY);
        }
        if (!Validator.isLetter(employee.getName())) {
            throw new InvalidDataException(ErrorCode.INVALID_NAME);
        }
    }
}
