package com.training.CRUDEmployee.repository.impl;

import com.training.CRUDEmployee.exceptions.EmployeeNotFound;
import com.training.CRUDEmployee.models.Employee;
import com.training.CRUDEmployee.repository.EmployeeRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryEmployeeRepository implements EmployeeRepository {

    private static final InMemoryEmployeeRepository inMemoryEmployeeRepository = new InMemoryEmployeeRepository();

    private InMemoryEmployeeRepository() {
        // Making class unable to instantiate
    }

    public static InMemoryEmployeeRepository getInstance() {
        return inMemoryEmployeeRepository;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        try {
            if (employeeMap.containsKey(employeeId)) return employeeMap.get(employeeId);
            throw new EmployeeNotFound();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeMap.put(employee.getEmployeeId(), employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return new ArrayList<>(employeeMap.values());
    }

    @Override
    public void updateEmployee(int employeeId, Employee employee) {
        try {
            if (employeeMap.containsKey(employeeId)) {
                employeeMap.put(employeeId, employee);
            } else throw new EmployeeNotFound();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteEmployee(int employeeId) {
        try {
            employeeMap.remove(employeeId);
            throw new EmployeeNotFound();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int generateEmployeeId() {
        int maxId = 0;
        for (int employeeId : employeeMap.keySet()) {
            maxId = Math.max(maxId, employeeId);
        }
        return maxId + 1;
    }


}