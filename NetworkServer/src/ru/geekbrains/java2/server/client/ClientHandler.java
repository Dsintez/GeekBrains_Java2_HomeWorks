package ru.geekbrains.java2.server.client;

import ru.geekbrains.java2.server.NetworkServer;
import ru.geekbrains.java2.server.database.ConnectSQLite;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class ClientHandler {

    private ExecutorService executorService;

    private final NetworkServer networkServer;
    private final Socket clientSocket;

    private DataInputStream in;
    private DataOutputStream out;

    private String nickname;
    private String login;

    private static String rxAllSpace = "\\s+";

    public String getNickname() {
        return nickname;
    }

    public ClientHandler(NetworkServer networkServer, Socket socket) {
        this.networkServer = networkServer;
        this.clientSocket = socket;
    }

    public void run() {
        doHandle(clientSocket);
    }

    private void doHandle(Socket socket) {
        executorService = Executors.newFixedThreadPool(1);
        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            executorService.execute(new Thread(() -> {
                try {
                    authentication();
                    readMessages();
                } catch (IOException e) {
                    final String msg = "Соединение с клиентом " + nickname + " было закрыто!";
                    System.out.println(msg);
                    networkServer.LOGGER.log(Level.INFO, msg);
                } finally {
                    closeConnection();
                }
            }));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        networkServer.unsubscribe(this);
        try {
            clientSocket.close();
        } catch (IOException e) {
            networkServer.LOGGER.log(Level.WARNING, "Ошибка", e);
            e.printStackTrace();
        }
    }

    private void readMessages() throws IOException {
        while (true) {
            String message = in.readUTF();
            message = ConnectSQLite.checkMessageForCensorship(message);
            System.out.printf("От %s: %s%n", nickname, message);
            if ("/end".equals(message)) {
                return;
            }
            // "/w nickname message"
            if (message.startsWith("/w")) {
                String[] messageParts = message.split(rxAllSpace, 3);
                String nicknameRecipient = messageParts[1];
                message = nickname + ": " + messageParts[2];
                networkServer.unicastMessage(nicknameRecipient, message);
                networkServer.LOGGER.log(Level.INFO, String.format("Пользователь с логином %s отправил сообщение: %s", login, message));
                continue;
            }
            // "/chname login newNickname"
            if (message.startsWith("/chname")) {
                String[] messageParts = message.split(rxAllSpace, 3);
                message = nickname + " изменил ник на ";
                String login = messageParts[1];
                nickname = messageParts[2];
                ConnectSQLite.changeNickname(login, nickname);
                message += nickname;
                networkServer.LOGGER.log(Level.INFO, message);
            }
            networkServer.broadcastMessage(nickname + ": " + message, this);
        }
    }

    private void authentication() throws IOException {
        Thread sleep = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(72000);
                    clientSocket.close();
                } catch (InterruptedException e) {
                    System.out.printf("%s успешно авторизовался%n", nickname);
                    networkServer.LOGGER.log(Level.INFO, String.format("%s успешно авторизовался%n", nickname));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        sleep.start();
        while (true) {
            String message = in.readUTF();
            // "/auth login password"
            if (message.startsWith("/auth")) {
                String[] messageParts = message.split("\\s+", 3);
                String login = messageParts[1];
                String password = messageParts[2];
                String username = networkServer.getAuthService().getUsernameByLoginAndPassword(login, password);
                if (username == null) {
                    sendMessage("Отсутствует учетная запись по данному логину и паролю!");
                    networkServer.LOGGER.log(Level.INFO, String.format("Неоудачная попытка авторизации с логином %s и паролем %s", login, password));
                } else {
                    nickname = username;
                    this.login = login;
                    networkServer.broadcastMessage(nickname + " зашел в чат!", this);
                    sendMessage("/auth " + nickname);
                    networkServer.subscribe(this);
                    sleep.interrupt();
                    break;
                }
            }
        }
    }

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
    }
}
