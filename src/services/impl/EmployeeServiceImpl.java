package services.impl;

import constants.Messages;
import enums.ErrorCode;
import enums.Status;
import exception.ResourceNotFoundException;
import model.Employee;
import repositories.EmployeeRepository;
import services.EmployeeService;
import utils.InputUtility;
import utils.MenuUtility;
import validation.Validator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;

    private static final Map<Integer, Consumer<Employee>> updateActions = new HashMap<>();

    static {
        updateActions.put(1, emp -> emp.setName(InputUtility.getValidInput(Messages.Prompt.NAME_UPDATE, Validator::stringNotBlank, Messages.Error.INVALID_NAME)));
        updateActions.put(2, emp -> emp.setEmail(InputUtility.getValidInput(Messages.Prompt.EMAIL_UPDATE, Validator::isEmailValid, Messages.Error.INVALID_EMAIL)));
        updateActions.put(3, emp -> emp.setPhone(InputUtility.getValidInput(Messages.Prompt.PHONE_UPDATE, Validator::isPhoneValid, Messages.Error.INVALID_PHONE)));
        updateActions.put(4, emp -> emp.setStatus(InputUtility.getStatus(Messages.Prompt.STATUS_UPDATE)));
        updateActions.put(5, emp -> emp.setSalary(InputUtility.getNumber(Messages.Prompt.SALARY_UPDATE, Double::parseDouble)));
        updateActions.put(6, emp -> emp.setDivision(InputUtility.getValidInput(Messages.Prompt.DIVISION_UPDATE, Validator::stringNotBlank, Messages.Error.INVALID_DIVISION)));
        updateActions.put(7, emp -> emp.setJoinDate(InputUtility.getDate(Messages.Prompt.JOIN_DATE_UPDATE)));
    }

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void insert() {
        Employee employee = doInsert();
        employeeRepository.save(employee);
        System.out.println(Messages.Success.INSERT);
    }

    @Override
    public void update() {
        Long updatedEmployeeId = InputUtility.getNumber(Messages.Prompt.ID_UPDATE, Long::parseLong);
        Employee employee = employeeRepository.findByIdForUpdate(updatedEmployeeId);
        boolean isFinished = false;
        while (!isFinished) {
            MenuUtility.updateMenu();
            int choice = InputUtility.getNumber(Messages.Prompt.CHOICE, Integer::parseInt);

            switch (choice) {
                case 0 -> {
                    // Lưu và thoát
                    employeeRepository.save(employee);
                    System.out.println(Messages.Success.UPDATE);
                    isFinished = true;
                }
                case -1 -> {
                    System.out.println(Messages.DEFAULT.CANCEL);
                    isFinished = true;
                }
                case 99 -> {
                    updateActions.values().forEach(employeeConsumer -> employeeConsumer.accept(employee));
                    employeeRepository.save(employee);
                    isFinished = true;
                }
                default -> {
                    // Thực thi action dựa trên Map
                    if (updateActions.containsKey(choice)) {
                        updateActions.get(choice).accept(employee);
                    } else {
                        System.out.println(Messages.Error.INVALID_CHOICE);
                    }
                }
            }
        }
    }

    @Override
    public void remove() {
        Long removedEmployeeId = InputUtility.getNumber(Messages.Prompt.ID_REMOVE, Long::parseLong);

        Employee employee = employeeRepository.findById(removedEmployeeId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.EMPLOYEE_ID_NOT_FOUND));
        employeeRepository.remove(employee);

        System.out.println(Messages.Success.REMOVE);
    }

    @Override
    public void display() {
        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            System.out.println(Messages.Success.EMPTY);
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
        Long id = InputUtility.getNumber(Messages.Prompt.ID, Long::parseLong);
        String name = InputUtility.getValidInput(Messages.Prompt.NAME, Validator::stringNotBlank, Messages.Error.INVALID_NAME);
        String email = InputUtility.getValidInput(Messages.Prompt.EMAIL, Validator::isEmailValid, Messages.Error.INVALID_EMAIL);
        String phone = InputUtility.getValidInput(Messages.Prompt.PHONE, Validator::isPhoneValid, Messages.Error.INVALID_PHONE);
        Status status = InputUtility.getStatus(Messages.Prompt.STATUS);
        Double salary = InputUtility.getNumber(Messages.Prompt.SALARY, Double::parseDouble);
        String division = InputUtility.getValidInput(Messages.Prompt.DIVISION, Validator::stringNotBlank, Messages.Error.INVALID_DIVISION);
        LocalDate joinDate = InputUtility.getDate(Messages.Prompt.JOIN_DATE);
        return new Employee(id, name, email, phone, status, salary, division, joinDate);
    }
}
