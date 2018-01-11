package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import model.enums.ProfessionType;
import model.enums.SexType;

import java.util.*;

import static javafx.scene.control.Alert.AlertType.CONFIRMATION;
import static javafx.scene.control.Alert.AlertType.WARNING;

public class GameWindowController {
    @FXML
    Button button;

    private GameEngineController gameEngineController;

    public GameWindowController() {
        //        mock
        Map<ProfessionType, SexType> choosedCharacters = new HashMap<>();
        choosedCharacters.put(ProfessionType.CARPENTER, SexType.MAN);
        choosedCharacters.put(ProfessionType.COOK, SexType.WOMAN);
        choosedCharacters.put(ProfessionType.EXPLORER, SexType.MAN);

        gameEngineController = new GameEngineController(
                this,
                1,
                choosedCharacters,
                true,
                true,
                2);
    }

    @FXML
    void onAction(ActionEvent event) {
        gameEngineController.nextPhase();
    }

    public String showStarvingCharactersAlert(List<ProfessionType> professions) {
        Alert alert = new Alert(CONFIRMATION);
        alert.setTitle("Brakuje jedzenia!");
        alert.setHeaderText("Wygląda na to, że ktoś dzisiaj nie zje kolacji...");
        alert.setContentText("Wybierz postać, która będzie dziś głodować i otrzyma 2 rany.");

        List<ButtonType> buttonTypes = new ArrayList<>();
        for (ProfessionType profession : professions) {
            buttonTypes.add(new ButtonType(profession.toString()));
        }
        alert.getButtonTypes().setAll(buttonTypes);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent()) {
            return result.get().getText();
        } else {
            return null;
        }
    }

    public void showGameEndAlert() {
        Alert alert = new Alert(WARNING);
        alert.setTitle("Przegrałeś!");
        alert.setHeaderText("Koniec gry!");
        alert.setContentText("Twój wynik to:");
        alert.showAndWait();
    }

    public void showFridayDeathAlert() {
        Alert alert = new Alert(WARNING);
        alert.setTitle("Śmierć Piętaszka!");
        alert.setHeaderText("Piętaszek zakończył swój żywot.");
        alert.showAndWait();
    }
}
