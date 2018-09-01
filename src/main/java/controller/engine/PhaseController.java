package controller.engine;

import controller.gui.GameWindowController;
import model.*;
import model.Character;
import model.cards.EventCard;
import model.cards.InventionCard;
import model.cards.IslandTile;
import model.elements.Dices;
import model.enums.PhaseType;
import model.enums.TerrainType;
import model.enums.action.ActionType;
import model.enums.action.BuildingActionType;
import model.enums.cards.InventionType;
import model.enums.cards.mysterycards.MysteryTreasureType;
import model.enums.elements.DiceWallType;
import model.enums.elements.ResourceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class PhaseController {
    private Logger logger = LogManager.getLogger(PhaseController.class);

    private GameController gameController;
    private PhaseType currentPhase = null;

    public PhaseController(GameController gameController) {
        this.gameController = gameController;
    }

    public void nextPhase() {
        currentPhase = Mappings.getCurrentPhaseToNextPhaseMapping().get(currentPhase);
        if (currentPhase == PhaseType.EVENT_PHASE) {
            gameController.getScenario().nextRound();
            prepareNewRound();
        }
        logger.info("Obecna faza: " + currentPhase);
        runPhase();
    }

    private void runPhase() {
        switch (currentPhase) {
            case EVENT_PHASE:
                runEventPhase();
                break;
            case MORALE_PHASE:
                runMoralePhase();
                break;
            case PRODUCTION_PHASE:
                runProductionPhase();
                break;
            case ACTION_PHASE:
                runActionPhase();
                break;
            case WEATHER_PHASE:
                runWeatherPhase();
                break;
            case NIGHT_PHASE:
                runNightPhase();
                break;
        }
    }

    private void prepareNewRound() {
        chooseFirstPlayer();
        resetAvailableMarkers();
        resetStarvingCharacters();
    }

    private void chooseFirstPlayer() {
        int round = gameController.getScenario().getRound();
        int firstPlayerId = !GameInfo.isFriday() ? (round - 1) % GameInfo.getCharacters().size() : (round - 1) % (GameInfo.getCharacters().size() - 1);
        GameInfo.setFirstPlayer((Character) GameInfo.getCharacters().get(firstPlayerId));
        logger.debug("Pierwszy gracz: " + GameInfo.getFirstPlayer().getProfession());
    }

    private void resetAvailableMarkers() {
        GameInfo.getAllSelectionMarkers().forEach(marker -> marker.setAvailable(true));
    }

//    private void resetAvailableActions() {
//        actionList.forEach(action -> action.setActionAvailable(true));
//    }

    private void resetStarvingCharacters() {
        GameInfo.getCharacters().stream().filter(character -> character instanceof Character).forEach(character -> ((Character) character).setStarving(false));
    }

    private void runEventPhase() {
        EventCard eventCard = Decks.getEventStackDeck().removeFirst();
        int scenarioId = gameController.getScenario().getId();
        logger.info("--->Wydarzenie: " + eventCard.getEventEffect());
        eventCard.runEventEffect();
        eventCard.runEventIconEffect(scenarioId);

        GameInfo.getThreatActionCards().addFirst(eventCard);
        if (GameInfo.getThreatActionCards().size() > 2) {
            EventCard threadCard = GameInfo.getThreatActionCards().removeLast();
            threadCard.runThreatEffect();
            logger.info("--->Efekt zagrożenia: " + threadCard.getThreatEffect());
        }
    }

    private void runMoralePhase() {
        int morale = GameInfo.getMoraleLevel();
        Character firstPlayer = GameInfo.getFirstPlayer();
        logger.info("--->Poziom morale: " + morale);

        if (morale != 0) firstPlayer.changeDetermination(morale);
    }

    private void runProductionPhase() {
        IslandTile camp = GameInfo.getCamp();
        int tmpWood = GameInfo.getAvailableResources().getWoodAmount();
        int woodProduction = GameInfo.getProductionWoodNumber();
        int tmpFood = GameInfo.getAvailableResources().getFoodAmount();
        int foodProduction = GameInfo.getProductionFoodNumber();

        if (!camp.isLeftSquareResourceCovered()) {
            ResourceType leftResource = camp.getLeftSquareResource();
            if (leftResource == ResourceType.WOOD) {
                GameInfo.getAvailableResources().setWoodAmount(tmpWood + woodProduction);
                logger.debug("--->Otrzymano " + woodProduction + " drewna.");
            } else if (leftResource == ResourceType.FOOD) {
                GameInfo.getAvailableResources().setFoodAmount(tmpFood + foodProduction);
                logger.debug("--->Otrzymano " + foodProduction + " jedzenia.");
            }
        }
        if (!camp.isRightSquareResourceCovered()) {
            ResourceType rightResource = camp.getRightSquareResource();
            if (rightResource == ResourceType.WOOD) {
                GameInfo.getAvailableResources().setWoodAmount(tmpWood + woodProduction);
                logger.debug("--->Otrzymano " + woodProduction + " drewna.");
            } else if (rightResource == ResourceType.FOOD) {
                GameInfo.getAvailableResources().setFoodAmount(tmpFood + foodProduction);
                logger.debug("--->Otrzymano " + foodProduction + " jedzenia.");
            }
        }
    }

    private void runActionPhase() {
        resetTmpAvailableResources();
        List<Action> availableActions = createAllAvailableActionsList();
        assignMarkersToActions(availableActions);
//        gameController.getActionList().forEach(Action::prepareAction);
//        handleMarkerSelect();
//        actionList.forEach(Action::runAction);
    }

    private void resetTmpAvailableResources() {
        GameInfo.getTmpAvailableResources().setWoodAmount(GameInfo.getAvailableResources().getWoodAmount());
        GameInfo.getTmpAvailableResources().setFoodAmount(GameInfo.getAvailableResources().getFoodAmount());
        GameInfo.getTmpAvailableResources().setLongExpiryDateFoodAmount(GameInfo.getAvailableResources().getLongExpiryDateFoodAmount());
        GameInfo.getTmpAvailableResources().setHideAmount(GameInfo.getAvailableResources().getHideAmount());
    }

    private List<Action> createAllAvailableActionsList() {
        List<Action> allAvailableActions = new ArrayList<>();
        Arrays.asList(ActionType.values()).forEach(actionType -> prepareAction(actionType, allAvailableActions));
        return allAvailableActions;
    }

    private void prepareAction(ActionType actionType, List<Action> allAvailableActions) {
        switch (actionType) {
            case THREAD_ACTION:
                for (EventCard eventCard : GameInfo.getThreatActionCards()) {
                    if (checkRequirements(eventCard.getActionRequirements())) {
                        allAvailableActions.add(new Action(actionType, eventCard));
                    }
                }
                break;
            case HUNTING_ACTION:
                GameInfo.getAvailableBeastCards().forEach(beastCard -> allAvailableActions.add(new Action(actionType)));
                break;
            case BUILDING_ACTION:
                for (InventionCard inventionCard : GameInfo.getInventions()) {
                    if (checkRequirements(inventionCard.getActionRequirements())) {
                        allAvailableActions.add(new Action(actionType, inventionCard));
                    }
                }

                Arrays.asList(BuildingActionType.values()).forEach(
                        buildingActionType -> {
                            if (checkBuildRequirements(buildingActionType)) {
                                allAvailableActions.add(new Action(actionType, buildingActionType, true));
                            } else {
                                allAvailableActions.add(new Action(actionType, buildingActionType, false));
                            }
                        }
                );
                break;
            case GATHERING_RESOURCES_ACTION:
                GameInfo.getDiscoveredTiles().forEach(islandTile -> {
                    if (islandTile != GameInfo.getCamp()) {
                        if (islandTile.getLeftSquareResource() != null) {
//                            allAvailableActions.add(new Action(actionType,)); //todo
                        }
                        if (islandTile.getRightSquareResource() != null) {
//                            allAvailableActions.add(new Action(actionType,)); //todo
                        }
                    }
                });
                break;
            case EXPLORATION_ACTION:
                List<Integer> usedPositions = new ArrayList<>();
                GameInfo.getDiscoveredTiles().forEach(discoveredTile -> {
                    List<Integer> discoveredTileNeighbours = Mappings.getTilePositionToNeighboursMapping().get(discoveredTile.getPositionOnBoard());
                    discoveredTileNeighbours.forEach(neighbourPosition -> {
                        if (Mappings.getTilePositionToNeighboursMapping().get(neighbourPosition) == null) {
                            if (!usedPositions.contains(neighbourPosition)) {
//                                allAvailableActions.add(new Action()); //todo
                                usedPositions.add(neighbourPosition);
                            }
                        }
                    });
                });
//                todo Obliczanie odległości od obozu

                break;
            case CAMP_ORDERING_ACTION:
                allAvailableActions.add(new Action(actionType));
                break;
            case REST_ACTION:
                allAvailableActions.add(new Action(actionType));
                break;
        }
    }

    private boolean checkRequirements(ActionRequirements actionRequirements) {
        boolean okTerrains = true;
        boolean okItems = true;
        boolean okWood = true;
        boolean okHide = true;

        List<TerrainType> discoveredTerrains = new ArrayList<>();
        GameInfo.getDiscoveredTiles().forEach(islandTile -> {
                    if (!discoveredTerrains.contains(islandTile.getTerrainType())) {
                        discoveredTerrains.add(islandTile.getTerrainType());
                    }
                }
        );
        if (!discoveredTerrains.containsAll(actionRequirements.getRequiredTerrains())) okTerrains = false;
        if (!GameInfo.getInventions().containsAll(actionRequirements.getRequiredItems())) okItems = false;
        if (GameInfo.getTmpAvailableResources().getWoodAmount() < actionRequirements.getRequiredWoods()) okWood = false;
        if (GameInfo.getTmpAvailableResources().getHideAmount() < actionRequirements.getRequiredHides()) okHide = false;
        return okTerrains && okItems && okWood && okHide;
    }

    private boolean checkBuildRequirements(BuildingActionType buildingActionType) {
        boolean okShelter = true;
        boolean okWood = true;
        boolean okHide = true;
        boolean okWoodOrHide = true;
        int requiredWood;
        int requiredHide;
        int tmpWood = GameInfo.getTmpAvailableResources().getWoodAmount();
        int tmpHide = GameInfo.getTmpAvailableResources().getHideAmount();
        switch (buildingActionType) {
            case SHELTER_BUILD:
                requiredWood = calculateRequiredWood(buildingActionType);
                requiredHide = calculateRequiredHide(buildingActionType);
                if (GameInfo.isShelter()) okShelter = false;
                if (tmpWood < requiredWood) okWood = false;
                if (tmpHide < requiredHide) okHide = false;
                okWoodOrHide = okWood || okHide;
                break;
            case ROOF_BUILD:
                requiredWood = calculateRequiredWood(buildingActionType);
                requiredHide = calculateRequiredHide(buildingActionType);
                if (!GameInfo.isShelter()) okShelter = false;
                if (tmpWood < requiredWood) okWood = false;
                if (tmpHide < requiredHide) okHide = false;
                okWoodOrHide = okWood || okHide;
                break;
            case PALISADE_BUILD:
                requiredWood = calculateRequiredWood(buildingActionType);
                requiredHide = calculateRequiredHide(buildingActionType);
                if (!GameInfo.isShelter()) okShelter = false;
                if (tmpWood < requiredWood) okWood = false;
                if (tmpHide < requiredHide) okHide = false;
                okWoodOrHide = okWood || okHide;
                break;
            case WEAPON_BUILD:
                requiredWood = calculateRequiredWood(buildingActionType);
                requiredHide = calculateRequiredHide(buildingActionType);
                if (tmpWood < requiredWood) okWood = false;
                if (tmpHide < requiredHide) okHide = false;
                okWoodOrHide = okWood || okHide;
                break;
        }
        return okShelter && okWoodOrHide;
    }

    private int calculateRequiredWood(BuildingActionType buildingActionType) {
        int charactersNumber = (int) GameInfo.getCharacters().stream().filter(iCharacter -> iCharacter instanceof Character).count();
        int requiredWood;
        if (buildingActionType == BuildingActionType.SHELTER_BUILD ||
                buildingActionType == BuildingActionType.ROOF_BUILD ||
                buildingActionType == BuildingActionType.PALISADE_BUILD) {
            if (charactersNumber <= 2) {
                requiredWood = 2;
            } else {
                requiredWood = charactersNumber;
            }
        } else {
            requiredWood = 1;
        }
        return requiredWood;
    }

    private int calculateRequiredHide(BuildingActionType buildingActionType) {
        int charactersNumber = (int) GameInfo.getCharacters().stream().filter(iCharacter -> iCharacter instanceof Character).count();
        int requiredHide;
        if (buildingActionType == BuildingActionType.SHELTER_BUILD ||
                buildingActionType == BuildingActionType.ROOF_BUILD ||
                buildingActionType == BuildingActionType.PALISADE_BUILD) {
            if (charactersNumber <= 2) {
                requiredHide = 1;
            } else {
                requiredHide = charactersNumber - 1;
            }
        } else {
            requiredHide = 0;
        }
        return requiredHide;
    }

    private void assignMarkersToActions(List<Action> avibleActions) {
        for (Action action : avibleActions) {

        }
    }

    private void handleTotem(int scenarioId) {
        switch (scenarioId) {
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

    private void runWeatherPhase() {
        AtomicInteger rainCloudsNumber = new AtomicInteger();
        AtomicInteger snowCludsNumber = new AtomicInteger();
        AtomicBoolean beastAttack = new AtomicBoolean(false);
        AtomicBoolean palisadeDamage = new AtomicBoolean(false);
        AtomicBoolean foodDiscard = new AtomicBoolean(false);

        Scenario scenario = gameController.getScenario();
        Dices dices = gameController.getDices();
        Mappings.getScenarioIdToRoundWeatherDicesMapMapping().get(scenario.getId()).get(scenario.getRound()).forEach(
                diceType -> {
                    DiceWallType result = dices.roll(dices.getDiceTypeToDiceMapping().get(diceType));
                    logger.info("--->Rzut kością pogody " + diceType + ". Wynik: " + result);
                    switch (result) {
                        case SINGLE_RAIN:
                            rainCloudsNumber.getAndIncrement();
                            break;
                        case DOUBLE_RAIN:
                            rainCloudsNumber.getAndAdd(2);
                            break;
                        case SINGLE_SNOW:
                            snowCludsNumber.getAndIncrement();
                            break;
                        case DOUBLE_SNOW:
                            snowCludsNumber.getAndAdd(2);
                            break;
                        case BEAST_ATTACK:
                            beastAttack.set(true);
                            break;
                        case PALISADE_DAMAGE:
                            palisadeDamage.set(true);
                            break;
                        case FOOD_DISCARD:
                            foodDiscard.set(true);
                            break;
                        case NOTHING:
                            break;
                    }
                }
        );

        if (snowCludsNumber.get() > 0) {
            logger.debug("--->Liczba chmur zimowych: " + snowCludsNumber.get() + ". Za każdą chmurę należy odrzucić 1 drewno.");
            GameInfo.changeWoodLevel(-snowCludsNumber.get());
        }

        int cloudsSum = rainCloudsNumber.get() + snowCludsNumber.get();
        int missingRoofLevel = cloudsSum - GameInfo.getRoofLevel();
        if (missingRoofLevel > 0) {
            logger.debug("--->Liczba wszystkich chmur: " + cloudsSum + ". Za każdy brakujący poziom dachu należy odrzucić 1 drewno i 1 jedzenie.");
            GameInfo.changeWoodLevel(-missingRoofLevel);
            GameInfo.changeFoodLevel(-missingRoofLevel);
        }

        if (beastAttack.get()) {
            logger.info("--->Atak bestii o sile 3!");
            int missingWeaponLevel = 3 - GameInfo.getWeaponLevel();
            if (missingWeaponLevel > 0) {
                GameInfo.getCharacters().stream().filter(iCharacter -> iCharacter instanceof Character).forEach(character -> character.changeLife(-missingWeaponLevel));
            }
        } else if (palisadeDamage.get()) {
            logger.info("--->Poziom palisady spada o 1!");
            GameInfo.changePalisadeLevel(-1);
        } else if (foodDiscard.get()) {
            logger.info("--->Należy odrzucić 1 jedzenie!");
            GameInfo.changeFoodLevel(-1);
        }
    }

    private void runNightPhase() {
        int requiredFood = !GameInfo.isFriday() ? GameInfo.getCharacters().size() : GameInfo.getCharacters().size() - 1;
        int foodAmount = GameInfo.getAvailableResources().getFoodAmount();
        int longExpiryDateFoodAmount = GameInfo.getAvailableResources().getLongExpiryDateFoodAmount();
        int allFoodAmount = foodAmount + longExpiryDateFoodAmount;

        logger.debug("--->Kolacja! Każda postać musi zjeść posiłek...");
        if (requiredFood > allFoodAmount) {
            chooseStarvingCharacters(requiredFood - allFoodAmount);
            GameInfo.changeFoodLevel(-allFoodAmount);
        } else {
            GameInfo.changeFoodLevel(-requiredFood);
        }

//        todo opcja przeniesienia obozu

        IslandTile camp = GameInfo.getCamp();
        if (!GameInfo.isShelter() && !camp.isNaturalShelter()) {
            logger.info("--->Brak schronienia! Tę noc wszyscy spędzą pod gołym niebem!");
            GameInfo.getCharacters().stream().filter(iCharacter -> iCharacter instanceof Character).forEach(character -> character.changeLife(-1));
        }

        AtomicBoolean canStorageFood = new AtomicBoolean(false);
        GameInfo.getInventions().forEach(inventionCard -> {
            if (inventionCard.getInvention() == InventionType.CELLAR) canStorageFood.set(true);
        });
        GameInfo.getTreasures().forEach(mysteryTreasureCard -> {
            if (mysteryTreasureCard.getTreasureType() == MysteryTreasureType.BARREL
                /*|| mysteryTreasureCard.getTreasureType() == skrzynie*/) canStorageFood.set(true);
        });

        if (!canStorageFood.get()) {
            logger.debug("--->Nie masz w czym przechowywać jedzenia! Całe psujące się jedzenie należy odrzucić!");
            GameInfo.getAvailableResources().setFoodAmount(0);
        }
    }

    private void chooseStarvingCharacters(int value) {
        GameWindowController gameWindowController = gameController.getGameWindowController();
        for (int i = 0; i < value; i++) {
            String result = gameWindowController.showStarvingCharactersAlert();
            Character character = (Character) GameInfo.getCharacters().stream()
                    .filter(x -> (result.equals(((Character) x).getProfession().toString()))).findAny().get();

            character.changeLife(-2);
            character.setStarving(true);
        }
    }

//    private void handleMarkerSelect() {
//        GameWindowController gameWindowController = gameController.getGameWindowController();
//        while (GameInfo.getAllSelectionMarkers().stream().anyMatch(Marker::isActionAvailable)) {
//            String resultMarker = gameWindowController.showSelectMarkerAlert();
//            Marker marker = GameInfo.getAllSelectionMarkers().stream().filter(
//                    x -> resultMarker.equals(x.getMarkerType().toString())).filter(Marker::isActionAvailable).findAny().get();
//
//            String resultAction = gameWindowController.showSelectActionAlert();
//            Action action = actionList.stream().filter(
//                    x -> resultAction.equals(x.getActionType().toString())).findAny().get();
//
//            if (action.getAllowedMarkers().contains(marker.getMarkerType())) {
//                action.addMarkerToAction(marker);
//                marker.setActionAvailable(false);
//            } else {
//                gameWindowController.showCannotConnectMarkerWithActionAlert();
//            }
//        }
//    }

}
