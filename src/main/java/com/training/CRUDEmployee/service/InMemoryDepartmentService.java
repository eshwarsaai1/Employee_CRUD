package com.training.CRUDEmployee.service;

import com.training.CRUDEmployee.models.Department;
import com.training.CRUDEmployee.repository.impl.InMemoryDepartmentRepository;

import java.util.List;

public class InMemoryDepartmentService implements DepartmentService {
    InMemoryDepartmentRepository inMemoryDepartmentRepository = InMemoryDepartmentRepository.getInstance();
    private static InMemoryDepartmentService inMemoryDepartmentService;

    private InMemoryDepartmentService() {
        //
    }

    public static InMemoryDepartmentService getInstance() {
        if (inMemoryDepartmentService == null) {
            inMemoryDepartmentService = new InMemoryDepartmentService();
        }
        return inMemoryDepartmentService;
    }

    @Override
    public void addDepartment(Department department) {
        inMemoryDepartmentRepository.addDepartment(department);
    }

    @Override
    public List<Department> getAllDepartment() {
        return inMemoryDepartmentRepository.getAllDepartment();
    }

    @Override
    public void updateDepartment(int departmentId, Department department) {
        inMemoryDepartmentRepository.updateDepartment(departmentId, department);
    }

    @Override
    public void deleteDepartment(int departmentId) {
        inMemoryDepartmentRepository.deleteDepartment(departmentId);
    }

    @Override
    public int generateDepartmentID() {
        return inMemoryDepartmentRepository.generateDepartmentId();
    }

    public String getDepartment(int departmentId) {
        return inMemoryDepartmentRepository.getDepartmentName(departmentId);
    }

    public boolean containsDepartmentId(int departmentId) {
        return inMemoryDepartmentRepository.containsDepartmentId(departmentId);
    }

    public void putDepartment(int departmentId, String departmentName) {
        inMemoryDepartmentRepository.putDepartment(departmentId, departmentName);
    }
}