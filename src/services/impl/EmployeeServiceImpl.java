package services.impl;

import constants.PromptMessage;
import constants.ResultMessage;
import constants.ValidationMessage;
import enums.ErrorCode;
import enums.Status;
import exception.ResourceNotFoundException;
import model.Employee;
import repositories.EmployeeRepository;
import services.EmployeeService;
import utils.InputUtility;
import validation.Validator;

import java.time.LocalDate;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void insert() {
        Employee employee = doInsert();
        employeeRepository.save(employee);
        System.out.println(ResultMessage.INSERT_SUCCESS);
    }

    @Override
    public void update() {
        Long updatedEmployeeId = InputUtility.getNumber(PromptMessage.GET_ID_TO_UPDATE, Long::parseLong);
        Employee employee = employeeRepository.findByIdForUpdate(updatedEmployeeId);

    }

    @Override
    public void remove() {
        Long removedEmployeeId = InputUtility.getNumber(PromptMessage.GET_ID_TO_REMOVE, Long::parseLong);

        Employee employee = employeeRepository.findById(removedEmployeeId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.EMPLOYEE_ID_NOT_FOUND));
        employeeRepository.remove(employee);

        System.out.println(ResultMessage.REMOVE_SUCCESS);
    }

    @Override
    public void display() {
        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            System.out.println(ResultMessage.EMPTY_LIST);
            return;
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Override
    public List<Employee> search() {
        return null;
    }

    private Employee doInsert() {
        Long id = InputUtility.getNumber(PromptMessage.GET_ID, Long::parseLong);
        String name = InputUtility.getValidInput(PromptMessage.GET_NAME, Validator::stringNotBlank, ValidationMessage.INVALID_NAME);
        String email = InputUtility.getEmail(PromptMessage.GET_EMAIL);
        String phone = InputUtility.getPhone(PromptMessage.GET_PHONE);
        Status status = InputUtility.getStatus(PromptMessage.GET_STATUS);
        Double salary = InputUtility.getNumber(PromptMessage.GET_SALARY, Double::parseDouble);
        String division = InputUtility.getValidInput(PromptMessage.GET_DIVISION, Validator::stringNotBlank, ValidationMessage.INVALID_DIVISION);
        LocalDate joinDate = LocalDate.now();
        return new Employee(id, name, email, phone, status, salary, division, joinDate);
    }
}
