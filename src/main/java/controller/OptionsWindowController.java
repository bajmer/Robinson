package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import model.ChoosedCharacter;
import model.enums.ProfessionType;
import model.enums.SexType;

import java.util.ArrayList;
import java.util.List;

public class OptionsWindowController {
    @FXML
    Pane rootPane;

    @FXML
    Button button;

    @FXML
    void onAction(ActionEvent event) {
//        ((Stage) rootPane.getScene().getWindow()).close();

//        mock
        int scenarioId = 1;
        List<ChoosedCharacter> choosedCharacters = new ArrayList<>();
        choosedCharacters.add(new ChoosedCharacter(ProfessionType.CARPENTER, SexType.MAN));
        choosedCharacters.add(new ChoosedCharacter(ProfessionType.COOK, SexType.WOMAN));
        boolean isFriday = true;
        boolean isDog = true;

        GameEngineController gameEngineController = new GameEngineController(scenarioId, choosedCharacters, isFriday, isDog);

//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gameWindow.fxml"));
//        Parent root2 = fxmlLoader.load();
//
//        Stage secondaryStage = new Stage();
//        secondaryStage.setTitle("Second Window");
//        secondaryStage.setScene(new Scene(root2, 400, 275));
//        secondaryStage.show();
    }

}
