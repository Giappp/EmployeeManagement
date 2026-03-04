import controller.EmployeeController;
import repositories.EmployeeRepository;
import repositories.impl.EmployeeRepositoryImpl;
import services.EmployeeService;
import services.impl.EmployeeServiceImpl;

public class Main {
    public static final EmployeeRepository EMPLOYEE_REPOSITORY = new EmployeeRepositoryImpl();
    public static final EmployeeService EMPLOYEE_SERVICE = new EmployeeServiceImpl(EMPLOYEE_REPOSITORY);
    public static final EmployeeController APP = new EmployeeController(EMPLOYEE_SERVICE);

    public static void main(String[] args) {
        APP.run();
    }
}
