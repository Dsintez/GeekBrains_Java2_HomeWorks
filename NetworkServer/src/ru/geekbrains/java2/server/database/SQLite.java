package ru.geekbrains.java2.server.database;

import java.sql.*;

public class SQLite implements ConnectDB {
    private Connection connection;
    private Statement statement;
    private ResultSet resSet;

    public SQLite() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:Chat.s3db");
            statement = connection.createStatement();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        try {
            resSet = statement.executeQuery("SELECT login, password, username FROM users");
            while (resSet.next()) {
                if (resSet.getString("login").equals(login) && resSet.getString("password").equals(password)) {
                    return resSet.getString("username");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void changeNickname(String login, String newNickname) {
        try {
            resSet = statement.executeQuery("SELECT login, username FROM users");
            statement.executeUpdate("UPDATE users SET username = '" + newNickname + "' WHERE login = '" + login + "'");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changePassword(String login, String newPassword) {

    }

    @Override
    public boolean createUser(String login, String password, String username) {

        return false;
    }

    @Override
    public void close() throws Exception {
        resSet.close();
        statement.close();
        connection.close();
    }
}
