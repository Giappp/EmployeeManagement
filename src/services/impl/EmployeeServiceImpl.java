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
import utils.ConverterUtility;
import utils.InputUtility;
import utils.MessageUtility;
import validation.Validator;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;

    private static final Map<Integer, Field> fieldMap = new HashMap<>();

    static {
        Field[] fields = Employee.class.getDeclaredFields();
        int index = 1;
        for (Field field : fields) {
            if (field.getName().equalsIgnoreCase("id")) continue;

            field.setAccessible(true);
            fieldMap.put(index, field);
            index++;
        }
    }

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
        int choice;
        do {
            MessageUtility.printUpdateMenu();
            choice = InputUtility.getNumber(PromptMessage.CHOICE, Integer::parseInt);
            if (choice == 99) {
                for (Field field : fieldMap.values()) {
                    doUpdate(employee, field);
                }
            } else if (fieldMap.containsKey(choice)) {
                doUpdate(employee, fieldMap.get(choice));
            } else {
                System.out.println(ValidationMessage.INVALID_CHOICE);
            }
        } while (choice != 0 && choice != -1);
        if (choice == 0) {
            employeeRepository.save(employee);
            System.out.println(ResultMessage.UPDATE_SUCCESS);
        }
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

    private void doUpdate(Employee employee, Field field) {
        try {
            if (field.getName().equalsIgnoreCase("status")) {
                Status newStatus = InputUtility.getStatus(PromptMessage.GET_STATUS);
                field.set(employee, newStatus);
                return;
            }
            String input = InputUtility.getValidInput(PromptMessage.UPDATE_VALUE, Validator::stringNotBlank, ValidationMessage.INVALID_VALUE);
            Object convertedValue = ConverterUtility.convertToFieldType(input, field.getType());

            field.set(employee, convertedValue);
            System.out.println(ResultMessage.UPDATE_SUCCESS);
        } catch (IllegalAccessException ignored) {

        }
    }
}
