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


    public StudentController(Connection connection) throws SQLException {
        this.connection = connection;
        this.scanner = new Scanner(System.in);
        this.scanner.useDelimiter("\n");
        this.studentQuery = new StudentQuery(connection);
    }

    public StudentController(Connection connection, String regNumber) throws SQLException {
        this.connection = connection;
        this.studentQuery = new StudentQuery(connection);
        this.student = new Student(regNumber);
        this.scanner = new Scanner(System.in);
        this.scanner.useDelimiter("\n");
    }

    protected List extractNames(String fullName) {
        String[] names = fullName.split(" ");

        return List.of(names);
    }

    protected String generateRegNumber() {
        return null;
    }

    protected boolean isStudent(String regNumber) throws SQLException {
        // student = new Student(regNumber);
        ResultSet resultSet = studentQuery.getStudent(student);
        return resultSet.next();
    }

    public Student getStudent() {
        return student;
    }

    public int getMaxCreditUnits() {
        int level = student.getLevel();
        int maxCreditUnits = 0;

        switch (level) {
            case 100 -> {
                maxCreditUnits = 20;
            }
            case 200 -> {
                maxCreditUnits = 24;
            }
            case 300 -> {
                maxCreditUnits = 28;
            }
            case 400 -> {
                maxCreditUnits = 22;
            }
            case 500 -> {
                maxCreditUnits = 26;
            }
        }

        return maxCreditUnits;
    }

    public String requestRegNumber() {
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

    public boolean isStudent() throws SQLException {
        // student = new Student(regNumber);
        ResultSet resultSet = studentQuery.getStudent(student);
        return resultSet.next();
    }

    private void registerStudent() throws SQLException {
        List studentDetails = requestStudentDetails();
        String regNumber = (String) studentDetails.get(0);
        String firstName = (String) studentDetails.get(1);
        String lastName = (String) studentDetails.get(2);
        String email = (String) studentDetails.get(3);
        int level = (int) studentDetails.get(4);
        int maxCreditUnits = getMaxCreditUnits();

        // to handle the exception to a student that already exists,
        // use the fullName and email instead of regNumber since this
        // is randomly generated at registration
        if (isStudent(regNumber)) {
            System.out.println("Student data already exists.");
        } else {
            student = new Student(regNumber, firstName, lastName, email, level, maxCreditUnits);
            studentQuery.addStudent(student);
        }
    }

    public void changeEmail() throws SQLException {
        // is a function requestNewEmail necessary?
        System.out.print("Enter new email: ");
        String newEmail = scanner.next();

        student.setEmail(newEmail);
        studentQuery.updateEmail(student);
    }

    public void changeLevel() throws SQLException {
        // is a function requestNewLevel necessary?
        System.out.print("Enter new level: ");
        int newLevel = scanner.nextInt();

        student.setLevel(newLevel);
        studentQuery.updateLevel(student);
    }

    private void deleteStudent() throws SQLException {
        String regNumber = requestRegNumber();
        student = new Student(regNumber);
        if (isStudent(student.getRegNumber())) {
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

    public void displayProfile() {
        try {
            // String regNumber = requestRegNumber();
            // student = new Student(regNumber);
            if (isStudent(student.getRegNumber())) {
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
            } else {
                System.out.println("Student data does not exist.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
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
}
