package ru.geekbrains.java2.client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import ru.geekbrains.java2.client.model.Client;


public class ClientApp extends Application {
    private static Stage primaryStage;
    private static Parent chat;
    private static Parent auth;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.auth = FXMLLoader.load(getClass().getResource("view/AuthWindow.fxml"));
        this.chat = FXMLLoader.load(getClass().getResource("view/ChatWindow.fxml"));

        primaryStage.setTitle("Авторизация");
        primaryStage.setScene(new Scene(auth));
        primaryStage.setResizable(false);
        Image icon = new Image(String.valueOf(getClass().getResource("logoApp.png")));
        primaryStage.getIcons().add(icon);
        primaryStage.show();
    }

    public static void switchToChat(String title) {
        Client.getClient().startChat();

        primaryStage.close();
        primaryStage.setResizable(true);
        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(chat));
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                try {
                    Client.getClient().close();
                    Platform.exit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        primaryStage.show();
    }
}
