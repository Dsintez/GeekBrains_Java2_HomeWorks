package ru.geekbrains.java2.server.database;

public interface ConnectDB extends AutoCloseable{
    String getUsernameByLoginAndPassword(String login, String password);
    void changeNickname(String login, String newNickname);
    void changePassword(String login, String newPassword);
    boolean createUser(String login, String password, String username);

}
