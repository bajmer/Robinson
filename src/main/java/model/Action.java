package model;

import model.cards.BeastCard;
import model.cards.RequirableCard;
import model.elements.Marker;
import model.enums.action.ActionType;
import model.enums.action.BuildingActionType;
import model.enums.cards.BeastType;
import model.enums.elements.MarkerType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Action {
    private Logger logger = LogManager.getLogger(Action.class);
    private ActionType actionType;
    private List<MarkerType> allowedMarkers;
    private List<Marker> assignedMarkers;
    private boolean actionAvailable;
    private RequirableCard requirableCard;
    private BuildingActionType buildingActionType;

    public Action(ActionType actionType) {
        this.actionType = actionType;
        this.allowedMarkers = Mappings.getActionToAllowedMarkerMapping().get(actionType);
        this.assignedMarkers = new ArrayList<>();
        this.actionAvailable = true;
    }

    public Action(ActionType actionType, RequirableCard requirableCard) {
        this.actionType = actionType;
        this.allowedMarkers = Mappings.getActionToAllowedMarkerMapping().get(actionType);
        this.assignedMarkers = new ArrayList<>();
        this.actionAvailable = true;
        this.requirableCard = requirableCard;
    }

    public Action(ActionType actionType, BuildingActionType buildingActionType, boolean actionAvailable) {
        this.actionType = actionType;
        this.allowedMarkers = Mappings.getActionToAllowedMarkerMapping().get(actionType);
        this.assignedMarkers = new ArrayList<>();
        this.buildingActionType = buildingActionType;
        this.actionAvailable = actionAvailable;
    }

    public ActionType getActionType() {
        return actionType;
    }

    public void setActionType(ActionType actionType) {
        this.actionType = actionType;
    }

    public boolean isActionAvailable() {
        return actionAvailable;
    }

    public void setActionAvailable(boolean actionAvailable) {
        this.actionAvailable = actionAvailable;
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

    public RequirableCard getRequirableCard() {
        return requirableCard;
    }

    public void setRequirableCard(RequirableCard requirableCard) {
        this.requirableCard = requirableCard;
    }

    public BuildingActionType getBuildingActionType() {
        return buildingActionType;
    }

    public void setBuildingActionType(BuildingActionType buildingActionType) {
        this.buildingActionType = buildingActionType;
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
                    BeastCard beast = GameInfo.getAvailableBeastCards().removeFirst();
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
