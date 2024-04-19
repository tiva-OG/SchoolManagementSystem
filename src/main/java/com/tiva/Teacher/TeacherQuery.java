package com.tiva.Teacher;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherQuery {
    private final Connection connection;
    private final String tableName;

    public TeacherQuery(Connection connection) {
        this.connection = connection;
        this.tableName = "teachers";
    }

    public void addTeacher(Teacher teacher) {
        try {
            String query = "INSERT INTO %s(reg_number, full_name, email) VALUES (?, ?, ?)";
            query = String.format(query, tableName);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, teacher.getRegNumber());
            preparedStatement.setString(2, teacher.getFullName());
            preparedStatement.setString(3, teacher.getEmail());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Student added to database.");
            } else {
                System.out.println("Student removed from database.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getTeacher(Teacher teacher) {
        try {
            String query = "SELECT * FROM %s WHERE reg_number=?";
            query = String.format(query, tableName);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, teacher.getRegNumber());
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
