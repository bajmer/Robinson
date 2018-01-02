package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.enums.ProfessionType;
import model.enums.SexType;
import model.enums.cards.wreckagecards.WreckageEventEffectType;

import java.util.HashMap;
import java.util.Map;

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
        boolean isFriday = true;
        boolean isDog = true;
        WreckageEventEffectType wreckageEvent = WreckageEventEffectType.FOOD_CRATES;
        int startingItemsNumber = 2;

        gameEngineController = new GameEngineController(
                scenarioId,
                choosedCharacters,
                isFriday,
                isDog,
                wreckageEvent,
                startingItemsNumber);
    }

    @FXML
    void onAction(ActionEvent event) {
        gameEngineController.nextPhase();
    }
}
