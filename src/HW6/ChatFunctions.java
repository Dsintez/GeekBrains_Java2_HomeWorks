package HW6;

import java.io.*;
import java.net.Socket;

public class ChatFunctions {
    public static void waitMessage(Socket socket){
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            while (true){
                String message = in.readUTF();
                if (message.equals("/end")) break;
                System.out.println(message);
            }
        } catch (IOException e) {
            System.out.println("Пользователь не в сети");
        }
    }

    public static void sendMessage(Socket socket){
        try {
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            while (true){
                String message = reader.readLine();
                if (message.equals("/end")) {
                    out.writeUTF("Пользователь отключился");
                    out.writeUTF("/end");
                    out.close();
                }
                out.writeUTF(message);
            }
        } catch (IOException e) {
            System.out.println("Не в сети");
        }
    }
}
