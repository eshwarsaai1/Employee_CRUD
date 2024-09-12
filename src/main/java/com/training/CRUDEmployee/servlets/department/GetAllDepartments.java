package com.training.CRUDEmployee.servlets.department;

import com.training.CRUDEmployee.models.Department;
import com.training.CRUDEmployee.service.InDatabaseDepartmentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/getAllDepartments")
public class GetAllDepartments extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Logger logger = LoggerFactory.getLogger(GetAllDepartments.class);

        InDatabaseDepartmentService inDatabaseDepartmentService = InDatabaseDepartmentService.getInstance();
        response.setContentType("application/json");
        PrintWriter print = response.getWriter();
        try {
            List<Department> departments = inDatabaseDepartmentService.getAllDepartment();
            for (Department department : departments) {
                print.println(department);
            }
        } catch (Exception e) {
            logger.error("Exception: ", e);
        }

    }
}