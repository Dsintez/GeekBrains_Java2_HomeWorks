package HW6;

import java.io.*;
import java.net.Socket;

import static HW6.ChatFunctions.*;

public class Сlient{
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8133);
        new Thread(() -> waitMessage(socket)).start();
        sendMessage(socket);
    }
}
