import com.tiva.Course.CourseController;
import com.tiva.CourseStudent.CourseStudentController;
import com.tiva.School.SchoolController;
import com.tiva.Student.StudentController;
import com.tiva.Teacher.TeacherController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {


    public static void main(String[] args) throws SQLException {
        System.out.println("Hello World.");

        String url = "jdbc:postgresql://localhost:5432/test";
        String username = "postgres";
        String password = "210598";
        Connection connection = DriverManager.getConnection(url, username, password);

        SchoolController schoolController = new SchoolController(connection);

        schoolController.loginPortal();

        /*
        String url = "jdbc:postgresql://localhost:5432/test";
        String username = "postgres";
        String password = "210598";
        Connection connection = DriverManager.getConnection(url, username, password);
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        // construct studentController with a student as the name implies.
        // maybe make displayDashboard static? no perfection :)

        // StudentController studentController = new StudentController(connection, scanner);
        // CourseStudentController courseStudentController = new CourseStudentController(connection);

        // String regNumber = studentController.requestRegNumber();
        // studentController.setRegNumber(regNumber);

        System.out.print("Enter teacher's registration-number: ");
        String regNumber = scanner.next();
        // StudentController studentController = new StudentController(connection, scanner, regNumber);
        TeacherController teacherController = new TeacherController(connection, regNumber);

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

        /*
        if (studentController.isStudent()) {
            studentController.loadStudentDetails();
            // the courseController may also be initialised with a studentController to make control
            // easier; hope you understand
            CourseStudentController courseStudentController = new CourseStudentController(connection, scanner, studentController);

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
         */
    }

}
