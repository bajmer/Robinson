package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import model.*;
import model.Character;
import model.actions.*;
import model.cards.*;
import model.cards.Stack;
import model.elements.Dices;
import model.elements.Marker;
import model.enums.PhaseType;
import model.enums.ProfessionType;
import model.enums.SexType;
import model.enums.cards.BeastType;
import model.enums.cards.DiscoveryTokenType;
import model.enums.cards.InventionType;
import model.enums.cards.StartingItemType;
import model.enums.cards.adventurecards.BuildingAdventureType;
import model.enums.cards.adventurecards.ExplorationAdventureType;
import model.enums.cards.adventurecards.GatheringResourcesAdventureType;
import model.enums.cards.eventcards.EventEffectType;
import model.enums.cards.eventcards.EventIconType;
import model.enums.cards.mysterycards.MysteryMonsterType;
import model.enums.cards.mysterycards.MysteryTrapType;
import model.enums.cards.mysterycards.MysteryTreasureType;
import model.enums.elements.DiceWallType;
import model.enums.elements.ResourceType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

import static model.enums.ProfessionType.*;
import static model.enums.cards.eventcards.EventEffectType.*;
import static model.enums.cards.eventcards.EventIconType.*;
import static model.enums.elements.MarkerType.*;

public class GameEngineController implements GameEndListener {
    private Logger logger = LogManager.getLogger(GameEngineController.class);
    private Scenario scenario;
    private GameInfo gameInfo;
    private Mappings mappings;
    private Stack inventionStack;
    private LinkedList<EventCard> eventStack;
    private Stack buildingAdventuresStack;
    private Stack gatheringResourcesAdventureStack;
    private Stack explorationAdventuresStack;
    private Stack hountingStack;
    private Stack mysteryStack;
    private Stack islandTilesStack;
    private Stack discoveryTokensStack;
    private Board board;
    private PhaseType phase;
    private Dices dices;
    private boolean isFriday;

    private List<Action> actionList;

    GameEngineController(int scenarioId,
                         Map<ProfessionType, SexType> choosedCharacters,
                         boolean isFriday,
                         boolean isDog,
                         EventEffectType wreckageEvent,
                         int startingItemsNumber) {

        this.isFriday = isFriday;

        if (isDog) {

        }

        mappings = new Mappings();
        gameInfo = new GameInfo();
        board = new Board();
        dices = new Dices();

        createScenario(scenarioId);
        createCharacters(choosedCharacters);
        createEventCardsDeck(wreckageEvent);
        createInventionCardsDeck();
        createAdventureCardsDecks();
        createHountingCardsDeck();
        createMysteryCardsDeck();
        createDiscoveryTokensStack();
        createStartingItems(startingItemsNumber);
        createIslandTilesStack();

        actionList = new ArrayList<>();
        actionList.add(new ThreadAction(new ArrayList<>(Arrays.asList(CARPENTER_MARKER, COOK_MARKER, EXPLORER_MARKER, SOLDIER_MARKER, FRIDAY_MARKER))));
        actionList.add(new HuntingAction(new ArrayList<>(Arrays.asList(CARPENTER_MARKER, COOK_MARKER, EXPLORER_MARKER, SOLDIER_MARKER, FRIDAY_MARKER, DOG_MARKER, HUNTING_HELPER_MARKER))));
        actionList.add(new BuildingAction(new ArrayList<>(Arrays.asList(CARPENTER_MARKER, COOK_MARKER, EXPLORER_MARKER, SOLDIER_MARKER, FRIDAY_MARKER, BUILDING_HELPER_MARKER))));
        actionList.add(new GatheringResourcesAction(new ArrayList<>(Arrays.asList(CARPENTER_MARKER, COOK_MARKER, EXPLORER_MARKER, SOLDIER_MARKER, FRIDAY_MARKER, GATHERING_RESOURCES_HELPER_MARKER))));
        actionList.add(new ExplorationAction(new ArrayList<>(Arrays.asList(CARPENTER_MARKER, COOK_MARKER, EXPLORER_MARKER, SOLDIER_MARKER, FRIDAY_MARKER, DOG_MARKER, EXPLORATION_HELPER_MARKER))));
        actionList.add(new CampOrderingAction(new ArrayList<>(Arrays.asList(CARPENTER_MARKER, COOK_MARKER, EXPLORER_MARKER, SOLDIER_MARKER, FRIDAY_MARKER))));
        actionList.add(new RestAction(new ArrayList<>(Arrays.asList(CARPENTER_MARKER, COOK_MARKER, EXPLORER_MARKER, SOLDIER_MARKER, FRIDAY_MARKER))));
    }

    public GameInfo getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(GameInfo gameInfo) {
        this.gameInfo = gameInfo;
    }

    private void createScenario(int scenarioId) {
        //        tworzenie scenariusza
        scenario = new Scenario(scenarioId,
                Mappings.getScenarioIdToRoundsNumberMapping().get(scenarioId),
                Mappings.getScenarioIdToRoundWeatherDicesMapMapping().get(scenarioId),
                this);
        logger.info("Utworzono scenariusz");
    }

    private void createCharacters(Map<ProfessionType, SexType> choosedCharacters) {
        //        tworzenie postaci
        for (Map.Entry<ProfessionType, SexType> entry : choosedCharacters.entrySet()) {
            ProfessionType profession = entry.getKey();
            SexType sex = entry.getValue();
            List<Marker> markers = new ArrayList<>();
            markers.add(new Marker(Mappings.getProfessionToMarkerMapping().get(profession)));
            markers.add(new Marker(Mappings.getProfessionToMarkerMapping().get(profession)));
            gameInfo.getCharacters().add(new Character(
                    profession,
                    sex,
                    Mappings.getProfessionToPersonalInventionMapping().get(profession),
                    Mappings.getProfessionToSpecialSkillMapping().get(profession),
                    Mappings.getProfessionToMoraleDownMapping().get(profession),
                    Mappings.getProfessionToLifeMapping().get(profession),
                    markers,
                    this));
        }

        if (isFriday) {
            gameInfo.getCharacters().add(new Friday(4, new ArrayList<>(Collections.singletonList(new Marker(FRIDAY_MARKER)))));
        }

        logger.info("Utworzono postacie");
    }

    private void createEventCardsDeck(EventEffectType wreckageEvent) {
//        karty wydarzeń
        LinkedList<EventCard> allEventsStack = new LinkedList<>();
        Arrays.asList(EventEffectType.values()).forEach(eventEffectType -> {
            if (eventEffectType != FOOD_CRATES && eventEffectType != WRECKED_LIFEBOAT && eventEffectType != CAPTAINS_CHEST) {
                allEventsStack.add(new EventCard(
                        eventEffectType,
                        Mappings.getEventEffectToEventIconMapping().get(eventEffectType),
                        Mappings.getEventEffectToThreatActionMapping().get(eventEffectType),
                        Mappings.getEventEffectToThreatEffectMapping().get(eventEffectType)
                ));
            }
        });
        Collections.shuffle(allEventsStack);

        eventStack = new LinkedList<>();
        int bookIconsCounter = 0;
        int questionmarkIconsCouter = 0;

        for (EventCard card : allEventsStack) {
            if (card.getEventIcon() == BOOK && bookIconsCounter < scenario.getRoundsNumber() / 2) {
                eventStack.add(card);
                bookIconsCounter++;
                continue;
            }
            if ((card.getEventIcon() == BUILDING_ADVENTURE ||
                    card.getEventIcon() == GATHERING_RESOURCES_ADVENTURE ||
                    card.getEventIcon() == EXPLORATION_ADVENTURE) &&
                    questionmarkIconsCouter < scenario.getRoundsNumber() / 2) {
                eventStack.add(card);
                questionmarkIconsCouter++;
            }
        }

        eventStack.addFirst(new EventCard(
                wreckageEvent,
                Mappings.getEventEffectToEventIconMapping().get(wreckageEvent),
                Mappings.getEventEffectToThreatActionMapping().get(wreckageEvent),
                Mappings.getEventEffectToThreatEffectMapping().get(wreckageEvent)
        ));

        logger.info("Przygotowano talię wydarzeń.");
        for (EventCard card : eventStack) {
            logger.debug(card.getEventEffect());
        }
    }

    private void createInventionCardsDeck() {
        //        karty pomysłów
        Stack allInventionsStack = new Stack();
        Arrays.asList(InventionType.values()).forEach(inventionType -> allInventionsStack.getStack().add(new InventionCard(
                inventionType,
                Mappings.getInventionToIsMandatoryMapping().get(inventionType),
                Mappings.getInventionToOwnerMapping().get(inventionType))));

        inventionStack = new Stack();
        for (Usable card : allInventionsStack.getStack()) {
            InventionCard inventionCard = (InventionCard) card;
            if (inventionCard.isMandatory()) {
                gameInfo.getIdeas().add(inventionCard);
            } else {
                ProfessionType owner = inventionCard.getOwner();
                if (owner == null) {
                    inventionStack.getStack().add(inventionCard);
                } else {
                    boolean characterEqualsOwner = false;
                    for (ICharacter character : gameInfo.getCharacters()) {
                        if (character instanceof Character) {
                            if (((Character) character).getProfession() == owner) {
                                characterEqualsOwner = true;
                            }
                        }
                    }
                    if (characterEqualsOwner) {
                        gameInfo.getIdeas().add(inventionCard);
                    } else {
                        inventionStack.getStack().add(inventionCard);
                    }
                }
            }
        }

        Collections.shuffle(inventionStack.getStack());
        for (int i = 0; i < 5; i++) {
            gameInfo.getIdeas().add((InventionCard) inventionStack.getStack().removeFirst());
        }
        logger.info("Wylosowano karty pomysłow");
        for (InventionCard invention : gameInfo.getIdeas()) {
            logger.debug(invention);
        }


        logger.debug("Pozostałe:");
        for (Usable card : inventionStack.getStack()) {
            InventionCard invention = (InventionCard) card;
            logger.debug(invention);
        }
    }

    private void createAdventureCardsDecks() {
        //        karty przygód
        buildingAdventuresStack = new Stack();
        Arrays.asList(BuildingAdventureType.values()).forEach(adventureType -> buildingAdventuresStack.getStack().add(
                new BuildingAdventureCard(adventureType, Mappings.getBuildingAdvntureToAdventureEventEffectMapping().get(adventureType))));
        Collections.shuffle(buildingAdventuresStack.getStack());

        gatheringResourcesAdventureStack = new Stack();
        Arrays.asList(GatheringResourcesAdventureType.values()).forEach(adventureType -> gatheringResourcesAdventureStack.getStack().add(
                new GatheringResourcesAdventureCard(adventureType, Mappings.getGatheringAdventureToAdventureEventEffectMapping().get(adventureType))));
        Collections.shuffle(gatheringResourcesAdventureStack.getStack());

        explorationAdventuresStack = new Stack();
        Arrays.asList(ExplorationAdventureType.values()).forEach(adventureType -> explorationAdventuresStack.getStack().add(
                new ExplorationAdventureCard(adventureType, Mappings.getExplorationAdventureToAdventureEventEffectMapping().get(adventureType))));
        Collections.shuffle(explorationAdventuresStack.getStack());
        logger.info("Przygotowano talie przygód");
    }

    private void createHountingCardsDeck() {
        //        karty bestii
        hountingStack = new Stack();
        Arrays.asList(BeastType.values()).forEach(beastType -> hountingStack.getStack().add(new BeastCard(
                beastType, Mappings.getBeastToBeastStatsMapping().get(beastType))));
        Collections.shuffle(hountingStack.getStack());
        logger.info("Przygotowano talię bestii");
    }

    private void createMysteryCardsDeck() {
        //        karty tajemnic
        mysteryStack = new Stack();
        Arrays.asList(MysteryTreasureType.values()).forEach(mysteryType -> mysteryStack.getStack().add(new MysteryTreasureCard(mysteryType)));
        Arrays.asList(MysteryMonsterType.values()).forEach(mysteryType -> mysteryStack.getStack().add(new MysteryMonsterCard(mysteryType)));
        Arrays.asList(MysteryTrapType.values()).forEach(mysteryType -> mysteryStack.getStack().add(new MysteryTrapCard(mysteryType)));
        Collections.shuffle(mysteryStack.getStack());
        logger.info("Przygotowano talię tajemnic");
    }

    private void createDiscoveryTokensStack() {
//        tworzenie stosu żetonów odkryć
        discoveryTokensStack = new Stack();
        Arrays.asList(DiscoveryTokenType.values()).forEach(discoveryToken -> discoveryTokensStack.getStack().add(
                new DiscoveryToken(discoveryToken)));
        Collections.shuffle(discoveryTokensStack.getStack());
        logger.info("Utworzono stos żetonów odkryć");
    }

    private void createStartingItems(int startingItemsNumber) {
        //        karty przedmiotów startowych
        Stack startingItemStack = new Stack();
        Arrays.asList(StartingItemType.values()).forEach(startingItemType -> startingItemStack.getStack().add(new StartingItemCard(startingItemType)));
        Collections.shuffle(startingItemStack.getStack());

        gameInfo.setStartingItems(new ArrayList<>());
        for (int i = 0; i < startingItemsNumber; i++) {
            gameInfo.getStartingItems().add((StartingItemCard) startingItemStack.getStack().removeFirst());
        }
        logger.info("Wylosowano przedmioty startowe");
        for (Usable card : gameInfo.getStartingItems()) {
            logger.debug(card);
        }
    }

    private void createIslandTilesStack() {
        islandTilesStack = new Stack();
        for (int i = 1; i <= 11; i++) {
            IslandTile islandTile = new IslandTile(
                    i,
                    Mappings.getIslandTileIdToTerrainMapping().get(i),
                    Mappings.getIslandTileIdToLeftSquareResourceMapping().get(i),
                    Mappings.getIslandTileIdToRightSquareResourceMapping().get(i),
                    Mappings.getIslandTileIdToHasAnimalSourceMapping().get(i),
                    Mappings.getIslandTileIdToTotemsNumberMapping().get(i),
                    Mappings.getIslandTileIdToDiscoveryTokensNumberMapping().get(i),
                    Mappings.getIslandTileIdToHasNaturalShelterMapping().get(i));

            if (i != 8) {
                islandTilesStack.getStack().add(islandTile);
            } else {
                gameInfo.getDiscoveredTiles().add(islandTile);
                board.getTilePositionIdToIslandTile().put(1, islandTile);
                gameInfo.setCamp(islandTile);
            }
        }

        Collections.shuffle(islandTilesStack.getStack());

        logger.info("Przygotowano stos kafelków wyspy");
    }

    public void nextPhase() {
        phase = Mappings.getCurrentPhaseToNextPhaseMapping().get(phase);
        if (phase == PhaseType.EVENT_PHASE) {
            scenario.nextRound();
            logger.info("***********************************************************");
            logger.info("Runda numer: " + scenario.getRound());

            int firstPlayerId;
            if (!isFriday) {
                firstPlayerId = (scenario.getRound() - 1) % gameInfo.getCharacters().size();
            } else {
                firstPlayerId = (scenario.getRound() - 1) % (gameInfo.getCharacters().size() - 1);
            }

            gameInfo.setFirstPlayer((Character) gameInfo.getCharacters().get(firstPlayerId));
            logger.debug("Pierwszy gracz: " + gameInfo.getFirstPlayer().getProfession());

            prepareAvaibleMarkers();
        }

        logger.info("Obecna faza: " + phase);
        runPhase();
    }

    private void prepareAvaibleMarkers() {
        for (ICharacter character : gameInfo.getCharacters()) {
            if (character instanceof Character) {
                character.getCharacterMarkers().forEach(marker -> gameInfo.getAvaibleCharactersMarkers().add(marker));
            }
        }
    }

    private void runPhase() {
        switch (phase) {
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

    private void runEventPhase() {
        EventCard card = eventStack.removeFirst();
        logger.info("--->Wydarzenie: " + card.getEventEffect());
        card.use();

        EventIconType eventIcon = card.getEventIcon();
        //handleIcon(eventIcon);

        gameInfo.getThreatActionCards().addFirst(card);
        if (gameInfo.getThreatActionCards().size() > 2) {
            EventCard threadCard = gameInfo.getThreatActionCards().removeLast();
            threadCard.runThreatEffect();
            logger.info("--->Efekt zagrożenia: " + threadCard.getThreatEffect());
        }
    }

    private void runMoralePhase() {
        int morale = GameInfo.getMoraleLevel();
        Character firstPlayer = gameInfo.getFirstPlayer();
        logger.info("--->Poziom morale: " + morale);

        firstPlayer.changeDetermination(morale);
    }

    private void runProductionPhase() {
        IslandTile camp = gameInfo.getCamp();
        int tmpWood = gameInfo.getAvaibleResources().getWoodAmount();
        int woodProduction = gameInfo.getProductionWoodNumber();
        int tmpFood = gameInfo.getAvaibleResources().getFoodAmount();
        int foodProduction = gameInfo.getProductionFoodNumber();

        if (camp.getLeftSquareResource() == ResourceType.WOOD || camp.getRightSquareResource() == ResourceType.WOOD) {
            gameInfo.getAvaibleResources().setWoodAmount(tmpWood + woodProduction);
            logger.debug("--->Otrzymano " + woodProduction + " drewna.");
        }
        if (camp.getLeftSquareResource() == ResourceType.FOOD || camp.getRightSquareResource() == ResourceType.FOOD) {
            gameInfo.getAvaibleResources().setFoodAmount(tmpFood + foodProduction);
            logger.debug("--->Otrzymano " + foodProduction + " jedzenia.");
        }
    }

    private void runActionPhase() {
//        przygotowanie

//        actionList.forEach(action -> action.runAction(this));
    }

    private void runWeatherPhase() {
        AtomicInteger rainCloudsNumber = new AtomicInteger();
        AtomicInteger snowCludsNumber = new AtomicInteger();
        AtomicBoolean beastAttack = new AtomicBoolean(false);
        AtomicBoolean palisadeDamage = new AtomicBoolean(false);
        AtomicBoolean foodDiscard = new AtomicBoolean(false);

        Mappings.getScenarioIdToRoundWeatherDicesMapMapping().get(scenario.getId()).get(scenario.getRound()).forEach(
                diceType -> {
                    DiceWallType result = dices.roll(dices.getDiceTypeToDiceMapping().get(diceType));
                    logger.info("--->Rzut kostką " + diceType + ". Wynik: " + result);
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
                    }
                }
        );

        if (snowCludsNumber.get() > 0) {
            logger.debug("--->Liczba chmur ziomowych: " + snowCludsNumber.get() + ". Za każdą chmurę należy odrzucić 1 drewno.");
            gameInfo.changeWoodLevel(-snowCludsNumber.get());
        }

        int cloudsSum = rainCloudsNumber.get() + snowCludsNumber.get();
        int missingRoofLevel = cloudsSum - gameInfo.getRoofLevel();
        if (missingRoofLevel > 0) {
            logger.debug("--->Liczba wszystkich chmur: " + cloudsSum + ". Za każdy brakujący poziom dachu należy odrzucić 1 drewno i 1 jedzenie.");
            gameInfo.changeWoodLevel(-missingRoofLevel);
            gameInfo.changeFoodLevel(-missingRoofLevel, null);
        }

        if (beastAttack.get()) {
            logger.info("--->Atak bestii o sile 3!");
        } else if (palisadeDamage.get()) {
            logger.info("--->Poziom palisady spada o 1!");
            gameInfo.changePalisadeLevel(-1);
        } else if (foodDiscard.get()) {
            logger.info("--->Należy odrzucić 1 jedzenie!");
            gameInfo.changeFoodLevel(-1, null);
        }
    }

    private void runNightPhase() {
        int requiredFood;
        if (!isFriday) {
            requiredFood = gameInfo.getCharacters().size();
        } else {
            requiredFood = gameInfo.getCharacters().size() - 1;
        }
        int foodAmount = gameInfo.getAvaibleResources().getFoodAmount();
        int longExpiryDateFoodAmount = gameInfo.getAvaibleResources().getLongExpiryDateFoodAmount();
        int allFoodAmount = foodAmount + longExpiryDateFoodAmount;

        logger.debug("--->Kolacja! Każda postać musi zjeść posiłek...");
        gameInfo.changeFoodLevel(-requiredFood, chooseStarvingCharacters(requiredFood - allFoodAmount));

//        opcja przeniesienia obozu
        IslandTile camp = gameInfo.getCamp();
        if (!gameInfo.isShelter() && !camp.isHasNaturalShelter()) {
            logger.info("--->Brak schronienia! Tę noc wszyscy spędzą pod gołym niebem!");
            gameInfo.getCharacters().forEach(character -> {
                if (character instanceof Character) {
                    character.changeLife(-1);
                }
            });
        }

        AtomicBoolean canStorageFood = new AtomicBoolean(false);
        gameInfo.getInventions().forEach(inventionCard -> {
            if (inventionCard.getInvention() == InventionType.CELLAR) canStorageFood.set(true);
        });
        gameInfo.getTreasures().forEach(mysteryTreasureCard -> {
            if (mysteryTreasureCard.getTreasureType() == MysteryTreasureType.BARREL
                /*|| mysteryTreasureCard.getTreasureType() == skrzynie*/) canStorageFood.set(true);
        });

        if (!canStorageFood.get()) {
            logger.debug("--->Nie masz w czym przechowywać jedzenia! Całe psujące się jedzenie należy odrzucić!");
            gameInfo.getAvaibleResources().setFoodAmount(0);
        }
    }

    private List<ProfessionType> chooseStarvingCharacters(int value) {
        List<ProfessionType> starvingProfessions = null;
        if (value > 1) {
            starvingProfessions = new ArrayList<>();
            List<ProfessionType> notChosenProfessions = new ArrayList<>();
            gameInfo.getCharacters().forEach(character -> {
                if (character instanceof Character) {
                    notChosenProfessions.add(((Character) character).getProfession());
                }
            });

            for (int i = 0; i < value; i++) {
                String result = showStarvingCharactersAlert(notChosenProfessions);
                if (result != null) {
                    if (result.equals(CARPENTER.toString())) {
                        notChosenProfessions.remove(CARPENTER);
                        starvingProfessions.add(CARPENTER);
                        logger.info("--->Dziś kolacji nie zje cieśla!");
                    } else if (result.equals(COOK.toString())) {
                        notChosenProfessions.remove(COOK);
                        starvingProfessions.add(COOK);
                        logger.info("--->Dziś kolacji nie zje kucharz!");
                    } else if (result.equals(EXPLORER.toString())) {
                        notChosenProfessions.remove(EXPLORER);
                        starvingProfessions.add(EXPLORER);
                        logger.info("--->Dziś kolacji nie zje odkrywca!");
                    } else if (result.equals(SOLDIER.toString())) {
                        notChosenProfessions.remove(SOLDIER);
                        starvingProfessions.add(SOLDIER);
                        logger.info("--->Dziś kolacji nie zje żołnierz!");
                    }
                }
            }
        }
        return starvingProfessions;
    }

    private String showStarvingCharactersAlert(List<ProfessionType> professions) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
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

    @Override
    public void handleGameEnd() {
        logger.info("KONIEC GRY!");
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Przegrałeś!");
        alert.setHeaderText("Koniec gry!");
        alert.setContentText("Twój wynik to:");
        alert.showAndWait();
    }

}
