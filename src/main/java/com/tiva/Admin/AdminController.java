package com.tiva.Admin;

import com.tiva.Course.CourseController;
import com.tiva.Student.StudentController;
import com.tiva.Teacher.TeacherController;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminController {

    private final Admin admin;
    private final Scanner scanner;
    private final CourseController courseController;
    private final StudentController studentController;
    private final TeacherController teacherController;

    public AdminController(Connection connection) {
        this.admin = new Admin();
        this.courseController = new CourseController(connection);
        this.studentController = new StudentController(connection);
        this.teacherController = new TeacherController(connection);
        this.scanner = new Scanner(System.in);
        this.scanner.useDelimiter("\n");
    }
    /*
    - register Teacher with courseInCharge
    - register new Student
    - register new Course
    - delete Teacher
    - delete Student
    - delete Course
     */

    public String getAdminUsername() {
        return admin.getUsername();
    }

    public String getAdminPassword() {
        return admin.getPassword();
    }

    public String requestUsername() {
        System.out.print("Enter admin username: ");
        String username = scanner.next();

        return username;
    }

    public String requestPassword() {
        System.out.print("Enter admin password: ");
        String password = scanner.next();

        return password;
    }

    public void displayDashboard() {
        System.out.println();
        System.out.println("<<<<<<< >>>>>>>");
        System.out.println("ADMIN DASHBOARD");
        System.out.println("<<<<<<< >>>>>>>");
        System.out.println("1. Register a New Teacher");
        System.out.println("2. Register a New Student");
        System.out.println("3. Register a New Course");
        System.out.println("4. Delete Teacher");
        System.out.println("5. Delete Student");
        System.out.println("6. Delete Course");
        System.out.println("7. Change Teacher's course");
        System.out.println("8. LOGOUT");
    }

    public void registerCourse() throws SQLException {
        courseController.registerCourse();
    }

    public void registerStudent() throws SQLException {
        studentController.registerStudent();
    }

    public void registerTeacher() throws SQLException {
        teacherController.registerTeacher();
    }

    public void deleteCourse() throws SQLException {
        courseController.deleteCourse();
    }

    public void deleteStudent() throws SQLException {
        studentController.deleteStudent();
    }

    public void deleteTeacher() throws SQLException {
        teacherController.deleteTeacher();
    }

    public void changeTeacherCourseInCharge() throws SQLException {
        teacherController.changeCourseInCharge();
    }
}
