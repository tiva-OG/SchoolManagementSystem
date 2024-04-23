package com.tiva.Teacher;

public class Teacher {

    private final String regNumber;
    private String firstName;
    private String lastName;
    private String email;
    private String courseInCharge;

    public Teacher(String regNumber, String firstName, String lastName, String email, String courseInCharge) {
        this.regNumber = regNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.courseInCharge = courseInCharge;
    }

    public Teacher(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getCourseInCharge() {
        return courseInCharge;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourseInCharge(String courseInCharge) {
        this.courseInCharge = courseInCharge;
    }

    public void setParams(String firstName, String lastName, String email, String courseInCharge) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.courseInCharge = courseInCharge;
    }

    @Override
    public String toString() {
        return "WELCOME " + lastName + " " + firstName +
                "; YOUR REG. NUMBER: <<< " + regNumber +
                " >>>; COURSE-IN-CHARGE: <<< " + courseInCharge + " >>>";
    }
}
