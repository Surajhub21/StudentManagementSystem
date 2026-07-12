package com.developersuraj.util;


import com.developersuraj.exceptions.ValidationException;
import com.developersuraj.model.Student;

public class Validator {

    private Validator() {
    }

    public static void validateStudent(Student student) {
        validateId(student.getStudentId());
        validateName(student.getName());
        validateAge(student.getAge());
        validateCourse(student.getCourse());
    }

    public static void validateId(int id) {

        if (id <= 0) {
            throw new ValidationException("Student ID must be greater than 0.");
        }
    }

    public static void validateName(String name) {

        if (name == null || name.isBlank()) {
            throw new ValidationException("Name cannot be empty.");
        }
    }

    public static void validateAge(int age) {

        if (age < 16 || age > 100) {
            throw new ValidationException("Age must be between 16 and 100.");
        }
    }

    public static void validateCourse(String course) {

        if (course == null || course.isBlank()) {
            throw new ValidationException("Course cannot be empty.");
        }
    }
}