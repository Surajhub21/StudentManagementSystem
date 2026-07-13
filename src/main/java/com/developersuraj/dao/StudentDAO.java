package com.developersuraj.dao;


import com.developersuraj.model.Student;

import java.util.List;

public interface StudentDAO {

    Student addStudent(Student student);

    List<Student> getAllStudents();

    Student getStudentById(int id);

    void updateStudent(Student student);

    void deleteStudent(int id);

    void deleteAllStudents();

    boolean exists(int id);

}