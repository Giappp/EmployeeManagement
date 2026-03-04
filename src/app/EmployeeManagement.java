package app;

import model.Employee;
import model.enums.Status;
import services.EmployeeService;
import utils.InputUtility;


public class EmployeeManagement {
    EmployeeService employeeService;

    public EmployeeManagement(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void run() {
        int input;
        do {
            displayMenu();
            input = InputUtility.getNumber("Your choice: ", Integer::parseInt);
            handleUserInput(input);
        } while (input != 0);
    }

    public void handleUserInput(int userInput) {
        switch (userInput) {
            case 1 -> {
                Employee employeeToAdd = prepareNewEmployee();
                employeeService.addEmployee(employeeToAdd);
            }
            case 2 -> {
            }
            case 9 -> {
                employeeService.display();
            }
            case 0 -> {

            }
            default -> System.out.println("Hãy chọn đúng chức năng");
        }
    }

    private Employee prepareNewEmployee() {
        Long id = InputUtility.getNumber("Id: ", Long::parseLong);
        String name = InputUtility.getString("Nhập tên: ");
        String email = InputUtility.getEmail("Email: ");
        String phone = InputUtility.getPhone("Phone: ");
        Status status = InputUtility.getStatus("Trạng thái nhân viên: ");
        Double salary = InputUtility.getNumber("Lương: ", Double::parseDouble);
        String division = InputUtility.getString("Division: ");
        return new Employee(id, name, email, phone, status, salary, division);
    }

    public void displayMenu() {
        System.out.println("========== Employee Management ==========");
        System.out.println("Menu: ");
        System.out.println("1. Add Employee");
        System.out.println("2. Remove Employee");
        System.out.println("3. Update Employee");
        System.out.println("4. Search Employee");
        System.out.println("5. Sort");
        System.out.println("6. Statistics");
        System.out.println("7. Save");
        System.out.println("8. Load");
        System.out.println("9. Display");
        System.out.println("0. Exit");
    }
}
