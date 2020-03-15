package HW6;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static HW6.ChatFunctions.*;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8133);
        Socket socketClient = serverSocket.accept();
        new Thread(() -> waitMessage(socketClient)).start();
        sendMessage(socketClient);
    }
}
