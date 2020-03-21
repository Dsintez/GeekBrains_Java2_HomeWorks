package HW6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static HW6.ChatFunctions.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(DEFAULT_PORT);
        Socket socketClient = serverSocket.accept();
        System.out.println("Пользователь подключен");
        new Thread(() -> waitMessage(socketClient)).start();
        sendMessage(socketClient, "Server");
    }
}
