package ru.geekbrains.java2.client.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import ru.geekbrains.java2.client.model.Client;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    @FXML
    private ListView<String> contactsList;
    @FXML
    private TextField messageField;
    @FXML
    private ListView<String> messagesList;

    private String user;
    private ObservableList<String> contacts = FXCollections.observableArrayList(
            "All",
            "username1",
            "username2",
            "username3"
    );

    public void sendMessage() {
        String message = messageField.getText();
        if (message.equals("")) return;
//        try {
        Client.getClient().sendMessage(message, user);
//        } catch (IOException e) {
//            messagesList.getItems().add("Соединение разорвано, сообщение не отправлено");
//        }
        messagesList.getItems().add(messagesList.getItems().size(), "Я: " + message);
        messageField.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messagesList.setItems(FXCollections.observableArrayList());
        contactsList.setItems(contacts);
        user = "All";
        Client.chatController = this;
    }

    public void setUser(MouseEvent mouseEvent) {
        user = contactsList.getFocusModel().getFocusedItem();
    }

    public ListView<String> getContactsList() {
        return contactsList;
    }

    public ListView<String> getMessagesList() {
        return messagesList;
    }

    public void addMessage(String message) {
        messagesList.getItems().add(message);
    }

    public void pressEnter(KeyEvent keyEvent) {
        if (!"".equals(messageField.getText())) {
            if (keyEvent.getCode().equals(KeyCode.ENTER)) {
                sendMessage();
            }
        }
    }
}
