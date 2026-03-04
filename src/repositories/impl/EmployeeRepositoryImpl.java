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
    public void save(Employee entity) {
        dbContext.EMPLOYEES.add(entity);
    }

    @Override
    public void remove(Employee entity) {
        dbContext.EMPLOYEES.remove(entity);
    }

    @Override
    public Optional<Employee> findById(Integer id) {
        return dbContext.EMPLOYEES.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(dbContext.EMPLOYEES);
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
        
        return dbContext.EMPLOYEES.stream()
                .filter(condition)
                .toList();
    }
}
