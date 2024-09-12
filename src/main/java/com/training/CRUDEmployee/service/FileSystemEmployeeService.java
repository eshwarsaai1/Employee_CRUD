package com.training.CRUDEmployee.service;

import com.training.CRUDEmployee.models.Employee;
import com.training.CRUDEmployee.repository.impl.FilesystemEmployeeRepository;

import java.util.List;

public class FileSystemEmployeeService implements EmployeeService {
    FilesystemEmployeeRepository filesystemEmployeeRepository;
    private static final FileSystemEmployeeService fileSystemEmployeeService = new FileSystemEmployeeService("C:\\Users\\eshwarg_700053\\IdeaProjects\\EmployeeProject\\Employee.txt");

    private FileSystemEmployeeService(String path) {
        filesystemEmployeeRepository = FilesystemEmployeeRepository.getInstance(path);
    }


    public static FileSystemEmployeeService getInstance() {
        return fileSystemEmployeeService;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        return filesystemEmployeeRepository.getEmployeeById(employeeId);
    }

    @Override
    public void addEmployee(Employee employee) {
        filesystemEmployeeRepository.addEmployee(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return filesystemEmployeeRepository.getAllEmployee();
    }

    @Override
    public void updateEmployee(int employeeId, Employee employee) {
        filesystemEmployeeRepository.updateEmployee(employeeId, employee);
    }

    @Override
    public void deleteEmployee(int employeeId) {
        filesystemEmployeeRepository.deleteEmployee(employeeId);
    }

    public int generateEmployeeId() {
        return filesystemEmployeeRepository.generateEmployeeId();
    }
}