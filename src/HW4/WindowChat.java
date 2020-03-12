package HW4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class WindowChat extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent chat = FXMLLoader.load(getClass().getResource("Controllers//Chat.fxml"));
        primaryStage.setTitle("Чат");
        primaryStage.setScene(new Scene(chat));
        primaryStage.setResizable(false);
        //Image icon = new Image(String.valueOf(getClass().getResource("")));
        //primaryStage.getIcons().add(icon);
        primaryStage.show();
    }
}
