package com.training.CRUDEmployee.utill;

import com.training.CRUDEmployee.models.Address;
import com.training.CRUDEmployee.models.Department;
import com.training.CRUDEmployee.models.Employee;

import java.util.Scanner;

public class UserInput {
    private static final Scanner scanner = new Scanner(System.in);

    public static Employee getEmployee() {
        Employee employee = new Employee();
        System.out.print("Enter Employee Name: ");
        String employeeName = scanner.nextLine();
        employee.setEmployeeName(employeeName);
        employee.setEmail(getValidEmail());
        Department dept;
        System.out.print("Enter Employee dept_id:");
        int dept_id = scanner.nextInt();
        scanner.nextLine();
        Department department = new Department(dept_id);
        employee.setDepartment(department);
        System.out.print("Enter Employee location:");
        String location = scanner.nextLine();
        int pinCode = getValidPinCode();
        Address add = new Address(location, pinCode);
        employee.setAddress(add);
        return employee;
    }

    public static Employee getUpdatedEmployee() {
        System.out.println("choose what you want to update: \n 1.Employee name \n 2.Email \n 3.Department Id \n 4.Address \n 5.Exit");
        int option = scanner.nextInt();
        scanner.nextLine();
        Employee employee = new Employee();
        switch (option) {
            case 1:
                System.out.print("Enter new Employee name: ");
                employee.setEmployeeName(scanner.nextLine());
                break;
            case 2:
                employee.setEmail(getValidEmail());
                break;
            case 3:
                System.out.print("Enter new Department Id");
                employee.setDepartment(new Department(scanner.nextInt()));
                break;
            case 4:
                System.out.print("Enter new Location: ");
                String Location = scanner.nextLine();
                int pinCode = getValidPinCode();
                employee.setAddress(new Address(Location, pinCode));
                break;
            case 5:
                System.exit(0);
                break;
        }
        return employee;
    }

    public static String getValidEmail() {
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        while (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            System.out.print("Enter valid email Id: ");
            email = scanner.nextLine();
        }
        return email;
    }

    public static int getValidPinCode() {
        System.out.print("Enter PinCode: ");
        int pinCode = scanner.nextInt();
        scanner.nextLine();
        while (!((pinCode >= 100000) && (pinCode <= 999999))) {
            System.out.print("Enter valid pinCode: ");
            pinCode = scanner.nextInt();
            scanner.nextLine();
        }
        return pinCode;
    }
}