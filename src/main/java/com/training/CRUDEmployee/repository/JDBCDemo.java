package com.training.CRUDEmployee.repository;

import com.training.CRUDEmployee.repository.impl.Database;

import java.sql.*;

public class JDBCDemo {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String query = "select is_admin from users where user_name = 'eshwar' and password = 'Eshwar@123'";
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(Database.url , Database.userName , Database.password);
        Statement statement = connection.createStatement();
//        int c = statement.executeUpdate(query);
        ResultSet resultSet = statement.executeQuery(query);
        while (resultSet.next()){
//            System.out.print(resultSet.getInt("Max_id") + " ");
            System.out.print(resultSet.getString("is_admin") + " ");
//            System.out.print(resultSet.getString("EMAIL") + " ");
//            System.out.println(resultSet.getInt("DEPARTMENT_ID") + " ");
        }
        connection.close();
    }
}