package controller;

import constants.Messages;
import services.EmployeeService;
import utils.InputUtility;
import utils.MessageUtility;


public class EmployeeController {
    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void run() {
        int input;
        do {
            MessageUtility.printMainMenu();
            input = InputUtility.getNumber(Messages.Prompt.CHOICE, Integer::parseInt);
            handleUserInsertRequest(input);
        } while (input != 0);
    }

    public void handleUserInsertRequest(int userInput) {
        switch (userInput) {
            case 1 -> employeeService.insert();
            case 2 -> employeeService.remove();
            case 3 -> employeeService.update();
            case 8 -> employeeService.display();
            case 0 -> {

            }
            default -> System.out.println(Messages.Error.INVALID_CHOICE);
        }
    }
}
