package ru.geekbrains.java2.client.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Random;
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
            "user1",
            "user2",
            "user3"
    );

    public void sendMessage(ActionEvent actionEvent) {
        if (messageField.getText().equals("")) return;
        String message = user + ": " + messageField.getText();
        messagesList.getItems().add(messagesList.getItems().size(), message);
        messageField.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messagesList.setItems(FXCollections.observableArrayList());
        contactsList.setItems(contacts);
        user = "All";
    }

    public void setUser(MouseEvent mouseEvent) {
        user = contactsList.getFocusModel().getFocusedItem();
    }
}
