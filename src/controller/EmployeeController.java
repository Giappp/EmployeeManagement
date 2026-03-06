package controller;

import constants.Messages;
import lib.InputUtility;
import lib.MenuUtility;
import services.EmployeeService;


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
            case 1 -> employeeService.insert();
            case 2 -> employeeService.remove();
            case 3 -> employeeService.update();
            case 4 -> employeeService.search();
            case 5 -> employeeService.sort();
            case 6 -> employeeService.statistic();
            case 7 -> employeeService.save();
            case 8 -> employeeService.display();
            case 0 -> {
                System.out.println("Saving...");
                employeeService.save();
            }
            default -> System.out.println(Messages.Error.INVALID_CHOICE);
        }
    }
}
