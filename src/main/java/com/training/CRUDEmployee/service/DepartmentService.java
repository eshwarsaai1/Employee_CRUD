package com.training.CRUDEmployee.service;

import com.training.CRUDEmployee.models.Department;

import java.sql.SQLException;
import java.util.List;

public interface DepartmentService {
    void addDepartment(Department department) throws SQLException, ClassNotFoundException;

    List<Department> getAllDepartment() throws SQLException, ClassNotFoundException;

    void updateDepartment(int departmentId, Department department) throws SQLException, ClassNotFoundException;

    void deleteDepartment(int departmentId) throws SQLException, ClassNotFoundException;

    int generateDepartmentID() throws SQLException, ClassNotFoundException;
}