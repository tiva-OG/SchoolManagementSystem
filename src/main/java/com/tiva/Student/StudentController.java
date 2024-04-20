package com.tiva.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    protected List extractNames(String fullName) {
        String[] names = fullName.split(" ");

        return List.of(names);
    }

    protected String generateRegNumber() {
        return null;
    }

    protected int getMaxCreditUnits(int level) {
        int maxCreditUnits = 0;

        switch (level) {
            case 100, 200 -> {
                maxCreditUnits = 48;
            }
            case 300, 400 -> {
                maxCreditUnits = 32;
            }
            case 500 -> {
                maxCreditUnits = 28;
            }
        }

        return maxCreditUnits;
    }

    protected String requestRegNumber() {
        System.out.print("Enter student's registration-number: ");
        String regNumber = scanner.next();

        return regNumber;
    }

    protected String requestNewEmail() {
        System.out.print("Enter the new email: ");
        String newEmail = scanner.next();

        return newEmail;
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

    protected boolean isStudent(String regNumber) {
        try {
            student = new Student(regNumber);
            ResultSet resultSet = studentQuery.getStudent(student);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void registerStudent() {
        List studentDetails = requestStudentDetails();
        String regNumber = (String) studentDetails.get(0);
        String firstName = (String) studentDetails.get(1);
        String lastName = (String) studentDetails.get(2);
        String email = (String) studentDetails.get(3);
        int level = (int) studentDetails.get(4);
        int maxCreditUnits = getMaxCreditUnits(level);

        if (isStudent(regNumber)) {
            System.out.println("Student data already exists.");
        } else {
            student = new Student(regNumber, firstName, lastName, email, level, maxCreditUnits);
            studentQuery.addStudent(student);
        }
    }

    private void deleteStudent() {
        String regNumber = requestRegNumber();
        student = new Student(regNumber);
        if (isStudent(student.getRegNumber())) {
            studentQuery.removeStudent(student);
        } else {
            System.out.println("Student data does not exist.");
        }
    }

    public void displayProfile() {
        try {
            String regNumber = requestRegNumber();
            student = new Student(regNumber);
            if (isStudent(student.getRegNumber())) {
                ResultSet resultSet = studentQuery.getStudent(student);
                while (resultSet.next()) {
                    String fullName = resultSet.getString("full_name");
                    String email = resultSet.getString("email");
                    int level = resultSet.getInt("level");
                    int regCreditUnits = resultSet.getInt("reg_credit_units");

                    System.out.println();
                    System.out.println("+----------++----------------------++--------------------------------++------------++------------+");
                    System.out.println("| ID       || NAME                 || EMAIL                          || LEVEL      || CU         |");
                    System.out.println("+----------++----------------------++--------------------------------++------------++------------+");
                    System.out.printf("| %-8s || %-20s || %-30s || %-10s || %-10s |\n", regNumber, fullName, email, level, regCreditUnits);
                    System.out.println("+-----++--------------------++--------------------------------++------------+");
                    System.out.println();
                }
            } else {
                System.out.println("Student data does not exist.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeEmail() {
        String regNumber = requestRegNumber();
        student = new Student(regNumber);
        if (isStudent(student.getRegNumber())) {
            String newEmail = requestNewEmail();
            student.setEmail(newEmail);
            studentQuery.updateEmail(student);
        } else {
            System.out.println("Student data does not exist.");
        }
        return;
    }
}
