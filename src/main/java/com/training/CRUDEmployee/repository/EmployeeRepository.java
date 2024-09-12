package com.training.CRUDEmployee.repository;

import com.training.CRUDEmployee.models.Employee;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface EmployeeRepository {
    Map<Integer, Employee> employeeMap = new HashMap<>();

    Employee getEmployeeById(int employeeId) throws ClassNotFoundException, SQLException;

    void addEmployee(Employee employee) throws ClassNotFoundException, SQLException;

    List<Employee> getAllEmployee() throws ClassNotFoundException, SQLException;

    void updateEmployee(int employeeId, Employee employee) throws ClassNotFoundException, SQLException;

    void deleteEmployee(int employeeId) throws SQLException, ClassNotFoundException;
}