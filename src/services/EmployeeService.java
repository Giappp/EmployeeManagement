package services;

import dto.SearchFilter;
import enums.SortCriteria;
import enums.StatisticCriteria;
import model.Employee;

public interface EmployeeService {
    void insert(Employee employee);

    void update(Employee employee);

    Employee getById(Long id);

    void removeById(Long id);

    void display();

    void search(SearchFilter filter);

    void sort(SortCriteria option);

    void statistic(StatisticCriteria criteria);

    Double totalSalary();

    void save();
}
