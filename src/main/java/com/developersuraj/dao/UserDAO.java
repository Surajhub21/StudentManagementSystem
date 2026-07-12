package com.developersuraj.dao;


import com.developersuraj.model.User;

public interface UserDAO {

    User login(String username, String password);

}