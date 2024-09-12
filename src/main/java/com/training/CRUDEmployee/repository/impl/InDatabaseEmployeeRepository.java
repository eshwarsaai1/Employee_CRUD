package com.training.CRUDEmployee.repository.impl;

import com.training.CRUDEmployee.models.Address;
import com.training.CRUDEmployee.models.Department;
import com.training.CRUDEmployee.models.Employee;
import com.training.CRUDEmployee.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InDatabaseEmployeeRepository implements EmployeeRepository {
    private final InDatabaseDepartmentRepository inDatabaseDepartmentRepository = InDatabaseDepartmentRepository.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(InDatabaseEmployeeRepository.class);
    private static Connection connection = null;


    private static InDatabaseEmployeeRepository inDataBaseEmployeeRepository;

    private InDatabaseEmployeeRepository() {
        //making class unable to instantiate
    }

    public static InDatabaseEmployeeRepository getInstance() {
        if (inDataBaseEmployeeRepository == null) {
            inDataBaseEmployeeRepository = new InDatabaseEmployeeRepository();
        }
        logger.info("InDatabaseDepartmentRepository instantiated");
        return inDataBaseEmployeeRepository;
    }


    @Override
    public Employee getEmployeeById(int employeeId) throws ClassNotFoundException, SQLException {
        logger.info("Get employee by id method called");
        String query = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID=" + employeeId;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                Database.url, Database.userName, Database.password
        );
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        if (resultSet.next()) {
            Employee employee = new Employee();
            employee.setEmployeeId(resultSet.getInt("Employee_id"));
            employee.setEmployeeName(resultSet.getString("Employee_name"));
            employee.setEmail(resultSet.getString("Email"));
            int departmentId = resultSet.getInt("department_id");
            employee.setDepartment(new Department(departmentId, inDatabaseDepartmentRepository.getDepartmentNameById(departmentId)));
            employee.setAddress(new Address(resultSet.getString("Address"), resultSet.getInt("pincode")));
            return employee;
        }
        logger.info("getEmployeeById returned: ");
        return null;
    }

    @Override
    public void addEmployee(Employee employee) throws ClassNotFoundException, SQLException {
        String query = "INSERT INTO EMPLOYEE ( EMPLOYEE_NAME, EMAIL, DEPARTMENT_ID, ADDRESS, PINCODE) VALUES('" + employee.getEmployeeName() + "','" + employee.getEmail() + "'," + employee.getDepartment().getDepartmentId() + ",'" + employee.getAddress().getLocation() + "'," + employee.getAddress().getPinCode() + ")";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                Database.url, Database.userName, Database.password
        );
        Statement statement = connection.createStatement();
        int c = statement.executeUpdate(query);
        connection.close();
        System.out.println("Employee Added successfully");
    }

    @Override
    public List<Employee> getAllEmployee() throws ClassNotFoundException, SQLException {
        String query = "SELECT * FROM EMPLOYEE";
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                Database.url, Database.userName, Database.password
        );
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Employee> employeeList = new ArrayList<>();
        while (resultSet.next()) {
            Employee employee = new Employee();
            employee.setEmployeeId(resultSet.getInt("Employee_id"));
            employee.setEmployeeName(resultSet.getString("Employee_name"));
            employee.setEmail(resultSet.getString("Email"));
            int departmentId = resultSet.getInt("Department_id");
            employee.setDepartment(new Department(departmentId, inDatabaseDepartmentRepository.getDepartmentNameById(departmentId)));
            employee.setAddress(new Address(resultSet.getString("Address"), resultSet.getInt("pincode")));
            employeeList.add(employee);
        }
        connection.close();
        return employeeList;
    }

    @Override
    public void updateEmployee(int employeeId, Employee employee) throws ClassNotFoundException, SQLException {
        logger.info("Upadate Employee method called...");
        String query = "UPDATE EMPLOYEE SET Employee_name ='" + employee.getEmployeeName() +
                "' , Email ='" + employee.getEmail() +
                "' , department_id = " + employee.getDepartment().getDepartmentId() +
                " , Address = '" + employee.getAddress().getLocation() +
                "' , pinCode = " + employee.getAddress().getPinCode() +
                " WHERE EMPLOYEE_ID =" + employeeId;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                Database.url, Database.userName, Database.password
        );
        Statement statement = connection.createStatement();
        statement.executeUpdate(query);
        connection.close();
        logger.info("Employee updated successfully");
    }

    @Override
    public void deleteEmployee(int employeeId) throws SQLException, ClassNotFoundException {
        String query = "DELETE FROM EMPLOYEE WHERE EMPLOYEE_ID=" + employeeId;
        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
                Database.url, Database.userName, Database.password
        );
        Statement statement = connection.createStatement();
        int c = statement.executeUpdate(query);
        connection.close();
        System.out.println("Employee deleted successfully");
    }
}