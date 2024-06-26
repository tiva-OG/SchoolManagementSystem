package com.tiva.Course;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseQuery {
    private final Connection connection;
    private final String tableName;

    public CourseQuery(Connection connection) {
        this.connection = connection;
        this.tableName = "courses";
    }

    /**
     * add a new course into the database
     */
    public void addCourse(Course course) throws SQLException {
        String query = "INSERT INTO %s(code, title, level, credit_units) VALUES (?, ?, ?, ?)";
        query = String.format(query, tableName);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, course.getCourseCode());
        preparedStatement.setString(2, course.getCourseTitle());
        preparedStatement.setInt(3, course.getCourseLevel());
        preparedStatement.setInt(4, course.getCreditUnits());
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Course added to database.");
        } else {
            System.out.println("Failed to add Course to database.");
        }
    }

    /**
     * get the details of a course from the database
     */
    public ResultSet getCourse(Course course) throws SQLException {
        String query = "SELECT * FROM %s WHERE code=upper(?);";
        query = String.format(query, tableName);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, course.getCourseCode());
        return preparedStatement.executeQuery();
    }

    /**
     * delete a course from the database
     */
    public void removeCourse(Course course) throws SQLException {
        String query = "DELETE FROM %s WHERE course_code=?";
        query = String.format(query, tableName);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, course.getCourseCode());
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Course removed from database.");
        } else {
            System.out.println("Failed to remove Course from database.");
        }
    }
}
