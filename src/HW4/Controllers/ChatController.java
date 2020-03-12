package HW4.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    private String user;
    private ObservableList<String> fakeContacts = FXCollections.observableArrayList(
            "peak",
            "falsify",
            "mud",
            "net",
            "value",
            "sickness",
            "package",
            "coma",
            "important",
            "particular"
    );

    @FXML
    private ListView<String> messagesList;
    @FXML
    private ListView<String> contacts;
    @FXML
    private TextField writeField;

    public void sendMessage(ActionEvent actionEvent) {
        if (writeField.getText().equals("")) return;
        String message = user + ": " + writeField.getText();
        messagesList.getItems().add(messagesList.getItems().size(), message);
        writeField.setText("");
    }

    public void endWriteMessage(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.ENTER)) {
            String message = writeField.getText().replaceAll("\\n", "");
            writeField.setText(message);
            sendMessage(new ActionEvent());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        messagesList.setItems(FXCollections.observableArrayList());
        contacts.setItems(fakeContacts);
        user = fakeContacts.get(new Random().nextInt(fakeContacts.size()));
    }

    public void enterUser(MouseEvent mouseEvent) {
        user = contacts.getFocusModel().getFocusedItem();
    }
}
