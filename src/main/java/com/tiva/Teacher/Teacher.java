package com.tiva.Teacher;

public class Teacher {

    private String regNumber;
    private String firstName;
    private String lastName;
    private String email;

    public Teacher(String regNumber, String firstName, String lastName, String email) {
        this.regNumber = regNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public Teacher(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }
}
