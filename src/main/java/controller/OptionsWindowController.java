package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class OptionsWindowController {
    @FXML
    Pane rootPane;

    @FXML
    Button button;

    @FXML
    void onAction(ActionEvent event) {
        ((Stage) rootPane.getScene().getWindow()).close();

//        GameEngineController gameEngineController = new GameEngineController();

//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gameWindow.fxml"));
//        Parent root2 = fxmlLoader.load();
//
//        Stage secondaryStage = new Stage();
//        secondaryStage.setTitle("Second Window");
//        secondaryStage.setScene(new Scene(root2, 400, 275));
//        secondaryStage.show();
    }

}
