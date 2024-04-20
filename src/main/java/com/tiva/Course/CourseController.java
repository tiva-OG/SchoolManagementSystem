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

    public CourseController(Connection connection, Scanner scanner) {
        this.scanner = scanner;
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

    public boolean isCourse(String courseCode) {
        try {
            course = new Course(courseCode);
            ResultSet resultSet = courseQuery.getCourse(course);
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public void registerCourse() {
        List courseDetails = requestCourseDetails();
        String courseCode = (String) courseDetails.get(0);
        String courseTitle = (String) courseDetails.get(1);
        int courseLevel = (int) courseDetails.get(2);
        int creditUnits = (int) courseDetails.get(3);

        course = new Course(courseCode, courseTitle, courseLevel, creditUnits);
        courseQuery.addCourse(course);
    }

    public void deleteCourse() {
        String courseCode = requestCourseCode();
        course = new Course(courseCode);
        if (isCourse(course.getCourseCode())) {
            courseQuery.removeCourse(course);
        } else {
            System.out.println("Course data does not exist.");
        }
    }
}
