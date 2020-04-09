package ru.geekbrains.java2.client.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class LoadingFiles {

    public static final String FILE_NAME = "BufferMessage.txt";
    //public static final String SEPARATOR = " *-*-* ";

    public static String[] loadHistoryMessage(String pref, int numberOfUploadedMessages) {
        String[] messages = new String[numberOfUploadedMessages];
        try {
            File file = new File(pref + FILE_NAME);
            if (!file.exists()) file.createNewFile();
            final List<String> listMessages = Files.readAllLines(Paths.get(file.toURI()));
            if (listMessages.size() > 100) {
                messages = listMessages.subList(listMessages.size() - 100, listMessages.size()).toArray(messages);
            } else {
                messages = listMessages.toArray(messages);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < messages.length; i++) {
            if (messages[i] == null) {
                messages = Arrays.copyOf(messages, i);
                return messages;
            }
        }
        return messages;
    }

    public static void saveMessage(String pref, String message) {
        if (message == null) return;
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(pref + FILE_NAME, true));
            writer.write(message + System.lineSeparator());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}