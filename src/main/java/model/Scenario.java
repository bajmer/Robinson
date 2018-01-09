package model;

import controller.GameEndListener;
import model.enums.DiceType;

import java.util.List;
import java.util.Map;

public class Scenario implements IScenario {
    private int id;
    private int roundsNumber;
    private int round;
    private Map<Integer, List<DiceType>> roundIdToWeatherDicesMapping;
    private GameEndListener gameEndListener;

    public Scenario(int id, int roundsNumber, Map<Integer, List<DiceType>> roundIdToWeatherDicesMapping, GameEndListener gameEndListener) {
        this.id = id;
        this.roundsNumber = roundsNumber;
        this.roundIdToWeatherDicesMapping = roundIdToWeatherDicesMapping;
        this.round = 0;
        this.gameEndListener = gameEndListener;
    }

    public Map<Integer, List<DiceType>> getRoundIdToWeatherDicesMapping() {
        return roundIdToWeatherDicesMapping;
    }

    public void setRoundIdToWeatherDicesMapping(Map<Integer, List<DiceType>> roundIdToWeatherDicesMapping) {
        this.roundIdToWeatherDicesMapping = roundIdToWeatherDicesMapping;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoundsNumber() {
        return roundsNumber;
    }

    public void setRoundsNumber(int roundsNumber) {
        this.roundsNumber = roundsNumber;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void handleTotem() {
        switch (this.id) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            default:
                break;
        }
    }

    public void handleBook() {
        switch (this.id) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            default:
                break;
        }
    }

    public void nextRound() {
        round += 1;
        if (round > roundsNumber) {
            gameEndListener.handleGameEnd();
        }
    }

}
