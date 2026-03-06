package services.impl;

import constants.Messages;
import dto.SearchFilter;
import enums.ErrorCode;
import enums.SortCriteria;
import enums.StatisticCriteria;
import exception.EmployeeException;
import exception.ResourceNotFoundException;
import model.Employee;
import repositories.EmployeeRepository;
import services.EmployeeService;
import validation.EmployeeValidator;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void insert(Employee employee) {
        if (employeeRepository.findById(employee.getId()).isPresent()) {
            throw new EmployeeException(ErrorCode.DUPLICATE_EMPLOYEE);
        }
        EmployeeValidator.validate(employee);
        employeeRepository.save(employee);
        System.out.println(Messages.Success.INSERT);
    }

    @Override
    public void update(Employee employee) {
        if (employeeRepository.findById(employee.getId()).isPresent()) {
            EmployeeValidator.validate(employee);
            employeeRepository.save(employee);
            System.out.println(Messages.Success.UPDATE);
        }
    }

    @Override
    public Employee getById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND));
    }

    @Override
    public void removeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(ErrorCode.EMPLOYEE_NOT_FOUND));

        employeeRepository.remove(employee);

        System.out.println(Messages.Success.REMOVE);
    }

    @Override
    public void display() {
        List<Employee> employees = employeeRepository.findAll();

        if (employees.isEmpty()) {
            System.out.println(Messages.Success.EMPTY);
            return;
        }

        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Override
    public void search(SearchFilter filter) {
        List<Employee> results = employeeRepository.search(filter);
        if (results.isEmpty()) {
            System.out.println(Messages.Success.EMPTY);
        } else {
            for (Employee employee : results) {
                System.out.println(employee);
            }
        }
    }


    @Override
    public void sort(SortCriteria option) {
        List<Employee> employees = employeeRepository.findAll();
        Comparator<Employee> comparator = switch (option) {
            case BY_SALARY -> Comparator.comparing(Employee::getSalary);
            case BY_NAME -> Comparator.comparing(Employee::getName);
            case BY_JOIN_DATE -> Comparator.comparing(Employee::getJoinDate);
        };
        employees.sort(comparator);
        for (Employee employee : employees) {
            System.out.println(employee);
        }
    }

    @Override
    public void statistic(StatisticCriteria criteria) {
        List<Employee> employees = employeeRepository.findAll();
        switch (criteria) {
            case TOTAL_EMPLOYEE -> {
                System.out.println(employees.size());
            }
            case TOTAL_SALARY -> {
                double sum = employees.stream()
                        .mapToDouble(Employee::getSalary)
                        .sum();
                System.out.println(sum);
            }
            case AVG_SALARY -> {
                OptionalDouble average = employees.stream()
                        .mapToDouble(Employee::getSalary)
                        .average();
                System.out.println(average.getAsDouble());
            }
            case MAX_SALARY -> {
                OptionalDouble max = employees.stream()
                        .mapToDouble(Employee::getSalary)
                        .max();
                System.out.println(max.getAsDouble());
            }
            case TOP_3 -> {
                List<Employee> top3 = employees.stream()
                        .sorted(Collections.reverseOrder(Comparator.comparingDouble(Employee::getSalary)))
                        .limit(3)
                        .toList();
                for (Employee employee : top3) {
                    System.out.println(employee);
                }
            }
            case GROUP_BY_DIVISION -> {
                Map<String, List<Employee>> empGroupByDiv = employees.stream()
                        .collect(Collectors.groupingBy(Employee::getDivision));
                for (var entry : empGroupByDiv.entrySet()) {
                    System.out.println(entry.getKey());
                    for (Employee employee : entry.getValue()) {
                        System.out.println(employee);
                    }
                }
            }
            case COUNT_ACTIVE -> {
                long count = employees.stream()
                        .filter(Employee::isActive)
                        .count();
                System.out.println(count);
            }
        }
    }

    @Override
    public Double totalSalary() {
        return employeeRepository.findAll().stream()
                .mapToDouble(Employee::getSalary)
                .sum();
    }

    @Override
    public void save() {
        employeeRepository.persist();
        System.out.println(Messages.Success.PERSIST);
    }

}
