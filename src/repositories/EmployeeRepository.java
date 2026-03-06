package repositories;

import dto.SearchFilter;
import model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    void save(Employee entity);

    void remove(Employee entity);

    Optional<Employee> findById(Long id);

    List<Employee> findAll();

    List<Employee> search(SearchFilter searchFilter);

    Employee findByIdForUpdate(Long id);

    void persist();
}
