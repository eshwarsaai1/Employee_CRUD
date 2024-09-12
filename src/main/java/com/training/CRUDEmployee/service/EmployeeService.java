package com.training.CRUDEmployee.service;

import com.training.CRUDEmployee.models.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeService {
    Employee getEmployeeById(int employeeId) throws SQLException, ClassNotFoundException;

    void addEmployee(Employee employee) throws SQLException, ClassNotFoundException;

    List<Employee> getAllEmployee() throws SQLException, ClassNotFoundException;

    void updateEmployee(int employeeId, Employee employee) throws SQLException, ClassNotFoundException;

    void deleteEmployee(int employeeId) throws SQLException, ClassNotFoundException;
}