package ru.geekbrains.java2.server;

import ru.geekbrains.java2.server.auth.AuthService;
import ru.geekbrains.java2.server.auth.DBAuthService;
import ru.geekbrains.java2.server.client.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class NetworkServer {

    private final int port;
    private final List<ClientHandler> clients = new ArrayList<>();
    private final AuthService authService;
    public final Logger LOGGER;

    public NetworkServer(int port) {
        this.LOGGER = Logger.getLogger(NetworkServer.class.getName());
        try {
            Handler handler = new FileHandler("Log.xml");
            handler.setFormatter(new SimpleFormatter());
            LOGGER.addHandler(handler);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.port = port;
        this.authService = new DBAuthService();
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Сервер был успешно запущен на порту " + port);
            LOGGER.log(Level.INFO, "Сервер был успешно запущен на порту " + port);
            authService.start();
            while (true) {
                System.out.println("Ожидание клиентского подключения...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Клиент подлючился");
                LOGGER.log(Level.INFO, "Клиент подлючился");
                createClientHandler(clientSocket);
            }
        } catch (IOException e) {
            System.out.println("Ошибка при работе сервера");
            LOGGER.log(Level.WARNING, "Ошибка при работе сервера", e);
            e.printStackTrace();
        } finally {
            authService.stop();
        }
    }

    private void createClientHandler(Socket clientSocket) {
        ClientHandler clientHandler = new ClientHandler(this, clientSocket);
        clientHandler.run();
    }

    public AuthService getAuthService() {
        return authService;
    }

    public synchronized void unicastMessage(String nicknameRecipient, String message) throws IOException {
        for (ClientHandler client : clients) {
            if (client.getNickname().equals(nicknameRecipient)) {
                client.sendMessage(message);
            }
        }
    }

    public String[] getContacts(String recipient) {
        final String[] contacts = new String[clients.size() - 1];
        int counter = 0;
        for (ClientHandler client : clients) {
            final String nickname = client.getNickname();
            if (recipient.equals(nickname))
            contacts[counter] = nickname;
        }
        return contacts;
    }

    public synchronized void broadcastMessage(String message, ClientHandler owner) throws IOException {
        for (ClientHandler client : clients) {
            if (client != owner) {
                client.sendMessage(message);
            }
        }
    }

    public synchronized void subscribe(ClientHandler clientHandler) {
        clients.add(clientHandler);
    }

    public synchronized void unsubscribe(ClientHandler clientHandler) {
        clients.remove(clientHandler);
    }
}
