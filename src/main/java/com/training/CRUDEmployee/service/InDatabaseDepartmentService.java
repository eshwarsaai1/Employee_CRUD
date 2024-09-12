package com.training.CRUDEmployee.service;

import com.training.CRUDEmployee.models.Department;
import com.training.CRUDEmployee.repository.impl.InDatabaseDepartmentRepository;

import java.sql.SQLException;
import java.util.List;

public class InDatabaseDepartmentService implements DepartmentService {

    InDatabaseDepartmentRepository inDatabaseDepartmentRepository = InDatabaseDepartmentRepository.getInstance();
    private static InDatabaseDepartmentService inDatabaseDepartmentService;

    private InDatabaseDepartmentService() {
        //
    }

    public static InDatabaseDepartmentService getInstance() {
        if (inDatabaseDepartmentService == null) {
            inDatabaseDepartmentService = new InDatabaseDepartmentService();
        }
        return inDatabaseDepartmentService;
    }

    @Override
    public void addDepartment(Department department) throws SQLException, ClassNotFoundException {
        inDatabaseDepartmentRepository.addDepartment(department);
    }

    @Override
    public List<Department> getAllDepartment() throws SQLException, ClassNotFoundException {
        return inDatabaseDepartmentRepository.getAllDepartment();
    }

    @Override
    public void updateDepartment(int departmentId, Department department) throws SQLException, ClassNotFoundException {
        inDatabaseDepartmentRepository.updateDepartment(departmentId, department);
    }

    @Override
    public void deleteDepartment(int departmentId) throws SQLException, ClassNotFoundException {
        inDatabaseDepartmentRepository.deleteDepartment(departmentId);
    }

    @Override
    public int generateDepartmentID() throws SQLException, ClassNotFoundException {
        return inDatabaseDepartmentRepository.generateDepartmentId();
    }

    public boolean containsDepartmentId(int departmentId) throws SQLException, ClassNotFoundException {
        return inDatabaseDepartmentRepository.containsDepartmentId(departmentId);
    }

    public String getDepartmentNameById(int departmentId) throws SQLException, ClassNotFoundException {
        return inDatabaseDepartmentRepository.getDepartmentNameById(departmentId);
    }
}