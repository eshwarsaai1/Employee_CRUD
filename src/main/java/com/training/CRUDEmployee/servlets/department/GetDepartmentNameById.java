package com.training.CRUDEmployee.servlets.department;

import com.training.CRUDEmployee.service.InDatabaseDepartmentService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/getDepartmentName")
public class GetDepartmentNameById extends HttpServlet {
    private static final InDatabaseDepartmentService departmentService = InDatabaseDepartmentService.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("application/json");

        int departmentId = Integer.parseInt(request.getParameter("departmentId"));

        // Get the PrintWriter to write the response
        PrintWriter out = response.getWriter();

        // Write the HTML response
        try {
            String departmentName = departmentService.getDepartmentNameById(departmentId);
            out.println(departmentName);
        } catch (Exception e) {
            out.println(e);
        }
    }
}