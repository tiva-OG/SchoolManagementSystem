package com.tiva.CourseStudent;

import com.tiva.Course.Course;
import com.tiva.Course.CourseController;
import com.tiva.Student.Student;
import com.tiva.Student.StudentController;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class CourseStudentController {

    private final Scanner scanner;
    private final CourseStudentQuery courseStudentQuery;
    private Course course;
    private CourseController courseController;
    private Student student;
    private StudentController studentController;


    public CourseStudentController(Connection connection, Scanner scanner) {
        this.scanner = scanner;
        this.courseStudentQuery = new CourseStudentQuery(connection);
    }


    public void registerForCourse(Student student) {
        String courseCode = courseController.requestCourseCode();
        if (courseController.isCourse(courseCode)) {
            course = new Course(courseCode);
            courseStudentQuery.registerForCourse(student, course);
        } else {
            System.out.println("Course data does not exist.");
        }

    }


}
