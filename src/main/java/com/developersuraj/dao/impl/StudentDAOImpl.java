package com.developersuraj.dao.impl;


import com.developersuraj.dao.StudentDAO;
import com.developersuraj.database.DBConnection;
import com.developersuraj.model.Student;
import com.developersuraj.util.PasswordUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    @Override
    public Student addStudent(Student student) {

        String studentSql = """
            INSERT INTO students(student_id, name, age, course)
            VALUES (?, ?, ?, ?)
            """;

        String userSql = """
            INSERT INTO users(username, password, role, student_id)
            VALUES (?, ?, ?, ?)
            """;

        try (Connection connection = DBConnection.getConnection()) {

            connection.setAutoCommit(false);

            try (
                    PreparedStatement studentStmt = connection.prepareStatement(studentSql);
                    PreparedStatement userStmt = connection.prepareStatement(userSql)
            ) {

                // Insert into students table
                studentStmt.setInt(1, student.getStudentId());
                studentStmt.setString(2, student.getName());
                studentStmt.setInt(3, student.getAge());
                studentStmt.setString(4, student.getCourse());
                studentStmt.executeUpdate();

                // Extract first name as username
                String username = student.getName().trim().split("\\s+")[0];

                // Insert into users table
                userStmt.setString(1, username);
                userStmt.setString(2, PasswordUtil.hashPassword("123"));
                userStmt.setString(3, "STUDENT");
                userStmt.setInt(4, student.getStudentId());
                userStmt.executeUpdate();

                connection.commit();
                return student;

            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to add student.", e);
        }
    }

    @Override
    public List<Student> getAllStudents() {

        List<Student> students = new ArrayList<>();

        String sql = """
            SELECT *
            FROM students
            ORDER BY student_id
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()
        ) {

            while (resultSet.next()) {

                Student student = new Student(
                        resultSet.getInt("student_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("course")
                );

                students.add(student);

            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to fetch students.", e);
        }

        return students;

    }
    @Override
    public Student getStudentById(int studentId) {

        String sql = """
            SELECT *
            FROM students
            WHERE student_id=?
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, studentId);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                return new Student(
                        resultSet.getInt("student_id"),
                        resultSet.getString("name"),
                        resultSet.getInt("age"),
                        resultSet.getString("course")
                );

            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to fetch student.", e);
        }

        return null;

    }
    @Override
    public void updateStudent(Student student) {

        String sql = """
            UPDATE students
            SET name=?,
                age=?,
                course=?
            WHERE student_id=?
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setString(3, student.getCourse());
            statement.setInt(4, student.getStudentId());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Unable to update student.", e);
        }

    }

    @Override
    public void deleteStudent(int studentId) {

        String sql = """
            DELETE FROM students
            WHERE student_id=?
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, studentId);

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete student.", e);
        }

    }

    @Override
    public void deleteAllStudents() {

        String deleteStudentUsers = """
            DELETE FROM users
            WHERE role = 'STUDENT';
            """;

        String deleteStudents = """
            DELETE FROM students;
            """;

        try (
                Connection connection = DBConnection.getConnection()
        ) {

            connection.setAutoCommit(false);

            try (
                    PreparedStatement ps1 = connection.prepareStatement(deleteStudentUsers);
                    PreparedStatement ps2 = connection.prepareStatement(deleteStudents)
            ) {

                ps1.executeUpdate();
                ps2.executeUpdate();

                connection.commit();

            } catch (SQLException e) {
                connection.rollback();
                throw e;
            }

        } catch (SQLException e) {
            throw new RuntimeException("Unable to delete all students: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean exists(int studentId) {

        String sql = """
            SELECT 1
            FROM students
            WHERE student_id=?
            """;

        try (
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {

            statement.setInt(1, studentId);

            ResultSet resultSet = statement.executeQuery();

            return resultSet.next();

        } catch (SQLException e) {
            throw new RuntimeException("Database Error.", e);
        }

    }
}