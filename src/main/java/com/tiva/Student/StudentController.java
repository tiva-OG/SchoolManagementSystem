package com.tiva.Student;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class StudentController {
    private final Connection connection;
    private final Scanner scanner;

    private Student student;
    private final StudentQuery studentQuery;


    public StudentController(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
        this.studentQuery = new StudentQuery(connection);
    }

    protected String generateRegNumber() {
        return null;
    }

    protected int getMaxCreditUnits(int level) {
        int maxCreditUnits = 0;

        switch (level) {
            case 100, 200 -> {maxCreditUnits = 48;}
            case 300, 400 -> {maxCreditUnits = 32;}
            case 500 -> {maxCreditUnits = 28;}
        }

        return maxCreditUnits;
    }

    protected List requestStudentDetails() {
        String regNumber;
        String firstName;
        String lastName;
        String email;
        int level;

        regNumber = generateRegNumber();
        System.out.print("Enter student's first-name: ");
        firstName = scanner.next();
        System.out.print("Enter student's last-name: ");
        lastName = scanner.next();
        System.out.print("Enter student's email: ");
        email = scanner.next();
        System.out.print("Enter student's level: ");
        level = scanner.nextInt();

        Object[] studentDetails = new Object[]{regNumber, firstName, lastName, email, level};
        return List.of(studentDetails);
    }

    private void registerStudent() {
        List studentDetails = requestStudentDetails();
        String regNumber = (String) studentDetails.get(0);
        String firstName = (String) studentDetails.get(1);
        String lastName = (String) studentDetails.get(2);
        String email = (String) studentDetails.get(3);
        int level = (int) studentDetails.get(4);
        int maxCreditUnits = getMaxCreditUnits(level);

        student = new Student(regNumber, firstName, lastName, email, level, maxCreditUnits);
        studentQuery.addStudent(student);
    }
}
