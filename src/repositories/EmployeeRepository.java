package repositories;

import database.MockDatabase;
import dto.SearchFilter;
import model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    MockDatabase dbContext = new MockDatabase();

    void save(Employee entity);

    void remove(Employee entity);

    Optional<Employee> findById(Long id);

    List<Employee> findAll();

    List<Employee> search(SearchFilter searchFilter);

    Employee findByIdForUpdate(Long id);
}
