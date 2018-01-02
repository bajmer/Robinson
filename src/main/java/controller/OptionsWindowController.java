package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class OptionsWindowController {
    @FXML
    Pane rootPane;

    @FXML
    Button button;

    @FXML
    void onAction(ActionEvent event) throws IOException {
        ((Stage) rootPane.getScene().getWindow()).close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gameWindow.fxml"));
        Parent root2 = fxmlLoader.load();

        Stage secondaryStage = new Stage();
        secondaryStage.setTitle("Second Window");
        secondaryStage.setScene(new Scene(root2, 400, 275));
        secondaryStage.show();
    }

}
