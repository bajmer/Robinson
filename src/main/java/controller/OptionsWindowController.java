package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import model.enums.ProfessionType;
import model.enums.SexType;
import model.enums.cards.wreckagecards.WreckageEventEffectType;

import java.util.HashMap;
import java.util.Map;

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
        Map<ProfessionType, SexType> choosedCharacters = new HashMap<>();
        choosedCharacters.put(ProfessionType.CARPENTER, SexType.MAN);
        choosedCharacters.put(ProfessionType.COOK, SexType.WOMAN);
        boolean isFriday = true;
        boolean isDog = true;
        WreckageEventEffectType wreckageEvent = WreckageEventEffectType.FOOD_CRATES;
        int startingItemsNumber = 2;

        GameEngineController gameEngineController = new GameEngineController(
                scenarioId,
                choosedCharacters,
                isFriday,
                isDog,
                wreckageEvent,
                startingItemsNumber);

//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../gameWindow.fxml"));
//        Parent root2 = fxmlLoader.load();
//
//        Stage secondaryStage = new Stage();
//        secondaryStage.setTitle("Second Window");
//        secondaryStage.setScene(new Scene(root2, 400, 275));
//        secondaryStage.show();
    }

}
