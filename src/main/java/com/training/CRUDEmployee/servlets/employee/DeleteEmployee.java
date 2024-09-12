package com.training.CRUDEmployee.servlets.employee;

import com.training.CRUDEmployee.service.InDatabaseEmployeeService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = "/deleteEmployee")
public class DeleteEmployee extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(DeleteEmployee.class);

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response){
        InDatabaseEmployeeService inDatabaseEmployeeService = InDatabaseEmployeeService.getInstance();
        response.setContentType("application/json");
        int employeeId = Integer.parseInt(request.getParameter("employeeId"));
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
                    inDatabaseEmployeeService.deleteEmployee(employeeId);
                    printWriter.println("Deleted employee successfully");
                    logger.info("Employee deleted Successfully");
                } catch (Exception e) {
                    logger.error("Exception: ", e);
                }
            } else {
                printWriter.println("User must be Admin to Delete employee");
                logger.debug("User is not Admin");
            }

        }
        else {
            printWriter.println("Should login to use CRUD_EMPLOYEE");
            logger.info("Attempted without Login");
        }
    }
}