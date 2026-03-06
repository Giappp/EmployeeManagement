package repositories.impl;

import constants.Config;
import dto.SearchFilter;
import enums.Status;
import model.Employee;
import repositories.EmployeeRepository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static constants.Config.*;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final List<Employee> employees;

    public EmployeeRepositoryImpl() {
        employees = readFromFile();
    }

    @Override
    public void save(Employee employee) {
        Optional<Employee> optionalEmployee = findById(employee.getId());

        if (optionalEmployee.isPresent()) {
            Employee existing = optionalEmployee.get();

            existing.setName(employee.getName());
            existing.setEmail(employee.getEmail());
            existing.setPhone(employee.getPhone());
            existing.setSalary(employee.getSalary());
            existing.setStatus(employee.getStatus());
            existing.setDivision(employee.getDivision());
            existing.setJoinDate(employee.getJoinDate());
        } else {
            employees.add(employee);
        }
    }


    @Override
    public void remove(Employee entity) {
        employees.remove(entity);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        return employees.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Employee> findAll() {
        return new ArrayList<>(employees);
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
        if (searchFilter.status != null) {
            condition = condition.and(e -> e.getStatus().equals(searchFilter.status));
        }
        if (searchFilter.minSalary != null) {
            condition = condition.and(e -> e.getSalary() >= searchFilter.minSalary);
        }

        if (searchFilter.maxSalary != null) {
            condition = condition.and(e -> e.getSalary() <= searchFilter.maxSalary);
        }

        return employees.stream()
                .filter(condition)
                .toList();
    }

    @Override
    public void persist() {
        Path tempPath = FILE_PATH.resolveSibling(FILE_PATH.getFileName() + ".tmp");
        try {
            try (BufferedWriter writer = Files.newBufferedWriter(tempPath, StandardCharsets.UTF_8, StandardOpenOption.CREATE, StandardOpenOption.WRITE)) {

                writer.write(CSV_HEADER);
                writer.newLine();

                for (Employee employee : employees) {
                    writer.write(toCsvRow(employee));
                    writer.newLine();
                }

                writer.flush();
            }
            Files.move(tempPath, FILE_PATH, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (IOException e) {
            // ignored
        }
    }

    private List<Employee> readFromFile() {
        if (Files.notExists(FILE_PATH)) {
            return new ArrayList<>();
        }
        try (Stream<String> lines = Files.lines(Paths.get(Config.FILE_NAME))) {
            return lines.skip(1)
                    .filter(line -> !line.isBlank())
                    .map(this::fromCsvRow)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }


    private String toCsvRow(Employee emp) {
        return String.join(DELIMITER,
                String.valueOf(emp.getId()),
                emp.getName(),
                emp.getEmail(),
                emp.getPhone(),
                emp.getStatus().name(),
                String.valueOf(emp.getSalary()),
                emp.getDivision(),
                emp.getJoinDate().format(Config.formatter)
        );
    }

    private Employee fromCsvRow(String row) {
        String[] data = row.split(DELIMITER);
        return new Employee(
                Long.parseLong(data[0]),
                data[1],
                data[2],
                data[3],
                Status.valueOf(data[4]), // Parse Enum
                Double.parseDouble(data[5]),
                data[6],
                LocalDate.parse(data[7], Config.formatter)
        );
    }
}
