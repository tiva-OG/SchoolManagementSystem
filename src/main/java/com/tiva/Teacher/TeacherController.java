package com.tiva.Teacher;

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

    protected String generateRegNumber() {
        return null;
    }

    protected String requestRegNumber() {
        System.out.print("Enter teacher's registration-number: ");
        String regNumber = scanner.next();

        return regNumber;
    }

    protected List requestTeacherDetails() {
        String regNumber;
        String firstName;
        String lastName;
        String email;

        regNumber = generateRegNumber();
        System.out.print("Enter teacher's first-name: ");
        firstName = scanner.next();
        System.out.print("Enter teacher's last-name: ");
        lastName = scanner.next();
        System.out.print("Enter teacher's email: ");
        email = scanner.next();

        Object[] teacherDetails = new Object[]{regNumber, firstName, lastName, email};
        return List.of(teacherDetails);
    }

    protected boolean isTeacher(String regNumber) {
        try {
            teacher = new Teacher(regNumber);
            ResultSet resultSet = teacherQuery.getTeacher(teacher);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    private void registerTeacher() {
        List teacherDetails = requestTeacherDetails();
        String regNumber = (String) teacherDetails.get(0);
        String firstName = (String) teacherDetails.get(1);
        String lastName = (String) teacherDetails.get(2);
        String email = (String) teacherDetails.get(3);

        teacher = new Teacher(regNumber, firstName, lastName, email);
        teacherQuery.addTeacher(teacher);
    }

    private void deleteTeacher() {
        String regNumber = requestRegNumber();
        teacher = new Teacher(regNumber);
        if (isTeacher(teacher.getRegNumber())) {
            teacherQuery.removeTeacher(teacher);
        } else {
            System.out.println("Teacher data does not exist.");
        }
    }
}
