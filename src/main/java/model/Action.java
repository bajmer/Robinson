package model;

import model.cards.BeastCard;
import model.elements.Marker;
import model.enums.ActionType;
import model.enums.cards.BeastType;
import model.enums.elements.MarkerType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static model.enums.elements.MarkerType.*;

public class Action {
    private Logger logger = LogManager.getLogger(Action.class);
    private ActionType actionType;
    private List<MarkerType> allowedMarkers;
    private List<Marker> assignedMarkers;
    private boolean isAvaible;

    public Action(ActionType actionType, List<MarkerType> extraMarkers) {
        this.actionType = actionType;
        this.allowedMarkers = new ArrayList<>(Arrays.asList(CARPENTER_MARKER, COOK_MARKER, EXPLORER_MARKER, SOLDIER_MARKER, FRIDAY_MARKER));
        if (extraMarkers != null) {
            this.allowedMarkers.addAll(extraMarkers);
        }
        this.assignedMarkers = new ArrayList<>();
        this.isAvaible = false;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public boolean isAvaible() {
        return isAvaible;
    }

    public void setAvaible(boolean avaible) {
        isAvaible = avaible;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public List<MarkerType> getAllowedMarkers() {
        return allowedMarkers;
    }

    public void setAllowedMarkers(List<MarkerType> allowedMarkers) {
        this.allowedMarkers = allowedMarkers;
    }

    public List<Marker> getAssignedMarkers() {
        return assignedMarkers;
    }

    public void setAssignedMarkers(List<Marker> assignedMarkers) {
        this.assignedMarkers = assignedMarkers;
    }

    public void runAction() {
        switch (actionType) {
            case THREAD_ACTION:
//                threadCard.runThreatAction(assignedMarkers);
                break;
            case HUNTING_ACTION:
                int assignedMarkersNumber = assignedMarkers.size();
                int huntingNumber = assignedMarkersNumber / 2;

                for (int i = 0; i < huntingNumber; i++) {
                    ICharacter leaderCharacter = assignedMarkers.get(2 * i).getCharacter();
                    BeastCard beast = GameInfo.getAvaibleBeastCards().removeFirst();
                    BeastType name = beast.getBeast();
                    int strength = beast.getStrength();
                    int weaponLevelDecrease = beast.getWeaponLevelDecrease();
                    int foodAmount = beast.getFoodAmount();
                    int hideAmount = beast.getHideAmount();

                    logger.info("Walka z bestią: " + name + ". Siła: " + strength +
                            ", spadek poziomu broni: " + weaponLevelDecrease +
                            ", ilość pożywienia: " + foodAmount + ", ilość skór:" + hideAmount);

                    int weaponLevel = GameInfo.getWeaponLevel();
                    if (weaponLevel < strength) {
                        leaderCharacter.changeLife(weaponLevel - strength);
                    }

                    GameInfo.changeWeaponLevel(beast.getWeaponLevelDecrease());
                    GameInfo.changeFoodLevel(beast.getFoodAmount());
                    GameInfo.changeHideLevel(beast.getHideAmount());
                }
                break;
            case BUILDING_ACTION:
                break;
            case GATHERING_RESOURCES_ACTION:
                break;
            case EXPLORATION_ACTION:
                break;
            case CAMP_ORDERING_ACTION:
                assignedMarkers.forEach(marker -> {
                    marker.getCharacter().changeDetermination(2);
                    GameInfo.changeMoraleLevel(1);
                    logger.info("Porządkowanie obozu!");
                });
                break;
            case REST_ACTION:
                assignedMarkers.forEach(marker -> {
                    marker.getCharacter().changeLife(1);
                    logger.info("Odpoczynek!");
                });
                break;
        }
    }

    public void addMarkerToAction(Marker marker) {
        assignedMarkers.add(marker);
    }
}
