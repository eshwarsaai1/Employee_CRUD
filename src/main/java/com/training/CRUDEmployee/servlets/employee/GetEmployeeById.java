package com.training.CRUDEmployee.servlets.employee;

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

@WebServlet(urlPatterns = "/getEmployee")
public class GetEmployeeById extends HttpServlet {
    private static final InDatabaseEmployeeService inDatabaseEmployeeService = InDatabaseEmployeeService.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(DeleteEmployee.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response){
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
            try {
                Employee employee = inDatabaseEmployeeService.getEmployeeById(employeeId);
                printWriter.println(employee.toString());
                logger.info("Retrieved employees");
            } catch (Exception e) {
                logger.error("e: ", e);
            }
        }
        else {
            printWriter.println("Should login to use CRUD_EMPLOYEE");
            logger.info("Attempted without Login");
        }
    }
}