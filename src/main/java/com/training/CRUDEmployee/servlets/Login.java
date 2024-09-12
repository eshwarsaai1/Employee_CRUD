package com.training.CRUDEmployee.servlets;

import com.training.CRUDEmployee.repository.impl.Authentication;
import com.training.CRUDEmployee.servlets.employee.Validation;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;


@WebServlet(urlPatterns = "/login")
public class Login extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(Login.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        String role = null;
        try {
            role = Validation.validateAndGetRole(userName, password);
        } catch (Exception e) {
            logger.error("Exception ", e);
        }
        if (role != null) {
            Cookie authCookie = new Cookie("authCookie", Character.toString(role.charAt(0)) + UUID.randomUUID());

            Authentication.authToken = authCookie.getValue();
            authCookie.setMaxAge(60 * 60);
            authCookie.setSecure(true);
            authCookie.setHttpOnly(true);
            response.addCookie(authCookie);
            printWriter.println("Login successful!! \n Welcome " + userName);
            logger.info("Login successful");
        } else {
            printWriter.println("UserName or Password Incorrect");
            logger.error("Invalid credentials");
        }
    }
}