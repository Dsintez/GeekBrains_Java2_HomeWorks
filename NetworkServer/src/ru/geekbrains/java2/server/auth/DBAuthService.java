package ru.geekbrains.java2.server.auth;

import ru.geekbrains.java2.server.database.ConnectDB;
import ru.geekbrains.java2.server.database.SQLite;

public class DBAuthService implements AuthService {
    public static ConnectDB connectDB;

    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        return connectDB.getUsernameByLoginAndPassword(login, password);
    }

    @Override
    public void start() {
        connectDB = new SQLite();
        System.out.println("Сервис аутентификации запущен");
    }

    @Override
    public void stop() {
        try {
            connectDB.close();
            System.out.println("Сервис аутентификации оставлен");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
