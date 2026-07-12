package com.developersuraj.service;

import com.developersuraj.dao.UserDAO;
import com.developersuraj.model.User;
import com.developersuraj.util.PasswordUtil;


import java.util.Scanner;

public class UserService {

    private final UserDAO userDAO;
    private final Scanner scanner;

    public UserService(UserDAO userDAO,
                       Scanner scanner) {

        this.userDAO = userDAO;
        this.scanner = scanner;

    }

    public void login(){

        System.out.println("\n===== LOGIN =====");

        System.out.print("Username : ");
        String username = scanner.nextLine();

        System.out.print("Password : ");
        String password = scanner.nextLine();

        password = PasswordUtil.hashPassword(password);

        User user = userDAO.login(username,password);

        if(user == null){
            throw new RuntimeException("Invalid Username or Password");
        }

        util.Session.login(user);

        System.out.println("Login Successful.");

    }

    public void logout(){

        util.Session.logout();

        System.out.println("Logout Successful.");

    }

}