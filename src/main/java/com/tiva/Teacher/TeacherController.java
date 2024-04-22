package com.tiva.Teacher;

import com.tiva.Student.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class TeacherController {
    private final Connection connection;
    private final Scanner scanner;

    private Teacher teacher;
    private final TeacherQuery teacherQuery;


    public TeacherController(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
        this.teacherQuery = new TeacherQuery(connection);
    }

    public TeacherController(Connection connection, String regNumber) throws SQLException {
        this.connection = connection;
        this.scanner = new Scanner(System.in);
        this.scanner.useDelimiter("\n");
        this.teacherQuery = new TeacherQuery(connection);
        this.teacher = new Teacher(regNumber);
    }

    protected List extractNames(String fullName) {
        String[] names = fullName.split(" ");
        return List.of(names);
    }

    protected String generateRegNumber() {
        return null;
    }

    protected String requestRegNumber() {
        System.out.print("Enter teacher's registration-number: ");
        String regNumber = scanner.next();

        return regNumber;
    }



    protected List requestTeacherDetails() {

        String regNumber = generateRegNumber();
        System.out.print("Enter teacher's first-name: ");
        String firstName = scanner.next();
        System.out.print("Enter teacher's last-name: ");
        String lastName = scanner.next();
        System.out.print("Enter teacher's email: ");
        String email = scanner.next();
        System.out.println("Enter teacher's course-in-charge:");
        String courseInCharge = scanner.next();

        Object[] teacherDetails = new Object[]{regNumber, firstName, lastName, email, courseInCharge};
        return List.of(teacherDetails);
    }

    protected boolean isTeacher(String regNumber) throws SQLException {
        // teacher = new Teacher(regNumber);
        ResultSet resultSet = teacherQuery.getTeacher(teacher);
        return resultSet.next();
    }

    public boolean isTeacher() throws SQLException {
        ResultSet resultSet = teacherQuery.getTeacher(teacher);
        return resultSet.next();
    }

    private void registerTeacher() {
        List teacherDetails = requestTeacherDetails();
        String regNumber = (String) teacherDetails.get(0);
        String firstName = (String) teacherDetails.get(1);
        String lastName = (String) teacherDetails.get(2);
        String email = (String) teacherDetails.get(3);
        String courseInCharge = (String) teacherDetails.get(4);

        teacher = new Teacher(regNumber, firstName, lastName, email, courseInCharge);
        teacherQuery.addTeacher(teacher);
    }

    private void deleteTeacher() throws SQLException {
        String regNumber = requestRegNumber();
        teacher = new Teacher(regNumber);
        if (isTeacher(teacher.getRegNumber())) {
            teacherQuery.removeTeacher(teacher);
        } else {
            System.out.println("Teacher data does not exist.");
        }
    }

    public void loadTeacherDetails() throws SQLException {
        ResultSet resultSet = teacherQuery.getTeacher(teacher);
        while (resultSet.next()) {
            String fullName = resultSet.getString("full_name");
            String email = resultSet.getString("email");
            String courseInCharge = resultSet.getString("course_in_charge");

            List names = extractNames(fullName);
            String firstName = (String) names.get(0);
            String lastName = (String) names.get(1);
            teacher.setParams(firstName, lastName, email, courseInCharge);
        }
    }

    public void displayDashboard() {
        System.out.println("-----------------");
        System.out.println("TEACHER DASHBOARD");
        System.out.println("-----------------");
        System.out.println("1. View profile");
        System.out.println("2. Edit profile");
        System.out.println("3. View course details");
        System.out.println("4. View course students");
        System.out.println("5. LOGOUT");
    }

    public void displayEditOptions() {
        System.out.println("---------------------");
        System.out.println("EDIT TEACHER PROFILE");
        System.out.println("---------------------");
        System.out.println("1. Edit email");
        System.out.println("2. GO BACK");
    }

    public void displayProfile() throws SQLException {
        if (isTeacher(teacher.getRegNumber())) {
            ResultSet resultSet = teacherQuery.getTeacher(teacher);
            while (resultSet.next()) {
                String regNumber = resultSet.getString("reg_number");
                String fullName = resultSet.getString("full_name");
                String email = resultSet.getString("email");
                String courseInCharge = resultSet.getString("course_in_charge");

                System.out.println();
                System.out.println("+--------++---------------------------++------------------------------------------++-------------+");
                System.out.println("| ID     || NAME                      || EMAIL                                    || COURSE      |");
                System.out.println("+--------++---------------------------++------------------------------------------++-------------+");
                System.out.printf("| %-6s || %-25s || %-40s || %-11s |\n", regNumber, fullName, email, courseInCharge);
                System.out.println("+--------++---------------------------++------------------------------------------++-------------+");
                System.out.println();
            }
        } else {
            System.out.println("Teacher data does not exist.");
        }

    }

    public void changeEmail() throws SQLException {
        // is a function requestNewEmail necessary?
        System.out.print("Enter new email: ");
        String newEmail = scanner.next();

        teacher.setEmail(newEmail);
        teacherQuery.updateEmail(teacher);
    }

    public String getCourseInCharge() {
        return teacher.getCourseInCharge();
    }
}
