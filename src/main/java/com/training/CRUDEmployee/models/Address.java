package com.training.CRUDEmployee.models;

public class Address {
    private String location;
    private int pinCode;

    public Address(String location, int pinCode) {
        this.location = location;
        this.pinCode = pinCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPinCode() {
        return pinCode;
    }

    public void setPinCode(int pinCode) {
        this.pinCode = pinCode;
    }

    @Override
    public String toString() {
        return "{ \n \t Location: " + location +
                "\n \t Pincode: " + pinCode + " \n }";
    }
}