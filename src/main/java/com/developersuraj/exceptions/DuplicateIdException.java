package com.developersuraj.exceptions;

public class DuplicateIdException extends RuntimeException {

    public DuplicateIdException(int id) {
        super("Student with ID " + id + " already exists.");
    }

}