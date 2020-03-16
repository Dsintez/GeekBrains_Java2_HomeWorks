package HW6;

import java.io.*;
import java.net.Socket;

import static HW6.ChatFunctions.*;

public class Client{
    public static void main(String[] args) throws IOException {
        System.out.println("Введите адресс сервера. Пример: 192.168.0.11");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String ip = reader.readLine();
        Socket socket = new Socket(ip, DEFAULT_PORT);
        System.out.println("Подключение произведено");
        new Thread(() -> waitMessage(socket)).start();
        sendMessage(socket, "Client");
    }
}
