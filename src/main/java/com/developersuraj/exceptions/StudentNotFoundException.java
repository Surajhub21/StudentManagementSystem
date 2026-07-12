package com.developersuraj.exceptions;


public class StudentNotFoundException extends RuntimeException {
//student Management system
    public StudentNotFoundException(int id) {
        super("Student with ID " + id + " not found.");
    }

}