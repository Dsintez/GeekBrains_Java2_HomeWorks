package ru.geekbrains.java2.client.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.paint.Color;
import ru.geekbrains.java2.client.ClientApp;
import ru.geekbrains.java2.client.Connection;

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
    @FXML
    private ImageView logoAuth;

    private boolean connect = false;

    public void authorize() {
        if (!connect) return;
        Connection connection = new Connection(loginField.getText(), passwordField.getText());
        String message = connection.auth();
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
        try {
            Connection.start();
            errorLabel.setTextFill(Color.GREEN);
            errorLabel.setText("Связь с сервером установлена");
            connect = true;
        } catch (IOException e) {
            errorLabel.setTextFill(Color.RED);
            errorLabel.setText("Нет соединения с сервером");
            connect = false;
        }
    }

    public void pressEnter(KeyEvent keyEvent) {
        if ("".equals(loginField.getText()) || "".equals(passwordField.getText())) return;
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            authorize();
        }
    }
}
