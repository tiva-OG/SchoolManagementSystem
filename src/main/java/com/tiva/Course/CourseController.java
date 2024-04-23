package com.tiva.Course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CourseController {
    private final Scanner scanner;

    private Course course;
    private final CourseQuery courseQuery;

    public CourseController(Connection connection) {
        this.scanner = new Scanner(System.in);
        this.scanner.useDelimiter("\n");
        this.courseQuery = new CourseQuery(connection);
    }

    protected List requestCourseDetails() {
        System.out.print("Enter the course code: ");
        String courseCode = scanner.next();
        System.out.print("Enter the course title: ");
        String courseTitle = scanner.next();
        System.out.print("Enter the course level: ");
        int courseLevel = scanner.nextInt();
        System.out.print("Enter the course credit-units: ");
        int creditUnits = scanner.nextInt();

        Object[] courseDetails = new Object[]{courseCode, courseTitle, courseLevel, creditUnits};
        return List.of(courseDetails);
    }

    public String requestCourseCode() {
        System.out.print("Enter the course code: ");
        String courseCode = scanner.next();

        return courseCode;
    }

    public boolean isCourse(String courseCode) throws SQLException {
        course = new Course(courseCode);
        ResultSet resultSet = courseQuery.getCourse(course);
        return resultSet.next();
    }

    public int getCreditUnits(String courseCode) throws SQLException {
        course = new Course(courseCode);
        ResultSet resultSet = courseQuery.getCourse(course);
        int creditUnits = 0;
        while (resultSet.next()) {
            creditUnits = resultSet.getInt("credit_units");
        }

        return creditUnits;
    }

    public void registerCourse() throws SQLException {
        List courseDetails = requestCourseDetails();
        String courseCode = (String) courseDetails.get(0);
        String courseTitle = (String) courseDetails.get(1);
        int courseLevel = (int) courseDetails.get(2);
        int creditUnits = (int) courseDetails.get(3);

        if (isCourse(courseCode)) {
            System.out.println("Course data already exists.");
        } else {
            course = new Course(courseCode, courseTitle, courseLevel, creditUnits);
            courseQuery.addCourse(course);
        }
    }

    public void deleteCourse() throws SQLException {
        String courseCode = requestCourseCode();
        course = new Course(courseCode);

        if (isCourse(courseCode)) {
            courseQuery.removeCourse(course);
        } else {
            System.out.println("Course data does not exist.");
        }
    }

    public void displayCourse(String courseCode) throws SQLException {
        course = new Course(courseCode);
        ResultSet resultSet = courseQuery.getCourse(course);
        while (resultSet.next()) {
            String courseTitle = resultSet.getString("title");
            int level = resultSet.getInt("level");
            int creditUnits = resultSet.getInt("credit_units");

            System.out.println();
            System.out.println("+-------------++----------------------------------++----------++-----+");
            System.out.println("| COURSE CODE || COURSE TITLE                     || LEVEL    || CU  |");
            System.out.println("+-------------++----------------------------------++----------++-----+");
            System.out.printf("| %-11s || %-32s || %-8s || %-3s |\n", courseCode, courseTitle, level, creditUnits);
            System.out.println("+-------------++----------------------------------++----------++-----+");
            System.out.println();
        }
    }
}
