package com.developersuraj;

import com.developersuraj.util.PasswordUtil;

public class Test {

    public static void main(String[] args) {

        System.out.println(PasswordUtil.hashPassword("admin123"));

        System.out.println(PasswordUtil.hashPassword("123"));

    }

}