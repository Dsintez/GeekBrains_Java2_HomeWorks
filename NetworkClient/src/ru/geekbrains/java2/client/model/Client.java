package ru.geekbrains.java2.client.model;

import javafx.application.Platform;
import ru.geekbrains.java2.client.ClientApp;
import ru.geekbrains.java2.client.controllers.ChatController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client implements AutoCloseable{
    private static final int DEFAULT_PORT = 8189;
    private static final String DEFAULT_IP = "localhost";
    private static Socket socket;
    private String login;
    private String password;
    private String username;
    private static Client client = null;
    public static ChatController chatController;
    public Thread listener;

    private DataInputStream in;
    private DataOutputStream out;

    public Client(String login, String password) {
        this.login = login;
        this.password = password;
        client = this;
    }

    public String auth() {
        if (socket == null) {
            System.out.printf("Хм, socket не инициализирован %n");
            return "socket не инициализирован";
        }
        String mes = "";
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            out.writeUTF("/auth " + login + " " + password);
            String message = in.readUTF();
            if (message.startsWith("/auth")) {
                String[] messageParts = message.split("\\s+", 2);
                username = messageParts[1];
            }
            return message;
        } catch (IOException e) {
            mes = "Соединение разорвано";
        }
        return mes;
    }

    public void startChat() {
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        listener = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        String message = in.readUTF();
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                chatController.addMessage(message);
                            }
                        }); //Вот эта штука нужна чтобы JavaFX не ругался, но даже без нее работает с матюками.
                    }
                } catch (IOException e) {
                    System.err.println("Соединение разорвано");
                }
            }
        });
        listener.start();
    }

    public void sendMessage(String message, String user) {
        try {
            if (!user.equals("All")) {
                out.writeUTF(String.format("/w %s %s", user, message));
            } else {
                out.writeUTF(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void connect() throws IOException {
        socket = new Socket(DEFAULT_IP, DEFAULT_PORT);
    }

    public static Client getClient() {
        return client;
    }

    @Override
    public void close() throws Exception {
        in.close();
        listener.interrupt();
    }
}
