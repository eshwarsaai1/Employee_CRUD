package com.training.CRUDEmployee.Controllers;

import com.training.CRUDEmployee.models.Employee;
import com.training.CRUDEmployee.service.FileSystemEmployeeService;
import com.training.CRUDEmployee.utill.UserInput;

import java.util.List;
import java.util.Scanner;

public class FileSystemController {
    private static final Scanner scanner = new Scanner(System.in);
    private static final FileSystemEmployeeService service = FileSystemEmployeeService.getInstance();

    public static void run() {
        while (true) {
            System.out.println("Choose operation to perform on Employees: \n 1.Get employee by id \n 2.Add employee \n 3.Delete Employee \n 4.Update employee \n 5.Show all employees \n 6.Exit");
            try {
                int option = scanner.nextInt();
                scanner.nextLine();
                switch (option) {
                    case 1:
                        System.out.print("Enter Employee employeeId: ");
                        Employee returnedEmployee = service.getEmployeeById(scanner.nextInt());
                        if (returnedEmployee != null) {
                            System.out.println(returnedEmployee);
                        }
                        break;
                    case 2:
                        Employee employee = UserInput.getEmployee();
                        employee.setEmployeeId(service.generateEmployeeId());
                        System.out.print("Enter Department name: ");
                        employee.getDepartment().setDepartmentName(scanner.nextLine());
                        service.addEmployee(employee);
                        break;
                    case 3:
                        System.out.print("Enter Employee employeeId: ");
                        service.deleteEmployee(scanner.nextInt());
                        scanner.nextLine();
                        break;
                    case 4:
                        System.out.print("Enter Employee id: ");
                        int currentEmployeeID = scanner.nextInt();
                        scanner.nextLine();
                        Employee currentEmployee = service.getEmployeeById(currentEmployeeID);
                        Employee updatedEmployee = UserInput.getUpdatedEmployee();
                        if (updatedEmployee.getEmployeeName() == null)
                            updatedEmployee.setEmployeeName(currentEmployee.getEmployeeName());
                        if (updatedEmployee.getEmail() == null) updatedEmployee.setEmail(currentEmployee.getEmail());
                        if (updatedEmployee.getDepartment() == null)
                            updatedEmployee.setDepartment(currentEmployee.getDepartment());
                        else {
                            System.out.println("Enter Department name: ");
                            updatedEmployee.getDepartment().setDepartmentName(scanner.nextLine());
                        }
                        if (updatedEmployee.getAddress() == null)
                            updatedEmployee.setAddress(currentEmployee.getAddress());
                        updatedEmployee.setEmployeeId(currentEmployee.getEmployeeId());
                        service.updateEmployee(currentEmployeeID, updatedEmployee);
                        break;
                    case 5:
                        List<Employee> employeeList = service.getAllEmployee();
                        for (Employee eachEmployee : employeeList) {
                            System.out.println(eachEmployee);
                        }
                        break;

                    case 6:
                        System.exit(0);
                    default:
                        System.out.println("choose valid Operation");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Enter valid input " + e);
            }
        }
    }
}