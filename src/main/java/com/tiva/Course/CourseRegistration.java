package com.tiva.Course;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class CourseRegistration {
    private Connection connection;
    private Scanner scanner;

    private Course course;
    private CourseQuery courseQuery;

    public CourseRegistration(Connection connection, Scanner scanner) {
        this.connection = connection;
        this.scanner = scanner;
        this.courseQuery = new CourseQuery(connection);
    }

    private List requestCourseDetails() {
        String courseCode;
        String courseTitle;
        int courseLevel;
        int creditUnits;

        System.out.print("Enter the course code: ");
        courseCode = scanner.next();
        System.out.print("Enter the course title: ");
        courseTitle = scanner.next();
        System.out.print("Enter the course level: ");
        courseLevel = scanner.nextInt();
        System.out.print("Enter the course credit-units: ");
        creditUnits = scanner.nextInt();

        Object[] courseDetails = new Object[]{courseCode, courseTitle, courseLevel, creditUnits};
        return List.of(courseDetails);
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
}
