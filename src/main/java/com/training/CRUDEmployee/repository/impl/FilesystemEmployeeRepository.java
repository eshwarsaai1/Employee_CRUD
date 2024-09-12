package com.training.CRUDEmployee.repository.impl;

import com.training.CRUDEmployee.exceptions.EmployeeNotFound;
import com.training.CRUDEmployee.models.Address;
import com.training.CRUDEmployee.models.Department;
import com.training.CRUDEmployee.models.Employee;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.training.CRUDEmployee.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FilesystemEmployeeRepository implements EmployeeRepository {
    File file;
    private static FilesystemEmployeeRepository filesystemEmployeeRepository;
    private static final Logger logger = LoggerFactory.getLogger(FilesystemEmployeeRepository.class);

    private FilesystemEmployeeRepository(String path) {
        try {
            file = new File(path);
            if (file.createNewFile()) {
                System.out.println("File created");
            } else {
                System.out.println("File already Exist");
            }
        } catch (Exception e) {
            logger.error("e: ", e);
        }
    }

    public static FilesystemEmployeeRepository getInstance(String path) {
        if (filesystemEmployeeRepository == null) {
            filesystemEmployeeRepository = new FilesystemEmployeeRepository(path);
        }
        return filesystemEmployeeRepository;
    }

    @Override
    public Employee getEmployeeById(int employeeId) {
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String currentLine = fileReader.nextLine();
                String[] data = currentLine.split(",");
                if (Integer.parseInt(data[0]) == employeeId) {
                    Employee employee = stringToEmployee(currentLine);
                    logger.info("Employee returned successfully");
                    return employee;
                }
            }
            throw new EmployeeNotFound();
        } catch (Exception e) {
            logger.error("e: ", e);
        }
        return null;
    }

    @Override
    public void addEmployee(Employee employee) {
        if (file.canWrite()) {
            Scanner sc = new Scanner(System.in);
            try {
                String data = "";
                Scanner fileReader = new Scanner(file);
                while (fileReader.hasNextLine()) {
                    data = data + fileReader.nextLine() + "\n";
                }
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(data + employeeToString(employee) + "\n");
                fileWriter.close();
                System.out.println("Added successfully");
            } catch (Exception e) {
                System.out.println(e);
            }
        } else {
            System.out.println("Cannot Write into this file");
        }
    }

    @Override
    public List<Employee> getAllEmployee() {
        try {
            Scanner fileReader = new Scanner(file);
            List<Employee> employeeList = new ArrayList<>();
            while (fileReader.hasNextLine()) {
                Employee employee = stringToEmployee(fileReader.nextLine());
                employeeList.add(employee);
            }
            return employeeList;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public void updateEmployee(int employeeId, Employee employee) {
        System.out.println("Started");
        try {
            String fileData = "";
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String currentLine = fileReader.nextLine();
                String[] data = currentLine.split(",");
                if (Integer.parseInt(data[0]) == employeeId) {
                    fileData = fileData + employeeToString(employee) + "\n";
                } else {
                    fileData = fileData + currentLine + "\n";
                }
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(fileData);
            fileWriter.close();
            System.out.println("Updated successfully");
        } catch (Exception e) {
            logger.error("e: ", e);
        }
    }

    @Override
    public void deleteEmployee(int employeeId) {
        try {
            String fileData = "";
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String currentLine = fileReader.nextLine();
                String[] data = currentLine.split(",");
                if (Integer.parseInt(data[0]) != employeeId) {
                    fileData = fileData + currentLine + "\n";
                }
            }
            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(fileData);
            fileWriter.close();
            System.out.println("Deleted successfully");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int generateEmployeeId() {
        int maxId = 0;
        try {
            Scanner fileReader = new Scanner(file);
            while (fileReader.hasNextLine()) {
                String currentLine = fileReader.nextLine();
                String[] data = currentLine.split(",");
                maxId = Math.max(maxId, Integer.parseInt(data[0]));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return maxId + 1;
    }

    public String employeeToString(Employee employee){
        return employee.getEmployeeId() + "," +
                employee.getEmployeeName() + "," +
                employee.getEmail() + "," +
                employee.getDepartment().getDepartmentId() + "," +
                employee.getDepartment().getDepartmentName() + "," +
                employee.getAddress().getLocation() + "," +
                employee.getAddress().getPinCode();
    }

    public Employee stringToEmployee(String string){
        String[] data = string.split(",");
        Employee employee = new Employee(Integer.parseInt(data[0]), data[1]);
        employee.setEmail(data[2]);
        employee.setDepartment(new Department(Integer.parseInt(data[3]), data[4]));
        employee.setAddress(new Address(data[5], Integer.parseInt(data[6])));
        return employee;
    }

}