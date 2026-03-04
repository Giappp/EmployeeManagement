package services;

import model.Employee;

import java.util.List;

public interface EmployeeService {
    void insert();

    void update();

    void remove();

    void display();

    List<Employee> search();
}
