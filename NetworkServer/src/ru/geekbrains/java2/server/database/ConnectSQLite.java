package ru.geekbrains.java2.server.database;

import java.sql.*;

public class ConnectSQLite {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resSet;

    public static void start() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:Chat.s3db");
            statement = connection.createStatement();
            resSet = statement.executeQuery("select * from sqlite_master where type = 'table'");
            while (resSet.next()){
                System.out.println(resSet.getString(2));
            }
            resSet = statement.executeQuery("SELECT login, password from users");
            while (resSet.next()){
                System.out.println(resSet.getString("login"));
                System.out.println(resSet.getString("password"));
            }
            //CREATE TABLE censorship ('id' INTEGER Primary Key AUTOINCREMENT, 'word' VARCHAR(100))
            //statement.execute("INSERT into 'censorship' ('word') values ('')");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getUsernameByLoginAndPassword(String login, String password) {
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

    public static void changeNickname(String login, String newNickname) {
        try {
            resSet = statement.executeQuery("SELECT login, username FROM users");
            String query = String.format("UPDATE users SET username = '%s' WHERE login = '%s", newNickname, login);
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void changePassword(String login, String newPassword) {
    }

    public static boolean createUser(String login, String password, String username) {
        return false;
    }

    public static String checkMessageForCensorship(String message){
        String[] wordsInMessage = message.split("\\s+");
        for (int i = 0; i < wordsInMessage.length; i++) {
            String query = String.format("SELECT word from censorship where word = '%s'", wordsInMessage[i].toLowerCase());
            try {
                resSet = statement.executeQuery(query);
                if (resSet.next()) {
                    wordsInMessage[i] = wordsInMessage[i].replaceAll(".", "*");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        String checkedMessage = "";
        for (int i = 0; i < wordsInMessage.length; i++) {
            checkedMessage += wordsInMessage[i] + " ";
        }
        return checkedMessage;
    }

    public static void close() throws Exception {
        resSet.close();
        statement.close();
        connection.close();
    }
}
