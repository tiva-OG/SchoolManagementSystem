package com.tiva.Student;

public class Student {

    private final String regNumber;
    private String firstName;
    private String lastName;
    private String email;
    private int level;
    private int maxCreditUnits;
    private int minCreditUnits;
    private int registeredCreditUnits;


    public Student(String regNumber, String firstName, String lastName, String email, int level, int maxCreditUnits) {
        this.regNumber = regNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.level = level;
        this.maxCreditUnits = maxCreditUnits;
        this.minCreditUnits = 28;
        this.registeredCreditUnits = 0;
    }

    public Student(String regNumber) {
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

    public int getLevel() {
        return level;
    }

    public int getMaxCreditUnits() {
        return maxCreditUnits;
    }

    public int getMinCreditUnits() {
        return minCreditUnits;
    }

    public int getRegisteredCreditUnits() {
        return registeredCreditUnits;
    }

    public String getFullName() {
        return lastName + " " + firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
