package com.training.CRUDEmployee.servlets.employee;

import com.google.gson.Gson;
import com.training.CRUDEmployee.models.Employee;
import com.training.CRUDEmployee.service.InDatabaseEmployeeService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/updateEmployee")
public class UpdateEmployee extends HttpServlet {

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) {
        Logger logger = LoggerFactory.getLogger(UpdateEmployee.class);
        InDatabaseEmployeeService inDatabaseEmployeeService = InDatabaseEmployeeService.getInstance();
        Gson gson = new Gson();
        response.setContentType("application/json");
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
        } catch (IOException e) {
            logger.error("Exception: ", e);
        }
        String user = Validation.validateUser(request);


        if (user != null) {
            if (user.equals("Admin")) {
                try {
                    Employee employee = gson.fromJson(request.getReader(), Employee.class);
                    printWriter.println(employee);
                    inDatabaseEmployeeService.updateEmployee(employee.getEmployeeId(), employee);
                    printWriter.println("Updated successfully");
                    logger.info("Employee updated successfully");
                } catch (Exception e) {
                    logger.error("Exception: ", e);
                }
            } else {
                printWriter.println("User must be Admin to Post");
                logger.info("User not a Admin");
            }

        }
        else {
            printWriter.println("Should login to use CRUD_EMPLOYEE");
            logger.info("Attempted without Login");
        }
    }
}