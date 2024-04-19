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

    public void addCourse(Course course) {
        try {
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
                System.out.println("Course removed from database.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getCourse(Course course) {
        try {
            String query = "SELECT * FROM %s WHERE course_code=?";
            query = String.format(query, tableName);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, course.getCourseCode());
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
