package com.developersuraj.dao.impl;


import com.developersuraj.dao.UserDAO;
import com.developersuraj.database.DBConnection;
import com.developersuraj.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements UserDAO {

    @Override
    public User login(String username, String password) {

        String sql = """
                SELECT *
                FROM users
                WHERE username = ?
                AND password = ?
                """;

        try(
                Connection connection = DBConnection.getConnection();
                PreparedStatement statement =
                        connection.prepareStatement(sql)
        ){

            statement.setString(1, username);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()){

                return new User(

                        resultSet.getInt("id"),

                        resultSet.getString("username"),

                        resultSet.getString("password"),

                        resultSet.getString("role"),

                        (Integer) resultSet.getObject("student_id")
                );

            }

        }
        catch (SQLException e){
            throw new RuntimeException("Login Failed",e);
        }

        return null;

    }

}