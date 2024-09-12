package com.training.CRUDEmployee.Controllers;

import com.training.CRUDEmployee.exceptions.DepartmentIdNotFound;
import com.training.CRUDEmployee.models.Department;
import com.training.CRUDEmployee.models.Employee;
import com.training.CRUDEmployee.service.InDatabaseDepartmentService;
import com.training.CRUDEmployee.service.InDatabaseEmployeeService;
import com.training.CRUDEmployee.utill.UserInput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class InDatabaseController {
    private static final Scanner scanner = new Scanner(System.in);
    private static final InDatabaseEmployeeService employeeService = InDatabaseEmployeeService.getInstance();
    private static final InDatabaseDepartmentService departmentService = InDatabaseDepartmentService.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(InDatabaseController.class);

    public static void run() {
        while (true) {
            System.out.println("Choose operation to perform on Employees: \n 1.Get employee by id \n 2.Add employee \n 3.Delete Employee \n 4.Update employee \n 5.Show all employees \n 6.Add department \n 7.Delete Department  \n 8.Update Department \n 9.Get all Departments \n 10.Exit");
            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        System.out.print("Enter Employee id: ");
                        Employee returnedEmployee = employeeService.getEmployeeById(scanner.nextInt());
                        scanner.nextLine();
                        if (returnedEmployee != null) {
                            System.out.println(returnedEmployee);
                        }
                        break;

                    case 2:
                        Employee employee = UserInput.getEmployee();
                        int departmentId = employee.getDepartment().getDepartmentId();
                        if (departmentService.containsDepartmentId(departmentId)) {
                            Department department = new Department(departmentId, departmentService.getDepartmentNameById(departmentId));
                            employee.setDepartment(department);
                        } else {
                            throw new DepartmentIdNotFound();
                        }
                        employeeService.addEmployee(employee);
                        break;

                    case 3:
                        System.out.print("Enter Employee id: ");
                        employeeService.deleteEmployee(scanner.nextInt());
                        scanner.nextLine();
                        break;

                    case 4:
                        System.out.print("Enter Employee id: ");
                        int currentEmployeeID = scanner.nextInt();
                        scanner.nextLine();
                        Employee currentEmployee = employeeService.getEmployeeById(currentEmployeeID);
                        Employee updatedEmployee = UserInput.getUpdatedEmployee();
                        logger.info("Updated employee received");
                        if (updatedEmployee.getEmployeeName() == null)
                            updatedEmployee.setEmployeeName(currentEmployee.getEmployeeName());
                        if (updatedEmployee.getEmail() == null) updatedEmployee.setEmail(currentEmployee.getEmail());
                        if (updatedEmployee.getDepartment() == null)
                            updatedEmployee.setDepartment(currentEmployee.getDepartment());
                        else {
                            int updatedDepartmentId = updatedEmployee.getDepartment().getDepartmentId();
                            if (departmentService.containsDepartmentId(updatedDepartmentId)) {
                                updatedEmployee.getDepartment().setDepartmentName(departmentService.getDepartmentNameById(updatedDepartmentId));
                            } else {
                                throw new DepartmentIdNotFound();
                            }
                        }
                        if (updatedEmployee.getAddress() == null)
                            updatedEmployee.setAddress(currentEmployee.getAddress());
                        updatedEmployee.setEmployeeId(currentEmployee.getEmployeeId());
                        employeeService.updateEmployee(updatedEmployee.getEmployeeId(), updatedEmployee);

                        break;

                    case 5:
                        List<Employee> employeeList = employeeService.getAllEmployee();
                        for (Employee eachEmployee : employeeList) {
                            System.out.println(eachEmployee);
                        }
                        break;

                    case 6:
                        System.out.print("Enter Department name: ");
                        String departmentName = scanner.nextLine();
                        departmentService.addDepartment(new Department(departmentService.generateDepartmentID(), departmentName));
                        break;

                    case 7:
                        System.out.println("Enter Department ID: ");
                        departmentService.deleteDepartment(scanner.nextInt());
                        scanner.nextLine();
                        break;

                    case 8:
                        System.out.println("Enter Department ID: ");
                        int departmentIdToBeUpdated = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Enter Department new Name: ");
                        String updatedDepartmentName = scanner.nextLine();
                        departmentService.updateDepartment(departmentIdToBeUpdated, new Department(departmentIdToBeUpdated, updatedDepartmentName));
                        break;

                    case 9:
                        List<Department> departments = departmentService.getAllDepartment();
                        for (Department department : departments) {
                            System.out.println(department);
                        }
                        break;

                    case 10:
                        System.exit(0);

                    default:
                        System.out.println("choose valid Operation");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Exception: " + e);
            }
        }
    }
}