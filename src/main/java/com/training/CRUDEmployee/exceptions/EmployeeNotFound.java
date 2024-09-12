package com.training.CRUDEmployee.exceptions;

public class EmployeeNotFound extends Exception {
    public EmployeeNotFound() {
        super("Employee not found!!");
    }
}