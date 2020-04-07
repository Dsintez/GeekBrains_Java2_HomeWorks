package ru.geekbrains.java2.server.auth;

public class PostgresAuthService implements AuthService{


    @Override
    public String getUsernameByLoginAndPassword(String login, String password) {
        return null;
    }

    @Override
    public void start() {


        System.out.println("Сервис аутентификации запущен");
    }

    @Override
    public void stop() {


        System.out.println("Сервис аутентификации оставлен");
    }
}
