package com.tiva.School;

import com.tiva.Course.CourseController;
import com.tiva.CourseStudent.CourseStudentController;
import com.tiva.Student.StudentController;
import com.tiva.Teacher.TeacherController;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SchoolController {

    private Connection connection;
    private final Scanner scanner;

    public SchoolController(Connection connection) {
        this.connection = connection;
        this.scanner = new Scanner(System.in);
        this.scanner.useDelimiter("\n");
    }

    public void loginPortal() throws SQLException {
        String regNumber = requestUserRegNumber();
        UserType userType = getUserType(regNumber);

        if (userType.equals(UserType.STUDENT)) {

            // open the student's portal
            StudentController studentController = new StudentController(connection, regNumber);
            openStudentPortal(studentController);

        } else if (userType.equals(UserType.TEACHER)) {

            // open the teacher's portal
            TeacherController teacherController = new TeacherController(connection, regNumber);
            openTeacherPortal(teacherController);

        } else System.out.println("INVALID REGISTRATION NUMBER.");
    }

    private void openStudentPortal(StudentController studentController) throws SQLException {

        if (studentController.isStudent()) {
            studentController.loadStudentDetails();
            CourseStudentController courseStudentController = new CourseStudentController(connection, studentController);

            boolean stayLoggedIn = true;

            while (stayLoggedIn) {
                System.out.println();
                studentController.displayDashboard();
                System.out.print("Please enter a choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        // display student's profile
                        studentController.displayProfile();
                    }
                    case 2 -> {
                        // display courses registered by student
                        courseStudentController.displayRegisteredCourses();
                    }
                    case 3 -> {
                        // student register for new course
                        String courseCode;
                        do {
                            courseStudentController.displayUnregisteredCourses();
                            courseCode = courseStudentController.requestCourseCode();
                            courseStudentController.registerForCourse(courseCode);
                        } while (!courseCode.equals("q"));
                    }
                    case 4 -> {
                        // exit the system
                        stayLoggedIn = false;
                    }
                    default -> System.out.println("Please enter a valid choice.");
                }
            }
        } else {
            System.out.println("Student does not exist.");
        }
    }

    private void openTeacherPortal(TeacherController teacherController) throws SQLException {

        if (teacherController.isTeacher()) {
            teacherController.loadTeacherDetails();
            CourseController courseController = new CourseController(connection);
            CourseStudentController courseStudentController = new CourseStudentController(connection);

            boolean stayLoggedIn = true;

            while (stayLoggedIn) {
                System.out.println();
                teacherController.displayDashboard();
                System.out.print("Please enter a choice: ");
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        // display teacher's profile
                        teacherController.displayProfile();
                    }
                    case 2 -> {
                        // edit teacher profile
                        boolean keepEditing = true;

                        while (keepEditing) {
                            System.out.println();
                            teacherController.displayEditOptions();
                            System.out.print("Enter a choice for edit: ");
                            int editChoice = scanner.nextInt();

                            switch (editChoice) {
                                case 1-> teacherController.changeEmail();
                                case 2-> keepEditing = false;
                                default -> System.out.println("Please enter a valid choice.");
                            }
                        }
                    }
                    case 3 -> {
                        // display details of course teacher's in-charge
                        courseController.displayCourse(teacherController.getCourseInCharge());
                    }
                    case 4 -> {
                        // display students of course teacher's in-charge
                        courseStudentController.displayStudentsOfferingCourse(teacherController.getCourseInCharge());
                    }
                    case 5 -> {
                        // exit the system
                        stayLoggedIn = false;
                    }
                    default -> System.out.println("Please enter a valid choice.");
                }
            }
        } else {
            System.out.println("Teacher does not exist.");
        }
    }

    private UserType getUserType(String regNumber) {
        // String adminRegex = "admin";
        String studentRegex = "\\S\\d/\\d{2}/\\S\\d{2}";
        String teacherRegex = "\\w{2}/S\\d{2}";

        return Pattern.matches(studentRegex, regNumber) ? UserType.STUDENT
                : Pattern.matches(teacherRegex, regNumber) ? UserType.TEACHER
                : null;
    }

    private String requestUserRegNumber() {
        System.out.print("Enter registration-number: ");
        String regNumber = scanner.next();
        return regNumber;
    }
}

enum UserType {
    ADMIN,
    STUDENT,
    TEACHER
}
