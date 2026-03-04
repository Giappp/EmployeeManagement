package services.impl;

import annotation.DisplayName;
import dto.SearchFilter;
import enums.ErrorCode;
import enums.Status;
import exception.ResourceNotFoundException;
import model.Employee;
import repositories.EmployeeRepository;
import services.EmployeeService;
import utils.ConverterUtility;
import utils.InputUtility;
import utils.MessageUtility;

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
        System.out.println("Thêm nhân viên mới thành công!");
    }

    @Override
    public void update() {
        Long updatedEmployeeId = InputUtility.getNumber("Nhập Id nhân viên cần sửa: ", Long::parseLong);
        Employee employee = employeeRepository.findByIdForUpdate(updatedEmployeeId);
        int choice;
        do {
            MessageUtility.printUpdateMenu();
            choice = InputUtility.getNumber("Lựa chọn của bạn: ", Integer::parseInt);
            if (choice == 99) {
                for (Field field : fieldMap.values()) {
                    doUpdate(employee, field);
                }
            } else if (fieldMap.containsKey(choice)) {
                doUpdate(employee, fieldMap.get(choice));
            } else {
                System.out.println("Lựa chọn không hợp lệ");
            }
        } while (choice != 0 && choice != -1);
        if (choice == 0) {
            employeeRepository.save(employee);
            System.out.println("Lưu thông tin thành công!");
        }
    }

    @Override
    public void remove() {
        Long removedEmployeeId = InputUtility.getNumber("Nhập Id nhân viên cần xóa: ", Long::parseLong);
        Employee employee = employeeRepository.findById(removedEmployeeId)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.EMPLOYEE_ID_NOT_FOUND));
        employeeRepository.remove(employee);
        System.out.println("Xóa nhân viên thành công");
    }

    @Override
    public void display() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            System.out.println("Danh sách nhân viên rỗng");
            return;
        }
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Override
    public List<Employee> search() {
        SearchFilter filter = doSearch();
        return employeeRepository.search(filter);
    }

    private SearchFilter doSearch() {

    }

    private Employee doInsert() {
        Long id = InputUtility.getNumber("Id: ", Long::parseLong);
        String name = InputUtility.getString("Nhập tên: ");
        String email = InputUtility.getEmail("Email: ");
        String phone = InputUtility.getPhone("Phone: ");
        Status status = InputUtility.getStatus("Trạng thái nhân viên: ");
        Double salary = InputUtility.getNumber("Lương: ", Double::parseDouble);
        String division = InputUtility.getString("Division: ");
        LocalDate joinDate = LocalDate.now();
        return new Employee(id, name, email, phone, status, salary, division, joinDate);
    }

    private void doUpdate(Employee employee, Field field) {
        try {
            if (field.getName().equalsIgnoreCase("status")) {
                Status newStatus = InputUtility.getStatus("Nhập giá trị mới cho status: ");
                field.set(employee, newStatus);
                return;
            }
            String input = InputUtility.getString("Nhập giá trị mới cho " + field.getAnnotation(DisplayName.class).value() + ": ");
            Object convertedValue = ConverterUtility.convertToFieldType(input, field.getType());

            field.set(employee, convertedValue);
            System.out.println("Cập nhật thành công");
        } catch (IllegalAccessException e) {
            System.out.println("Lỗi khi câp nhật: " + e.getMessage());
        }
    }
}
