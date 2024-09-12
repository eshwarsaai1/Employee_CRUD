package com.training.CRUDEmployee.service;

import com.training.CRUDEmployee.models.Employee;
import com.training.CRUDEmployee.repository.impl.InMemoryEmployeeRepository;

import java.util.List;

public class InMemoryEmployeeService implements EmployeeService {

    InMemoryEmployeeRepository inMemoryEmployeeRepository = InMemoryEmployeeRepository.getInstance();
    private static InMemoryEmployeeService inMemoryEmployeeService;

    private InMemoryEmployeeService() {
        //
    }

    public static InMemoryEmployeeService getInstance() {
        if (inMemoryEmployeeService == null) {
            inMemoryEmployeeService = new InMemoryEmployeeService();
        }
        return inMemoryEmployeeService;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return inMemoryEmployeeRepository.getEmployeeById(employeeId);
    }

    @Override
    public void addEmployee(Employee employee) {
        inMemoryEmployeeRepository.addEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return inMemoryEmployeeRepository.getAllEmployee();
    }

    @Override
    public void updateEmployee(int employeeId, Employee employee) {
        inMemoryEmployeeRepository.updateEmployee(employeeId, employee);
    }

    @Override
    public void deleteEmployee(int employeeId) {
        inMemoryEmployeeRepository.deleteEmployee(employeeId);
    }

    public int generateEmployeeId() {
        return inMemoryEmployeeRepository.generateEmployeeId();
    }
}