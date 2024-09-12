package com.training.CRUDEmployee.repository.impl;

import com.training.CRUDEmployee.models.Department;
import com.training.CRUDEmployee.repository.DepartmentRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDepartmentRepository implements DepartmentRepository {

    private static final InMemoryDepartmentRepository inMemoryDepartmentRepository = new InMemoryDepartmentRepository();

    private InMemoryDepartmentRepository() {
        // Making class unable to instantiate
    }

    public static InMemoryDepartmentRepository getInstance() {
        return inMemoryDepartmentRepository;
    }

    @Override
    public void addDepartment(Department department) {
        departmentMap.put(department.getDepartmentId(), department);
    }

    @Override
    public List<Department> getAllDepartment() {
        return new ArrayList<>(departmentMap.values());
    }

    @Override
    public void updateDepartment(int departmentId, Department department) {
        departmentMap.put(departmentId, new Department(departmentId, department.getDepartmentName()));
    }

    @Override
    public void deleteDepartment(int departmentId) {
        departmentMap.remove(departmentId);
    }

    @Override
    public int generateDepartmentId() {
        int maxId = 0;
        for (int departmentId : departmentMap.keySet()) {
            if (departmentId > maxId) maxId = departmentId;
        }
        return maxId + 1;
    }

    public String getDepartmentName(int departmentId) {
        return departmentMap.get(departmentId).getDepartmentName();
    }

    public boolean containsDepartmentId(int departmentId) {
        return departmentMap.containsKey(departmentId);
    }

    public void putDepartment(int departmentId, String departmentName) {
        departmentMap.put(departmentId, new Department(departmentId, departmentName));
    }

}