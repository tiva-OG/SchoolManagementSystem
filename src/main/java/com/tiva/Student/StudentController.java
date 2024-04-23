package com.tiva.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class StudentController {
    private final Scanner scanner;
    private Student student;
    private final StudentQuery studentQuery;


    public StudentController(Connection connection) {
        this.studentQuery = new StudentQuery(connection);
        this.scanner = new Scanner(System.in);
        this.scanner.useDelimiter("\n");
    }

    public StudentController(Connection connection, String regNumber) {
        this.studentQuery = new StudentQuery(connection);
        this.student = new Student(regNumber);
        this.scanner = new Scanner(System.in);
        this.scanner.useDelimiter("\n");
    }

    public void changeEmail() throws SQLException {
        System.out.print("Enter new email: ");
        String newEmail = scanner.next();

        student.setEmail(newEmail);
        studentQuery.updateEmail(student);
    }

    public void changeLevel() throws SQLException {
        System.out.print("Enter new level: ");
        int newLevel = scanner.nextInt();

        student.setLevel(newLevel);
        studentQuery.updateLevel(student);
    }

    public void deleteStudent() throws SQLException {
        System.out.print("Enter student's registration-number: ");
        String regNumber = scanner.next();
        student = new Student(regNumber);

        if (isStudent(regNumber)) {
            studentQuery.removeStudent(student);
        } else {
            System.out.println("Student data does not exist.");
        }
    }

    public void displayDashboard() {
        System.out.println("-----------------");
        System.out.println("STUDENT DASHBOARD");
        System.out.println("-----------------");
        System.out.println("1. View profile");
        System.out.println("2. Edit profile");
        System.out.println("3. View registered courses");
        System.out.println("4. Register new course");
        System.out.println("5. LOGOUT");
    }

    public void displayEditOptions() {
        System.out.println("---------------------");
        System.out.println("EDIT STUDENT PROFILE");
        System.out.println("---------------------");
        System.out.println("1. Edit email");
        System.out.println("2. Edit level");
        System.out.println("3. GO BACK");
    }

    public void displayProfile() throws SQLException {
        ResultSet resultSet = studentQuery.getStudent(student);
        while (resultSet.next()) {
            String regNumber = resultSet.getString("reg_number");
            String fullName = resultSet.getString("full_name");
            String email = resultSet.getString("email");
            int level = resultSet.getInt("level");
            int regCreditUnits = resultSet.getInt("reg_credit_units");

            System.out.println();
            System.out.println("+------------++----------------------++------------------------------------------++------------++------------+");
            System.out.println("| ID         || NAME                 || EMAIL                                    || LEVEL      || CU         |");
            System.out.println("+------------++----------------------++------------------------------------------++------------++------------+");
            System.out.printf("| %-10s || %-20s || %-40s || %-10s || %-10s |\n", regNumber, fullName, email, level, regCreditUnits);
            System.out.println("+------------++----------------------++------------------------------------------++------------++------------+");
            System.out.println();


        }
    }

    public void loadStudentDetails() throws SQLException {
        ResultSet resultSet = studentQuery.getStudent(student);
        while (resultSet.next()) {
            String fullName = resultSet.getString("full_name");
            String email = resultSet.getString("email");
            int level = resultSet.getInt("level");
            int registeredCreditUnits = resultSet.getInt("reg_credit_units");

            List names = extractNames(fullName);
            String firstName = (String) names.get(0);
            String lastName = (String) names.get(1);
            student.setParams(firstName, lastName, email, level, registeredCreditUnits);
        }
    }

    protected List extractNames(String fullName) {
        String[] names = fullName.split(" ");

        return List.of(names);
    }

    protected String generateRegNumber() {
        Random random = new Random();
        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        return abc.charAt(random.nextInt(abc.length())) + String.valueOf(random.nextInt(0, 9)) + "/"
                + random.nextInt(0, 9) + random.nextInt(0, 9) + "/"
                + abc.charAt(random.nextInt(abc.length())) + random.nextInt(0, 9) + random.nextInt(0, 9);
    }

    protected int getMaxCreditUnits(int level) {
        int maxCreditUnits = 0;

        switch (level) {
            case 100 -> maxCreditUnits = 20;
            case 200 -> maxCreditUnits = 24;
            case 300 -> maxCreditUnits = 28;
            case 400 -> maxCreditUnits = 22;
            case 500 -> maxCreditUnits = 26;
        }

        return maxCreditUnits;
    }

    public int getMaxCreditUnits() {
        return getMaxCreditUnits(student.getLevel());
    }

    public Student getStudent() {
        return student;
    }

    protected boolean isStudent(String regNumber) throws SQLException {
        student = new Student(regNumber);
        ResultSet resultSet = studentQuery.getStudent(student);
        return resultSet.next();
    }

    public boolean isStudent() throws SQLException {
        ResultSet resultSet = studentQuery.getStudent(student);
        return resultSet.next();
    }

    public void registerStudent() throws SQLException {
        List studentDetails = requestStudentDetails();
        String regNumber = (String) studentDetails.get(0);
        String firstName = (String) studentDetails.get(1);
        String lastName = (String) studentDetails.get(2);
        String email = (String) studentDetails.get(3);
        int level = (int) studentDetails.get(4);

        if (isStudent(regNumber)) {
            System.out.println("Student data already exists.");
        } else {
            student = new Student(regNumber, firstName, lastName, email, level);
            studentQuery.addStudent(student);
            System.out.println(student);
        }
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

    public void updateRegisteredCreditUnits(int creditUnits) throws SQLException {
        student.setRegisteredCreditUnits(creditUnits);
        studentQuery.updateRegisteredCreditUnits(student);
    }
}
