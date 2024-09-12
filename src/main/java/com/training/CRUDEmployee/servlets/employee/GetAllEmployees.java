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
import java.util.List;

@WebServlet(urlPatterns = "/getAllEmployees")
public class GetAllEmployees extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(DeleteEmployee.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        InDatabaseEmployeeService inDatabaseEmployeeService = InDatabaseEmployeeService.getInstance();
        response.setContentType("application/json");
        PrintWriter print = response.getWriter();
        PrintWriter printWriter = null;
        try {
            printWriter = response.getWriter();
        } catch (IOException e) {
            logger.error("Exception: ", e);
        }
        String user = Validation.validateUser(request);
        if (user != null) {
            try {
                List<Employee> employees = inDatabaseEmployeeService.getAllEmployee();
                for (Employee employee : employees) {
                    print.println(employee);
                }
                logger.info("Retrieved employees");
            } catch (Exception e) {
                logger.error("Exception: ", e);
            }
        }
        else {
            printWriter.println("Should login to use CRUD_EMPLOYEE");
            logger.info("Attempted without Login");
        }
    }
}