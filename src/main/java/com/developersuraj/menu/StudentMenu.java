package com.developersuraj.menu;


import com.developersuraj.service.StudentService;
import com.developersuraj.service.UserService;

import java.util.Scanner;

public class StudentMenu {

    private final StudentService studentService;
    private final UserService userService;
    private final Scanner scanner;

    public StudentMenu(StudentService studentService,
                       UserService userService,
                       Scanner scanner) {

        this.studentService = studentService;
        this.userService = userService;
        this.scanner = scanner;
    }

    public void show() {

        while (true) {

            System.out.println("\n===== STUDENT MENU =====");
            System.out.println("1. View My Profile");
            System.out.println("2. Logout");

            System.out.print("Choice : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {

                switch (choice) {

                    case 1 -> studentService.viewMyProfile();

                    case 2 -> {
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