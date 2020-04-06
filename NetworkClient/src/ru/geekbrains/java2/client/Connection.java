package ru.geekbrains.java2.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection {
    private static final int DEFAULT_PORT = 8189;
    private static final String  DEFAULT_IP = "localhost";
    private static Socket socket;
    private String login;
    private String password;

    public Connection(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String auth() {
        if (socket == null) {
            System.out.printf("Хм, socket не инициализирован");
            return "socket не инициализирован";
        }
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            out.writeUTF("/auth " + login + " " + password);
            return in.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static void start() throws IOException {
        socket = new Socket(DEFAULT_IP, DEFAULT_PORT);
    }
}
