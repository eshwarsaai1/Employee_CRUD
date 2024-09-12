package com.training.CRUDEmployee.service;

import com.training.CRUDEmployee.models.Employee;
import com.training.CRUDEmployee.repository.impl.InDatabaseEmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;

public class InDatabaseEmployeeService implements EmployeeService {
    private static final Logger logger = LoggerFactory.getLogger(InDatabaseEmployeeService.class);
    InDatabaseEmployeeRepository inDatabaseEmployeeRepository = InDatabaseEmployeeRepository.getInstance();
    private static InDatabaseEmployeeService inDatabaseEmployeeService;

    private InDatabaseEmployeeService() {
        //
    }

    public static InDatabaseEmployeeService getInstance() {
        if (inDatabaseEmployeeService == null) {
            inDatabaseEmployeeService = new InDatabaseEmployeeService();
        }
        return inDatabaseEmployeeService;
    }

    @Override
    public Employee getEmployeeById(int employeeId) throws SQLException, ClassNotFoundException {
        return inDatabaseEmployeeRepository.getEmployeeById(employeeId);
    }

    @Override
    public void addEmployee(Employee employee) throws SQLException, ClassNotFoundException {
        inDatabaseEmployeeRepository.addEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployee() throws SQLException, ClassNotFoundException {
        return inDatabaseEmployeeRepository.getAllEmployee();
    }

    @Override
    public void updateEmployee(int employeeId, Employee employee) throws SQLException, ClassNotFoundException {
        inDatabaseEmployeeRepository.updateEmployee(employeeId, employee);
    }

    @Override
    public void deleteEmployee(int employeeId) throws SQLException, ClassNotFoundException {
        inDatabaseEmployeeRepository.deleteEmployee(employeeId);
    }

}