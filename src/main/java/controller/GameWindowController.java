package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.enums.ProfessionType;
import model.enums.SexType;

import java.util.HashMap;
import java.util.Map;

import static model.enums.cards.eventcards.EventEffectType.FOOD_CRATES;

public class GameWindowController {
    @FXML
    Button button;

    private GameEngineController gameEngineController;

    public GameWindowController() {
        //        mock
        int scenarioId = 1;
        Map<ProfessionType, SexType> choosedCharacters = new HashMap<>();
        choosedCharacters.put(ProfessionType.CARPENTER, SexType.MAN);
        choosedCharacters.put(ProfessionType.COOK, SexType.WOMAN);
        choosedCharacters.put(ProfessionType.EXPLORER, SexType.MAN);
        boolean isFriday = true;
        boolean isDog = true;
        int startingItemsNumber = 2;

        gameEngineController = new GameEngineController(
                scenarioId,
                choosedCharacters,
                isFriday,
                isDog,
                FOOD_CRATES,
                startingItemsNumber);
    }

    @FXML
    void onAction(ActionEvent event) {
        gameEngineController.nextPhase();
    }
}
