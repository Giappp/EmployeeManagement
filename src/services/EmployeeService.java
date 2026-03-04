package services;

import dto.SearchFilter;
import model.Employee;
import repositories.EmployeeRepository;

import java.util.List;

public class EmployeeService {
    EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void display() {
        for (Employee employee : employeeRepository.findAll()) {
            System.out.println(employee);
        }
    }

    public List<Employee> search(SearchFilter filter) {
        return employeeRepository.search(filter);
    }
}
