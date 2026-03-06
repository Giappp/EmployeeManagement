import controller.BackgroundTask;
import controller.EmployeeController;
import repositories.EmployeeRepository;
import repositories.impl.EmployeeRepositoryImpl;
import services.EmployeeService;
import services.impl.EmployeeServiceImpl;

public class Main {
    public static final EmployeeRepository EMPLOYEE_REPOSITORY = new EmployeeRepositoryImpl();
    public static final EmployeeService EMPLOYEE_SERVICE = new EmployeeServiceImpl(EMPLOYEE_REPOSITORY);
    public static final EmployeeController APP = new EmployeeController(EMPLOYEE_SERVICE);
    public static final BackgroundTask BACKGROUND_TASK = new BackgroundTask(EMPLOYEE_REPOSITORY);

    public static void main(String[] args) {
        BACKGROUND_TASK.start();
        try {
            APP.run();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
