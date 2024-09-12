package com.training.CRUDEmployee.Controllers;

import com.training.CRUDEmployee.exceptions.DepartmentIdNotFound;
import com.training.CRUDEmployee.models.Department;
import com.training.CRUDEmployee.models.Employee;
import com.training.CRUDEmployee.service.InMemoryDepartmentService;
import com.training.CRUDEmployee.service.InMemoryEmployeeService;
import com.training.CRUDEmployee.utill.UserInput;

import java.util.List;
import java.util.Scanner;

public class InMemoryController {
    private static final Scanner scanner = new Scanner(System.in);
    private static final InMemoryEmployeeService employeeService = InMemoryEmployeeService.getInstance();
    private static final InMemoryDepartmentService departmentService = InMemoryDepartmentService.getInstance();

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
                        employee.setEmployeeId(employeeService.generateEmployeeId());
                        int departmentId = employee.getDepartment().getDepartmentId();
                        if (departmentService.containsDepartmentId(departmentId)) {
                            Department department = new Department(departmentId, departmentService.getDepartment(departmentId));
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
                        if (updatedEmployee.getEmployeeName() == null)
                            updatedEmployee.setEmployeeName(currentEmployee.getEmployeeName());
                        if (updatedEmployee.getEmail() == null) updatedEmployee.setEmail(currentEmployee.getEmail());
                        if (updatedEmployee.getDepartment() == null)
                            updatedEmployee.setDepartment(currentEmployee.getDepartment());
                        else {
                            int updatedDepartmentId = updatedEmployee.getDepartment().getDepartmentId();
                            if (departmentService.containsDepartmentId(updatedDepartmentId)) {
                                updatedEmployee.getDepartment().setDepartmentName(departmentService.getDepartment(updatedDepartmentId));
                            } else {
                                throw new DepartmentIdNotFound();
                            }
                        }
                        if (updatedEmployee.getAddress() == null)
                            updatedEmployee.setAddress(currentEmployee.getAddress());
                        updatedEmployee.setEmployeeId(currentEmployee.getEmployeeId());
                        employeeService.updateEmployee(currentEmployeeID, updatedEmployee);
                        break;

                    case 5:
                        List<Employee> employeeList = employeeService.getAllEmployee();
                        for (Employee eachEmployee : employeeList) {
                            System.out.println(eachEmployee);
                        }
                        break;
                    case 6:
                        System.out.print("Enter Department name: ");
                        departmentService.addDepartment(new Department(departmentService.generateDepartmentID(), scanner.nextLine()));
                        break;

                    case 7:
                        System.out.print("Enter Department ID: ");
                        departmentService.deleteDepartment(scanner.nextInt());
                        scanner.nextLine();
                        break;

                    case 8:
                        System.out.print("Enter Department ID: ");
                        int departmentIdToBeUpdated = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Department new Name: ");
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
                System.out.println(e);
            }
        }
    }
}