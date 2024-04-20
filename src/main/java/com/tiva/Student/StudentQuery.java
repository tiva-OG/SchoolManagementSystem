package com.tiva.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentQuery {
    private final Connection connection;
    private final String tableName;

    public StudentQuery(Connection connection) {
        this.connection = connection;
        this.tableName = "students";
    }

    public ResultSet getStudent(Student student) {
        try {
            String query = "SELECT * FROM %s WHERE reg_number=?";
            query = String.format(query, tableName);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getRegNumber());
            return preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addStudent(Student student) {
        try {
            String query = "INSERT INTO %s(reg_number, full_name, email, level, reg_credit_units) VALUES (?, ?, ?, ?, ?)";
            query = String.format(query, tableName);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getRegNumber());
            preparedStatement.setString(2, student.getFullName());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getLevel());
            preparedStatement.setInt(5, student.getRegisteredCreditUnits());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Student added to database.");
            } else {
                System.out.println("Failed to add Student to database.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeStudent(Student student) {
        try {
            String query = "DELETE FROM %s WHERE reg_number=?";
            query = String.format(query, tableName);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getRegNumber());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Student removed from database.");
            } else {
                System.out.println("Failed to remove Student from database.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateEmail(Student student) {
        try {
            String query = "UPDATE TABLE %s SET email=? WHERE reg_number=?";
            query = String.format(query, tableName);
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, student.getEmail());
            preparedStatement.setString(2, student.getRegNumber());
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                System.out.println("Student email has been changed.");
            } else {
                System.out.println("Unable to change Student email.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
