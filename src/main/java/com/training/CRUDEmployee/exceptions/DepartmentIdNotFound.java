package com.training.CRUDEmployee.exceptions;

public class DepartmentIdNotFound extends Exception {
    public DepartmentIdNotFound() {
        super("Entered Department Id invalid!!");
    }
}