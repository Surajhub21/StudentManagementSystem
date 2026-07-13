package com.developersuraj.service;



import com.developersuraj.dao.StudentDAO;
import com.developersuraj.exceptions.DuplicateIdException;
import com.developersuraj.exceptions.StudentNotFoundException;
import com.developersuraj.model.Student;
import com.developersuraj.util.BackupUtil;
import com.developersuraj.util.CSVUtil;
import com.developersuraj.util.Validator;

import java.util.List;
import java.util.Scanner;

public class StudentService {

    private final StudentDAO studentDAO;
    private final Scanner scanner;

    public StudentService(StudentDAO studentDAO, Scanner scanner) {
        this.studentDAO = studentDAO;
        this.scanner = scanner;
    }


    public void addStudent() {

        System.out.print("Student ID : ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (studentDAO.exists(id)) {
            throw new DuplicateIdException(id);
        }

        System.out.print("Name : ");
        String name = scanner.nextLine();

        System.out.print("Age : ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Course : ");
        String course = scanner.nextLine();

        Student student = new Student(id, name, age, course);

        Validator.validateStudent(student);

        Student addedStudent = studentDAO.addStudent(student);

        System.out.println("Student added successfully. : "+ addedStudent);
    }

    public void viewAllStudents() {

        List<Student> students = studentDAO.getAllStudents();

        if (students.isEmpty()) {
            System.out.println("\nNo students found.");
            return;
        }

        System.out.println("\n===== Student List =====");

        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void searchStudent() {

        System.out.print("Enter Student ID : ");
        int id = scanner.nextInt();

        Student student = studentDAO.getStudentById(id);

        if (student == null) {
            throw new StudentNotFoundException(id);
        }

        System.out.println("\nStudent Details");
        System.out.println(student);
    }

    public void updateStudent() {

        System.out.print("Enter Student ID : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Student existingStudent = studentDAO.getStudentById(id);

        if (existingStudent == null) {
            throw new StudentNotFoundException(id);
        }

        System.out.print("Enter Name : ");
        String name = scanner.nextLine();

        System.out.print("Enter Age : ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        System.out.print("Enter Course : ");
        String course = scanner.nextLine();

        Student updatedStudent = new Student(id, name, age, course);

        Validator.validateStudent(updatedStudent);

        studentDAO.updateStudent(updatedStudent);

        System.out.println("Student updated successfully.");
    }

    public void deleteStudent() {

        System.out.print("Enter Student ID : ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        Student student = studentDAO.getStudentById(id);

        if (student == null) {
            throw new StudentNotFoundException(id);
        }

        System.out.print("Are you sure? (Y/N) : ");

        String choice = scanner.next();

        if (choice.equalsIgnoreCase("Y")) {

            studentDAO.deleteStudent(id);

            System.out.println("Student deleted successfully.");

        } else {

            System.out.println("Operation cancelled.");

        }

    }

    public void deleteAllStudent() {

        System.out.print("Are you sure? (Y/N) : ");

        String choice = scanner.next();

        if (choice.equalsIgnoreCase("Y")) {

            studentDAO.deleteAllStudents();

            System.out.println("All students deleted successfully.");

        } else {

            System.out.println("Operation cancelled.");

        }

    }

    public void viewMyProfile() {

        int studentId = util.Session.getCurrentUser().getStudentId();

        Student student = studentDAO.getStudentById(studentId);

        if (student == null) {
            throw new StudentNotFoundException(studentId);
        }

        System.out.println("\n========== MY PROFILE ==========");
        System.out.println("Student ID : " + student.getStudentId());
        System.out.println("Name       : " + student.getName());
        System.out.println("Age        : " + student.getAge());
        System.out.println("Course     : " + student.getCourse());
    }

    public void exportStudents() {

        List<Student> students = studentDAO.getAllStudents();

        String filename = "students.csv";

        CSVUtil.exportStudents(students, filename);
    }

    public void importStudents(String fileName) {

        List<Student> students = CSVUtil.importStudents(fileName);

        for (Student student : students) {
            studentDAO.addStudent(student);
        }

        System.out.println("Import Completed.");
    }

    public void backup(){

        List<Student> students = studentDAO.getAllStudents();

        BackupUtil.backup(students);
    }
}
