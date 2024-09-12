package com.training.CRUDEmployee.models;

public class Employee {
    private int employeeId;
    private String employeeName;
    private String email;
    private Department department;
    private Address address;

    public Employee() {

    }

    public Employee(int id, String name) {
        employeeId = id;
        employeeName = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "{ \n Employee Id: " + employeeId +
                "\n Employee Name: " + employeeName +
                "\n Email: " + email +
                "\n Department: " + department +
                "\n Address: " + address +
                "\n}";
    }
}