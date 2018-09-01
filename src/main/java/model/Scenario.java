package model;

import controller.engine.GameEventsListener;
import model.enums.elements.DiceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Map;

public class Scenario {
    private Logger logger = LogManager.getLogger(Scenario.class);

    private int id;
    private int roundsNumber;
    private int round;
    private GameEventsListener gameEventsListener;

    public Scenario(int id, int roundsNumber, Map<Integer, List<DiceType>> roundIdToWeatherDicesMapping, GameEventsListener gameEventsListener) {
        this.id = id;
        this.roundsNumber = roundsNumber;
        this.round = 0;
        this.gameEventsListener = gameEventsListener;
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

    public void nextRound() {
        round += 1;
        if (round > roundsNumber) {
            gameEventsListener.handleGameEnd();
        }
        logger.info("********************************************************************************");
        logger.info("Runda numer " + round);
    }
}
