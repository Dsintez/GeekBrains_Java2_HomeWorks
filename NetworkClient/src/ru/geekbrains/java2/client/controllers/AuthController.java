package ru.geekbrains.java2.client.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import ru.geekbrains.java2.client.ClientApp;
import ru.geekbrains.java2.client.model.Client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthController implements Initializable {
    @FXML
    private Label errorLabel;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField loginField;

    private boolean connect;

    public void authorize() {
        if ("".equals(loginField.getText()) || "".equals(passwordField.getText())) return;
        if (!connect) return;
        Client client = new Client(loginField.getText(), passwordField.getText());
        String message = client.auth();
        if (message.startsWith("/auth")){
            String[] messageParts = message.split("\\s+", 2);
            message = messageParts[1];
            ClientApp.switchToChat(message);
        } else {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText(message);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reConnect();
    }

    public void pressEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            authorize();
        }
    }

    public void reConnect() {
        try {
            Client.connect();
            errorLabel.setTextFill(Color.GREEN);
            errorLabel.setText("Связь с сервером установлена");
            connect = true;
        } catch (IOException e) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Нет соединения с сервером");
            connect = false;
        }
    }
}
