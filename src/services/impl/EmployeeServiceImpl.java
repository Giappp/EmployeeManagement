package services.impl;

import constants.Messages;
import dto.SearchFilter;
import enums.ErrorCode;
import enums.Status;
import exception.EmployeeException;
import exception.ResourceNotFoundException;
import lib.InputUtility;
import lib.MenuUtility;
import model.Employee;
import repositories.EmployeeRepository;
import services.EmployeeService;
import validation.Validator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
        if (employeeRepository.findById(employee.getId()).isPresent()) {
            throw new EmployeeException(ErrorCode.DUPLICATE_EMPLOYEE_ID);
        }
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
    public void search() {
        SearchFilter filter = new SearchFilter();
        boolean isFinished = false;
        while (!isFinished) {
            MenuUtility.searchMenu();
            String input = InputUtility.getString(Messages.Prompt.CHOICE);

            if (input == null || input.isEmpty()) {
                executeSearch(filter);
                break;
            }
            try {
                int choice = Integer.parseInt(input);

                switch (choice) {
                    case 0 -> isFinished = true;
                    case 1 -> filter.name = InputUtility.getString(Messages.Prompt.NAME);
                    case 2 -> filter.division = InputUtility.getString(Messages.Prompt.DIVISION);
                    case 3 -> filter.status = InputUtility.getStatus(Messages.Prompt.STATUS);
                    case 4 -> {
                        filter.minSalary = InputUtility.getNumber(Messages.Prompt.MIN_SALARY, Double::parseDouble);
                        filter.maxSalary = InputUtility.getNumber(Messages.Prompt.MAX_SALARY, Double::parseDouble);
                    }
                    case -1 -> {
                        System.out.println(Messages.DEFAULT.CANCEL);
                        isFinished = true;
                    }
                }
            } catch (NumberFormatException ignored) {
                System.out.println(Messages.Error.INVALID_NUMBER);
            }
        }
    }

    @Override
    public void sort() {

    }

    @Override
    public void statistic() {
        Map<String, List<Employee>> map = employeeRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDivision));
    }

    @Override
    public void save() {
        employeeRepository.persist();
        System.out.println(Messages.Success.PERSIST);
    }

    private void executeSearch(SearchFilter filter) {
        System.out.println(Messages.DEFAULT.SEARCH + filter);
        var result = employeeRepository.search(filter);
        if (result == null || result.isEmpty()) {
            System.out.println(Messages.Success.SEARCH_EMPTY);
            return;
        }
        for (Employee employee : result) {
            System.out.println(employee);
        }
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
