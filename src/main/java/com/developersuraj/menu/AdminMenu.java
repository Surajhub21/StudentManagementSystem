package com.developersuraj.menu;


import com.developersuraj.service.StudentService;
import com.developersuraj.service.UserService;

import java.util.Scanner;

public class AdminMenu {

    private final StudentService studentService;
    private final UserService userService;
    private final Scanner scanner;

    public AdminMenu(StudentService studentService,
                     UserService userService,
                     Scanner scanner) {

        this.studentService = studentService;
        this.userService = userService;
        this.scanner = scanner;
    }

    public void show() {

        while (true) {

            System.out.println("\n===== ADMIN MENU =====");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Delete All Student");
            System.out.println("7. Export Students on CSV");
            System.out.println("8. Import Students from CSV");
            System.out.println("9. Backup Data");
            System.out.println("10. Logout");

            System.out.print("Choice : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {

                    case 1 -> studentService.addStudent();

                    case 2 -> studentService.viewAllStudents();

                    case 3 -> studentService.searchStudent();

                    case 4 -> studentService.updateStudent();

                    case 5 -> studentService.deleteStudent();

                    case 6 -> studentService.deleteAllStudent();

                    case 7 -> studentService.exportStudents();

                    case 8 -> studentService.importStudents("students.csv");

                    case 9 -> studentService.backup();

                    case 10 -> {
                        userService.logout();
                        return;
                    }

                    default -> System.out.println("Invalid Choice");
                }

            } catch (Exception e) {

                System.out.println(e.getMessage());

            }

        }

    }

}