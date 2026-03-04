package repositories;

import database.MockDatabase;
import model.Employee;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<T, TId> {
    MockDatabase dbContext = new MockDatabase();

    void save(T entity);

    void remove(T entity);

    Optional<Employee> findById(TId id);

    List<Employee> findAll();
}
