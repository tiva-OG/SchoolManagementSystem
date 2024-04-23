package com.tiva.CourseStudent;

import com.tiva.Course.Course;
import com.tiva.Course.CourseController;
import com.tiva.Student.Student;
import com.tiva.Student.StudentController;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseStudentController {

    private final CourseStudentQuery courseStudentQuery;
    private Course course;
    private CourseController courseController;
    private Student student;
    private StudentController studentController;


    public CourseStudentController(Connection connection) {
        this.courseStudentQuery = new CourseStudentQuery(connection);
    }

    public CourseStudentController(Connection connection, StudentController studentController) {
        this.courseStudentQuery = new CourseStudentQuery(connection);
        this.courseController = new CourseController(connection);
        this.studentController = studentController;
        this.student = studentController.getStudent();
    }

    public void displayStudentsOfferingCourse(String courseCode) throws SQLException {
        // String courseCode = courseController.requestCourseCode();
        course = new Course(courseCode);
        ResultSet resultSet = courseStudentQuery.getStudentsOfferingCourse(course);

        System.out.println();
        System.out.println("+------------------------------------+");
        System.out.printf("| %-34s |\n", courseCode);
        System.out.println("+------------++----------------------+");
        System.out.println("| ID         || NAME                 |");
        System.out.println("+------------++----------------------+");

        int totalStudents = 0;
        while (resultSet.next()) {
            String regNumber = resultSet.getString("reg_number");
            String fullName = resultSet.getString("full_name");
            totalStudents += 1;

            System.out.printf("| %-10s || %-20s |\n", regNumber, fullName);
            System.out.println("+------------++----------------------+");
        }
        System.out.printf("| Total No. of Students      ||  %-3s |\n", totalStudents);
        System.out.println("+------------++----------------------+");
        System.out.println();
    }

    public void displayRegisteredCourses() throws SQLException {
        ResultSet resultSet = courseStudentQuery.getCoursesRegisteredByStudent(student);
        System.out.println();
        System.out.println("+-------------++----------------------------------++-----+");
        System.out.println("| COURSE CODE || COURSE TITLE                     || CU  |");
        System.out.println("+-------------++----------------------------------++-----+");

        int registeredCreditUnits = 0;
        while (resultSet.next()) {
            String courseCode = resultSet.getString("code");
            String courseTitle = resultSet.getString("title");
            int creditUnits = resultSet.getInt("credit_units");
            registeredCreditUnits += creditUnits;

            System.out.printf("| %-11s || %-32s || %-3s |\n", courseCode, courseTitle, creditUnits);
            System.out.println("+-------------++----------------------------------++-----+");
        }
        System.out.printf("| Total Credit-Units Registered                   || %-3s |\n", registeredCreditUnits);
        System.out.println("+-------------++----------------------------------++-----+");
        System.out.println();
    }

    public void displayUnregisteredCourses() throws SQLException {
        ResultSet resultSet = courseStudentQuery.getCoursesUnregisteredByStudent(student);
        System.out.println();
        System.out.println("+-------------++----------------------------------++----------++-----+");
        System.out.println("| COURSE CODE || COURSE TITLE                     || LEVEL    || CU  |");
        System.out.println("+-------------++----------------------------------++----------++-----+");

        while (resultSet.next()) {
            String courseCode = resultSet.getString("code");
            String courseTitle = resultSet.getString("title");
            int level = resultSet.getInt("level");
            int creditUnits = resultSet.getInt("credit_units");

            System.out.printf("| %-11s || %-32s || %-8s || %-3s |\n", courseCode, courseTitle, level, creditUnits);
            System.out.println("+-------------++----------------------------------++----------++-----+");
        }
        System.out.println();
    }

    public int getRegisteredCreditUnits() throws SQLException {
        ResultSet resultSet = courseStudentQuery.getCoursesRegisteredByStudent(student);
        int registeredCreditUnits = 0;
        while (resultSet.next()) {
            int creditUnits = resultSet.getInt("credit_units");
            registeredCreditUnits += creditUnits;
        }

        return registeredCreditUnits;
    }

    public void registerForCourse(String courseCode) throws SQLException {
        // String courseCode = courseController.requestCourseCode();

        int maxCreditUnits = studentController.getMaxCreditUnits();
        int registeredCreditUnits = getRegisteredCreditUnits();
        int creditUnits = courseController.getCreditUnits(courseCode);

        if ((registeredCreditUnits + creditUnits) > maxCreditUnits) {
            System.out.println("Cannot exceed maximum credit-units allowed.");
            return;
        }

        if (courseController.isCourse(courseCode)) {
            course = new Course(courseCode);
            courseStudentQuery.registerForCourse(student, course);
            System.out.println("Current registered credit-units: " + registeredCreditUnits);
            System.out.println("Course credit-units: " + creditUnits);
            System.out.println("New registered credit-units: " + (registeredCreditUnits+creditUnits));
            studentController.updateRegisteredCreditUnits(registeredCreditUnits + creditUnits);
        } else if (!courseCode.equals("q")) {
            System.out.println("Course data does not exist.");
        }
    }

    public String requestCourseCode() {
        return courseController.requestCourseCode();
    }

    public void unregisterForCourse(Student student) throws SQLException {
        // also consider checking if student has already registered for the course
        String courseCode = courseController.requestCourseCode();
        if (courseController.isCourse(courseCode)) {
            course = new Course(courseCode);
            courseStudentQuery.unregisterForCourse(student, course);
        } else {
            System.out.println("Course data does not exist.");
        }
    }

}
