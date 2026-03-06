package controller;

import model.Employee;
import repositories.EmployeeRepository;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class BackgroundTask {

    private final EmployeeRepository employeeRepository;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(2);

    public BackgroundTask(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void start() {

        scheduler.scheduleAtFixedRate(() -> {
            double totalSalary = employeeRepository.findAll()
                    .stream()
                    .mapToDouble(Employee::getSalary)
                    .sum();
            System.out.println("\n[System] Tổng quỹ lương hiện tại: " + totalSalary);
        }, 0, 30, TimeUnit.SECONDS);

        scheduler.scheduleAtFixedRate(() -> {
            System.out.println("\n[System] Đang tự động lưu dữ liệu...");
            employeeRepository.persist();
            System.out.println("\n[System] Backup thành công");
        }, 0, 60, TimeUnit.SECONDS);
    }
}
