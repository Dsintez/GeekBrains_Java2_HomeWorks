package ru.geekbrains.java2.server.auth;

import ru.geekbrains.java2.server.database.ConnectSQLite;

public class DBAuthService implements AuthService {

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        return ConnectSQLite.getUsernameByLoginAndPassword(login, password);
    }

    @Override
    public void start() {
        ConnectSQLite.start();
        System.out.println("Сервис аутентификации запущен");
    }

    @Override
    public void stop() {
        try {
            ConnectSQLite.close();
            System.out.println("Сервис аутентификации оставлен");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
