import app.EmployeeManagement;
import repositories.EmployeeRepository;
import repositories.impl.EmployeeRepositoryImpl;
import services.EmployeeService;

public class Main {
    public static final EmployeeRepository EMPLOYEE_REPOSITORY = new EmployeeRepositoryImpl();
    public static final EmployeeService EMPLOYEE_SERVICE = new EmployeeService(EMPLOYEE_REPOSITORY);
    public static final EmployeeManagement APP = new EmployeeManagement(EMPLOYEE_SERVICE);

    public static void main(String[] args) {
        APP.run();
    }
}
