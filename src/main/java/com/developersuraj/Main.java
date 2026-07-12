package com.developersuraj;

import com.developersuraj.dao.StudentDAO;
import com.developersuraj.dao.UserDAO;
import com.developersuraj.dao.impl.StudentDAOImpl;
import com.developersuraj.dao.impl.UserDAOImpl;
import com.developersuraj.menu.AdminMenu;
import com.developersuraj.menu.StudentMenu;
import com.developersuraj.service.StudentService;
import com.developersuraj.service.UserService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        StudentDAO studentDAO = new StudentDAOImpl();
        UserDAO userDAO = new UserDAOImpl();

        StudentService studentService = new StudentService(studentDAO, scanner);
        UserService userService = new UserService(userDAO, scanner);

        AdminMenu adminMenu = new AdminMenu(studentService, userService, scanner);
        StudentMenu studentMenu = new StudentMenu(studentService, userService, scanner);

        while (true) {

            System.out.println("\n====================================");
            System.out.println("   STUDENT MANAGEMENT SYSTEM");
            System.out.println("====================================");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter Choice : ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:

                    try {

                        userService.login();

                        if (util.Session.getCurrentUser().getRole().equalsIgnoreCase("ADMIN")) {

                            adminMenu.show();

                        } else {

                            studentMenu.show();

                        }

                    } catch (Exception e) {

                        System.out.println(e.getMessage());

                    }

                    break;

                case 2:

                    System.out.println("Thank You!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:

                    System.out.println("Invalid Choice.");

            }

        }

    }

}