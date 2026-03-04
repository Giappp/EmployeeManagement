package model;

import annotation.DisplayName;
import enums.Status;

import java.time.LocalDate;

public class Employee {
    private Long id;
    @DisplayName("Họ và Tên")
    private String name;
    @DisplayName("Email")
    private String email;
    @DisplayName("Số điện thoại")
    private String phone;
    @DisplayName("Trạng Thái")
    private Status status;
    @DisplayName("Lương")
    private Double salary;
    @DisplayName("Phòng ban")
    private String division;
    @DisplayName("Ngày tuyển dụng")
    private LocalDate joinDate;

    public Employee() {
    }

    public Employee(Long id, String name, String email, String phone, Status status, Double salary, String division, LocalDate joinDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.salary = salary;
        this.division = division;
        this.joinDate = joinDate;
    }

    public Employee(Employee employee) {
        this.id = employee.id;
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.phone = employee.getPhone();
        this.status = employee.getStatus();
        this.salary = employee.getSalary();
        this.division = employee.getDivision();
        this.joinDate = employee.getJoinDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", salary=" + salary +
                ", division='" + division + '\'' +
                '}';
    }
}
