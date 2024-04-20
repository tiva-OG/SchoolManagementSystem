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
     * responsible for obtaining courses registered by student
     * @param student being queried
     * @return resultSet from database
     * @throws SQLException
     */
    public ResultSet getCourseRegisteredByStudent(Student student) throws SQLException {
        String query = "SELECT c.code, c.title, c.credit_units FROM %s cs JOIN " +
                "%s c ON cs.course_code=c.code WHERE cs.reg_number=? " +
                "ORDER BY cs.course_code;";
        query = String.format(query, associationTableName, coursesTableName);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, student.getRegNumber());
        return preparedStatement.executeQuery();

    }

    /**
     * responsible for obtaining courses not yet registered by student
     * this includes courses from lower levels in case of carry-overs
     * @param student being queried
     * @return resultSet from database
     * @throws SQLException
     */
    public ResultSet getCoursesUnregisteredByStudent(Student student) throws SQLException {
        String query = "SELECT * FROM %s c WHERE c.level<=? AND c.code NOT IN " +
                "(SELECT cs.course_code FROM %s cs WHERE cs.reg_number=?) " +
                "ORDER BY c.code;";
        query = String.format(query, coursesTableName, associationTableName);
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, student.getLevel());
        preparedStatement.setString(2, student.getRegNumber());
        return preparedStatement.executeQuery();
    }

    public void registerForCourse(Student student, Course course) {

    }
}
