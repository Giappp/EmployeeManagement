package repositories;

import dto.SearchFilter;
import model.Employee;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {
    List<Employee> search(SearchFilter searchFilter);
}
