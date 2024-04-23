package com.tiva.Student;

public class Student {

    private final String regNumber;
    private String firstName;
    private String lastName;
    private String email;
    private int level;
    private int registeredCreditUnits;


    public Student(String regNumber, String firstName, String lastName, String email, int level) {
        this.regNumber = regNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.level = level;
        this.registeredCreditUnits = 0;
    }

    public Student(String regNumber) {
        this.regNumber = regNumber;
    }

    public void setParams(String firstName, String lastName, String email, int level, int registeredCreditUnits) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.level = level;
        this.registeredCreditUnits = registeredCreditUnits;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setRegisteredCreditUnits(int registeredCreditUnits) {
        this.registeredCreditUnits = registeredCreditUnits;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getLevel() {
        return level;
    }

    public int getRegisteredCreditUnits() {
        return registeredCreditUnits;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "WELCOME " + lastName + " " + firstName +
                " TO " + level + "L" +
                "; YOUR REG. NUMBER: <<< " + regNumber + " >>>";
    }
}
