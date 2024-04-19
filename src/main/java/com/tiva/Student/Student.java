package com.tiva.Student;

public class Student {

    private String regNumber;
    private String firstName;
    private String lastName;
    private String email;
    private int level;
    private int maxCreditUnits;
    private int minCreditUnits;
    private int registeredCreditUnits;


    public Student(String regNumber, String firstName, String lastName, String email, int level) {
        this.regNumber = regNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.level = level;
    }
}
