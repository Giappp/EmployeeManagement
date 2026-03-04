package model;

import model.enums.Status;

public class Employee {
    private Long id;
    private String name;
    private String email;
    private String phone;
    private Status status;
    private Double salary;
    private String division;

    public Employee() {
    }

    public Employee(Long id, String name, String email, String phone, Status status, Double salary, String division) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.status = status;
        this.salary = salary;
        this.division = division;
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
