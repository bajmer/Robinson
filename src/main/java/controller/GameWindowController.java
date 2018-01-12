package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import model.Action;
import model.Character;
import model.GameInfo;
import model.elements.Marker;
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

    public String showStarvingCharactersAlert() {
        Alert alert = new Alert(CONFIRMATION);
        alert.setTitle("Brakuje jedzenia!");
        alert.setHeaderText("Wygląda na to, że ktoś dzisiaj nie zje kolacji...");
        alert.setContentText("Wybierz postać, która będzie dziś głodować i otrzyma 2 rany.");

        List<ButtonType> buttonTypes = new ArrayList<>();
        GameInfo.getCharacters().stream().filter(character -> character instanceof Character).filter(x -> !((Character) x).isStarving()).forEach(
                character -> buttonTypes.add(new ButtonType(((Character) character).getProfession().toString()))
        );

        alert.getButtonTypes().setAll(buttonTypes);
        Optional<ButtonType> result = alert.showAndWait();

        return (result.isPresent() ? result.get().getText() : null);
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

    public String showSelectMarkerAlert() {
        Alert alert = new Alert(CONFIRMATION);
        alert.setTitle("Wybór znacznika.");
        alert.setHeaderText("Wybierz znacznik, do którego chcesz przypisać akcję.");

        List<ButtonType> buttonTypes = new ArrayList<>();
        GameInfo.getAllSelectionMarkers().stream().filter(Marker::isAvaible).forEach(
                marker -> buttonTypes.add(new ButtonType(marker.getMarkerType().toString())));

        alert.getButtonTypes().setAll(buttonTypes);
        Optional<ButtonType> result = alert.showAndWait();

        return (result.isPresent() ? result.get().getText() : null);
    }

    public String showSelectActionAlert() {
        Alert alert = new Alert(CONFIRMATION);
        alert.setTitle("Wybór akcji.");
        alert.setHeaderText("Wybierz akcję, którą chcesz wykonać.");

        List<ButtonType> buttonTypes = new ArrayList<>();
        gameEngineController.getActionList().stream().filter(Action::isAvaible).forEach(
                action -> buttonTypes.add(new ButtonType(action.getActionType().toString()))
        );

        alert.getButtonTypes().setAll(buttonTypes);
        Optional<ButtonType> result = alert.showAndWait();

        return (result.isPresent() ? result.get().getText() : null);
    }

    public void showCannotConnectMarkerWithActionAlert() {
        Alert alert = new Alert(WARNING);
        alert.setTitle("Nieprawidłowa akcja!");
        alert.setHeaderText("Nie można przypisać znacznika do tej akcji!");
        alert.showAndWait();
    }
}
