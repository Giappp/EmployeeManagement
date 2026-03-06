package controller;

import constants.Messages;
import dto.SearchFilter;
import enums.*;
import lib.InputUtility;
import lib.MenuUtility;
import mapper.EmployeeInputMapper;
import model.Employee;
import services.EmployeeService;
import validation.Validator;

import java.time.LocalDate;
import java.util.function.Consumer;


public class EmployeeController {
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void run() {
        int input;
        do {
            MenuUtility.mainMenu();
            input = InputUtility.getNumber(Messages.Prompt.CHOICE, Integer::parseInt);
            handleUserInsertRequest(input);
        } while (input != 0);
    }

    public void handleUserInsertRequest(int userInput) {
        switch (userInput) {
            case 1 -> doInsert();
            case 2 -> doRemove();
            case 3 -> doUpdate();
            case 4 -> doSearch();
            case 5 -> doSort();
            case 6 -> doStats();
            case 7 -> employeeService.save();
            case 8 -> employeeService.display();
            case 0 -> {
                System.out.println("Saving...");
                employeeService.save();
            }
            default -> System.out.println(Messages.Error.INVALID_CHOICE);
        }
    }

    private void doStats() {
        StatisticCriteria criteria = InputUtility.getChoice(StatisticCriteria.class);
        employeeService.statistic(criteria);
    }

    private void doSort() {
        SortCriteria sortCriteria = InputUtility.getChoice(SortCriteria.class);
        employeeService.sort(sortCriteria);
    }

    private void doSearch() {
        SearchFilter filter = handleUserSearchRequest();
        employeeService.search(filter);
    }

    private SearchFilter handleUserSearchRequest() {
        SearchFilter filter = new SearchFilter();
        while (true) {
            Search choice = InputUtility.getChoice(Search.class);

            switch (choice) {
                case NAME_FILTER -> filter.name = InputUtility.getString(Messages.Prompt.NAME);
                case DIVISION_FILTER -> filter.division = InputUtility.getString(Messages.Prompt.DIVISION);
                case STATUS_FILTER -> filter.status = InputUtility.getChoice(Status.class);
                case SALARY_FILTER -> {
                    filter.minSalary = InputUtility.getNumber(Messages.Prompt.MIN_SALARY, Double::parseDouble);
                    filter.maxSalary = InputUtility.getNumber(Messages.Prompt.MAX_SALARY, Double::parseDouble);
                }
                default -> {
                    return filter;
                }
            }
        }
    }

    private void doRemove() {
        Long removedEmployeeId = InputUtility.getNumber(Messages.Prompt.ID_REMOVE, Long::parseLong);
        employeeService.removeById(removedEmployeeId);
    }

    private void doInsert() {
        Long id = InputUtility.getNumber(Messages.Prompt.ID, Long::parseLong);
        String name = InputUtility.getValidInput(Messages.Prompt.NAME, Validator::isLetter, Messages.Error.INVALID_NAME);
        String email = InputUtility.getValidInput(Messages.Prompt.EMAIL, Validator::isEmailValid, Messages.Error.INVALID_EMAIL);
        String phone = InputUtility.getValidInput(Messages.Prompt.PHONE, Validator::isPhoneValid, Messages.Error.INVALID_PHONE);
        Status status = InputUtility.getChoice(Status.class);
        Double salary = InputUtility.getNumber(Messages.Prompt.SALARY, Double::parseDouble);
        String division = InputUtility.getValidInput(Messages.Prompt.DIVISION, Validator::stringNotBlank, Messages.Error.INVALID_DIVISION);
        LocalDate joinDate = InputUtility.getDate(Messages.Prompt.JOIN_DATE);
        Employee employee = new Employee(id, name, email, phone, status, salary, division, joinDate);
        employeeService.insert(employee);
    }

    private void doUpdate() {
        Long id = InputUtility.getNumber(Messages.Prompt.ID_UPDATE, Long::parseLong);
        Employee employee = employeeService.getById(id);

        while (true) {
            Update choice = InputUtility.getChoice(Update.class);

            switch (choice) {
                case UPDATE -> {
                    employeeService.update(employee);
                    return;
                }
                case CANCEL -> {
                    System.out.println(Messages.DEFAULT.CANCEL);
                    return;
                }
                case UPDATE_ALL -> {
                    EmployeeInputMapper.UPDATE_ACTION_MAPPER.values().forEach(action -> action.accept(employee));
                    employeeService.update(employee);
                    return;
                }
                default -> {
                    Consumer<Employee> action = EmployeeInputMapper.UPDATE_ACTION_MAPPER.get(choice);
                    if (action != null) {
                        action.accept(employee);
                    } else {
                        System.out.println(Messages.Error.INVALID_CHOICE);
                    }
                }
            }
        }
    }
}
