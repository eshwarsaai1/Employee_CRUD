package com.training.CRUDEmployee.servlets.employee;

import com.google.gson.Gson;
import com.training.CRUDEmployee.models.Employee;
import com.training.CRUDEmployee.service.InDatabaseEmployeeService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addEmployee")
public class AddEmployee extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = LoggerFactory.getLogger(AddEmployee.class);
        InDatabaseEmployeeService inDatabaseEmployeeService = InDatabaseEmployeeService.getInstance();
        response.setContentType("application/json");
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
        } catch (IOException e) {
            logger.error("Exception: ", e);
        }
        Gson gson = new Gson();
        String user = Validation.validateUser(request);
        if (user != null) {
            if (user.equals("Admin")) {
                try {
                    Employee employee = gson.fromJson(request.getReader(), Employee.class);
                    inDatabaseEmployeeService.addEmployee(employee);
                    printWriter.println("Employee added successfully");
                    logger.info("Employee added successfully");
                } catch (Exception e) {
                    logger.error("Exception: ", e);
                }
            } else {
                printWriter.println("User must be Admin to Add employee");
                logger.info("User not a Admin");
            }

        }
        else {
            printWriter.println("Should login to use CRUD_EMPLOYEE");
            logger.info("Attempted without Login");
        }
    }
}