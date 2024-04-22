package com.tiva.CourseStudent;

import com.tiva.Course.Course;
import com.tiva.Student.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseStudentQuery {

    private final Connection connection;
    private final String associationTableName;
    private final String coursesTableName;
    private final String studentsTableName;

    public CourseStudentQuery(Connection connection) {
        this.connection = connection;
        this.associationTableName = "courses_students";
        this.coursesTableName = "courses";
        this.studentsTableName = "students";
    }

    /**
     * responsible for obtaining students offering a certain course
     * @param course being queried
     */
    public ResultSet getStudentsOfferingCourse(Course course) throws SQLException {
        String query = "SELECT s.reg_number, s.full_name FROM %s cs JOIN " +
                "%s s ON cs.reg_number=s.reg_number WHERE cs.course_code=? " +
                "ORDER BY cs.reg_number;";
        query = String.format(query, associationTableName, studentsTableName);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, course.getCourseCode());
        return preparedStatement.executeQuery();
    }

    /**
     * responsible for obtaining courses registered by student
     * @param student being queried
     */
    public ResultSet getCoursesRegisteredByStudent(Student student) throws SQLException {
        String query = "SELECT c.code, c.title, c.credit_units FROM %s cs JOIN " +
                "%s c ON cs.course_code=c.code WHERE cs.reg_number=? " +
                "ORDER BY c.level, c.code;";
        query = String.format(query, associationTableName, coursesTableName);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, student.getRegNumber());
        return preparedStatement.executeQuery();
    }

    /**
     * responsible for obtaining courses not yet registered by student
     * this includes courses from lower levels in case of carry-overs
     * @param student being queried
     */
    public ResultSet getCoursesUnregisteredByStudent(Student student) throws SQLException {
        String query = "SELECT * FROM %s c WHERE c.level<=? AND c.code NOT IN " +
                "(SELECT cs.course_code FROM %s cs WHERE cs.reg_number=?) " +
                "ORDER BY c.level, c.code;";
        query = String.format(query, coursesTableName, associationTableName);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, student.getLevel());
        preparedStatement.setString(2, student.getRegNumber());
        return preparedStatement.executeQuery();
    }

    public void registerForCourse(Student student, Course course) throws SQLException {
        String query = "INSERT INTO %s(course_code, reg_number) VALUES (upper(?), ?);";
        query = String.format(query, associationTableName);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, course.getCourseCode());
        preparedStatement.setString(2, student.getRegNumber());
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Student successfully registered.");
        } else {
            System.out.println("Failed to register Student.");
        }
    }

    public void unregisterForCourse(Student student, Course course) throws SQLException {
        String query = "DELETE FROM %s WHERE course_code=upper(?) AND reg_number=?";
        query = String.format(query, associationTableName);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, course.getCourseCode());
        preparedStatement.setString(2, student.getRegNumber());
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Student successfully unregistered.");
        } else {
            System.out.println("Failed to unregister Student.");
        }
    }
}
