package repositories.impl;

import dto.SearchFilter;
import model.Employee;
import repositories.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class EmployeeRepositoryImpl implements EmployeeRepository {

    @Override
    public void save(Employee employee) {
        Optional<Employee> existing = findById(employee.getId());

        if (existing.isPresent()) {
            int index = dbContext.employees.indexOf(existing.get());
            dbContext.employees.set(index, employee);
        } else {
            dbContext.employees.add(employee);
        }
    }


    @Override
    public void remove(Employee entity) {
        dbContext.employees.remove(entity);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return dbContext.employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(dbContext.employees);
    }

    @Override
    public List<Employee> search(SearchFilter searchFilter) {
        Predicate<Employee> condition = employee -> true;

        if (searchFilter.name != null && !searchFilter.name.trim().isEmpty()) {
            condition = condition.and(e -> e.getName().toLowerCase().contains(searchFilter.name.toLowerCase()));
        }
        if (searchFilter.division != null && !searchFilter.division.trim().isEmpty()) {
            condition = condition.and(e -> e.getDivision().equalsIgnoreCase(searchFilter.division));
        }
        if (searchFilter.minSalary != null) {
            condition = condition.and(e -> e.getSalary() >= searchFilter.minSalary);
        }

        if (searchFilter.maxSalary != null) {
            condition = condition.and(e -> e.getSalary() <= searchFilter.maxSalary);
        }

        return dbContext.employees.stream()
                .filter(condition)
                .toList();
    }

    @Override
    public Employee findByIdForUpdate(Long id) {
        Employee original = findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên với Id: " + id));
        return new Employee(original);
    }
}
