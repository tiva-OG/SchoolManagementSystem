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

    /**
     * add new teacher to database
     */
    public void addTeacher(Teacher teacher) throws SQLException {
        String query = "INSERT INTO %s(reg_number, full_name, email, course_in_charge) VALUES (?, ?, ?, ?)";
        query = String.format(query, tableName);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, teacher.getRegNumber());
        preparedStatement.setString(2, teacher.getFullName());
        preparedStatement.setString(3, teacher.getEmail());
        preparedStatement.setString(4, teacher.getCourseInCharge());

        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Student added to database.");
        } else {
            System.out.println("Student removed from database.");
        }

    }

    /**
     * get teacher details from database
     */
    public ResultSet getTeacher(Teacher teacher) throws SQLException {
        String query = "SELECT * FROM %s WHERE reg_number=?";
        query = String.format(query, tableName);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, teacher.getRegNumber());
        return preparedStatement.executeQuery();
    }

    /**
     * update course teacher's in charge
     */
    public void updateCourseInCharge(Teacher teacher) throws SQLException {
        String query = "UPDATE %s SET course_in_charge=? WHERE reg_number=?;";
        query = String.format(query, tableName);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, teacher.getCourseInCharge());
        preparedStatement.setString(2, teacher.getRegNumber());
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Teacher course in-charge has been changed.");
        } else {
            System.out.println("Unable to change Teacher course in-charge.");
        }
    }

    /**
     * update teacher's email in database
     */
    public void updateEmail(Teacher teacher) throws SQLException {
        String query = "UPDATE %s SET email=? WHERE reg_number=?;";
        query = String.format(query, tableName);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, teacher.getEmail());
        preparedStatement.setString(2, teacher.getRegNumber());
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Teacher email has been changed.");
        } else {
            System.out.println("Unable to change Teacher email.");
        }
    }

    /**
     * delete teacher from database
     */
    public void removeTeacher(Teacher teacher) throws SQLException {
        String query = "DELETE FROM %s WHERE reg_number=?";
        query = String.format(query, tableName);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, teacher.getRegNumber());
        int affectedRows = preparedStatement.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("Teacher removed from database.");
        } else {
            System.out.println("Failed to remove Teacher from database.");
        }
    }
}
