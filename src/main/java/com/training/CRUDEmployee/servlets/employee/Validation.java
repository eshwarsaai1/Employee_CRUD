package com.training.CRUDEmployee.servlets.employee;

import com.training.CRUDEmployee.repository.impl.Authentication;
import com.training.CRUDEmployee.repository.impl.Database;
import com.training.CRUDEmployee.servlets.Login;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class Validation {
    private static final Logger logger = LoggerFactory.getLogger(Login.class);

    public static String validateUser(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("authCookie")) {
                String authCookie = cookie.getValue();
                if (authCookie.equals(Authentication.authToken)) {
                    logger.info("User Authentication successful");
                    return authCookie.charAt(0) == 'a' ? "Admin" : "User";
                }
                logger.info("User Authentication failed");
            }
        }
        logger.info("authCookie not found");
        return null;
    }

    public static String validateAndGetRole(String userName, String password) throws SQLException, ClassNotFoundException {
        logger.info("validating user...");
        String query = "select is_admin from users where user_name='" + userName + "'and password='" + password + "'";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                Database.url, Database.userName, Database.password
        );
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        String role = null;
        if (resultSet.next()) {
            role = resultSet.getString("is_admin").equals("0") ? "user" : "admin";
        }
        logger.info("User validated");
        return role;
    }
}