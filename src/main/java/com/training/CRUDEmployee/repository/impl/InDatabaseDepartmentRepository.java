package com.training.CRUDEmployee.repository.impl;

import com.training.CRUDEmployee.exceptions.DepartmentIdNotFound;
import com.training.CRUDEmployee.models.Department;
import com.training.CRUDEmployee.repository.DepartmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InDatabaseDepartmentRepository implements DepartmentRepository {
    private static InDatabaseDepartmentRepository inDatabaseDepartmentRepository;
    private static final Logger logger = LoggerFactory.getLogger(InDatabaseDepartmentRepository.class);

    private InDatabaseDepartmentRepository() {
        //making class unable to instantiate
    }

    public static InDatabaseDepartmentRepository getInstance() {
        if (inDatabaseDepartmentRepository == null) {
            inDatabaseDepartmentRepository = new InDatabaseDepartmentRepository();
            logger.info("Department DataBase Instance created");
        }
        return inDatabaseDepartmentRepository;
    }

    public String getDepartmentNameById(int departmentId) throws ClassNotFoundException, SQLException {
        logger.info("getDepartmentNameById Method called...");
        System.out.println("Getting Department name...");
        String query = "SELECT department_name FROM DEPARTMENT WHERE DEPARTMENT_ID = " + departmentId;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(Database.url, Database.userName, Database.password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String departmentName = null;
        if (resultSet.next()) {
            departmentName = resultSet.getString("department_name");
        } else {
            try {
                throw new DepartmentIdNotFound();
            } catch (Exception e) {
                logger.error("e: ", e);
            }
        }
        System.out.println("got it :)");
        connection.close();
        logger.info("getDepartmentNameById Method returned Department Name");
        return departmentName;
    }

    @Override
    public void addDepartment(Department department) throws ClassNotFoundException, SQLException {
        logger.info("query excution started");
        String query = "INSERT INTO DEPARTMENT VALUES (" + department.getDepartmentId() + ",'" + department.getDepartmentName() + "')";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(Database.url, Database.userName, Database.password);
        logger.info("connection Created");
        Statement statement = connection.createStatement();
        int c = statement.executeUpdate(query);
        connection.close();
        logger.info("Department Added successfully");
    }

    @Override
    public List<Department> getAllDepartment() throws SQLException, ClassNotFoundException {
        logger.info("getAllDepartment called...");
        String query = "SELECT * FROM DEPARTMENT";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(Database.url, Database.userName, Database.password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Department> departmentList = new ArrayList<>();
        while (resultSet.next()) {
            int departmentID = resultSet.getInt("department_id");
            String departmentName = resultSet.getString("department_name");
            Department department = new Department(departmentID, departmentName);
            departmentList.add(department);
        }
        logger.info("getAllDepartment called...");
        connection.close();
        return departmentList;
    }

    @Override
    public void updateDepartment(int departmentId, Department department) throws SQLException, ClassNotFoundException {
        if (containsDepartmentId(departmentId)) {
            String query = "UPDATE DEPARTMENT SET DEPARTMENT_NAME= '" + department.getDepartmentName() + "' WHERE DEPARTMENT_ID=" + departmentId;
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(Database.url, Database.userName, Database.password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
            System.out.println("Department updated successfully");
        } else {
            try {
                throw new DepartmentIdNotFound();
            } catch (DepartmentIdNotFound e) {
                logger.error("e: ", e);
            }
        }
    }

    @Override
    public void deleteDepartment(int departmentId) throws SQLException, ClassNotFoundException {
        if (containsDepartmentId(departmentId)) {
            String query = "DELETE FROM DEPARTMENT WHERE DEPARTMENT_ID=" + departmentId;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(Database.url, Database.userName, Database.password);
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            connection.close();
            logger.info("Department deleted successfully");
        } else {
            try {
                throw new DepartmentIdNotFound();
            } catch (DepartmentIdNotFound e) {
                logger.error("e: ", e);
            }
        }
    }

    @Override
    public int generateDepartmentId() throws SQLException, ClassNotFoundException {
        String query = "SELECT max(Department_id) as Max_id from Department";
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(Database.url, Database.userName, Database.password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        int maxId = 1;
        if (resultSet.next()) {
            maxId = resultSet.getInt("Max_id") + 1;
        }
        connection.close();
        return maxId;
    }


    public boolean containsDepartmentId(int departmentId) throws ClassNotFoundException, SQLException {
        logger.info("Checking department id");
        String query = "SELECT * from Department where Department_id=" + departmentId;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(Database.url, Database.userName, Database.password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        boolean has = resultSet.next();
        connection.close();
        logger.info("containsDepartmentName returned");
        return has;
    }
}