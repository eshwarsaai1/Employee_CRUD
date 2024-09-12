package com.training.CRUDEmployee.models;

public class Department {
    private int departmentId;
    private String departmentName;

    public Department(int id) {
        departmentId = id;
    }

    public Department(int id, String name) {
        departmentId = id;
        departmentName = name;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int dept_id) {
        this.departmentId = dept_id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    @Override
    public String toString() {
        return "{\n \t Department Id: " + departmentId +
                "\n \t Department Name: " + departmentName + "\n }";
    }
}