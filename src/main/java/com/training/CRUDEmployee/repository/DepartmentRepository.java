package com.training.CRUDEmployee.repository;

import com.training.CRUDEmployee.models.Department;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DepartmentRepository {

    Map<Integer, Department> departmentMap = new HashMap<>();

    void addDepartment(Department department) throws ClassNotFoundException, SQLException;

    List<Department> getAllDepartment() throws SQLException, ClassNotFoundException;

    void updateDepartment(int departmentId, Department department) throws SQLException, ClassNotFoundException;

    void deleteDepartment(int departmentId) throws SQLException, ClassNotFoundException;

    int generateDepartmentId() throws SQLException, ClassNotFoundException;
}