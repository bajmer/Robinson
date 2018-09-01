package controller.engine;

import controller.gui.GameWindowController;
import model.*;
import model.Character;
import model.cards.*;
import model.elements.Dices;
import model.elements.Marker;
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
import model.enums.cards.mysterycards.MysteryMonsterType;
import model.enums.cards.mysterycards.MysteryTrapType;
import model.enums.cards.mysterycards.MysteryTreasureType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

import static model.enums.cards.eventcards.EventEffectType.*;
import static model.enums.cards.eventcards.EventIconType.*;
import static model.enums.elements.MarkerType.DOG_MARKER;
import static model.enums.elements.MarkerType.FRIDAY_MARKER;

public class GameController implements GameEventsListener {
    private Logger logger = LogManager.getLogger(GameController.class);
    private GameWindowController gameWindowController;
    private PhaseController phaseController;
    private Scenario scenario;
    private Board board;
    private Dices dices;
//    private List<Action> actionList;

    public GameController(GameWindowController gameWindowController, int scenarioId, Map<ProfessionType, SexType> choosedCharacters,
                          boolean isFriday, boolean isDog, int startingItemsNumber) {

        this.gameWindowController = gameWindowController;
        this.phaseController = new PhaseController(this);
        new Mappings();
        board = new Board();
        dices = new Dices();
        GameInfo.setFriday(isFriday);
        GameInfo.setDog(isDog);

        prepareGame(scenarioId, choosedCharacters, startingItemsNumber);
    }

    private void prepareGame(int scenarioId, Map<ProfessionType, SexType> choosedCharacters, int startingItemsNumber) {
        createScenario(scenarioId);
        createCharacters(choosedCharacters);
        createCharacterMarkers();
        createEventCardsDeck();
        createInventionCardsDeck();
        createAdventureCardsDecks();
        createHountingCardsDeck();
        createMysteryCardsDeck();
        createDiscoveryTokensStack();
        createStartingItems(startingItemsNumber);
        createIslandTilesStack();
//        createActions();
        logger.info("Nowa gra została przygotowana.");
    }

    private void createScenario(int scenarioId) {
        scenario = new Scenario(scenarioId, Mappings.getScenarioIdToRoundsNumberMapping().get(scenarioId),
                Mappings.getScenarioIdToRoundWeatherDicesMapMapping().get(scenarioId), this);
        logger.info("Utworzono scenariusz.");
    }

    private void createCharacters(Map<ProfessionType, SexType> choosedCharacters) {
        for (Map.Entry<ProfessionType, SexType> entry : choosedCharacters.entrySet()) {
            ProfessionType profession = entry.getKey();
            SexType sex = entry.getValue();
            GameInfo.getCharacters().add(
                    new Character(profession, sex,
                            Mappings.getProfessionToPersonalInventionMapping().get(profession),
                            Mappings.getProfessionToSpecialSkillMapping().get(profession),
                            Mappings.getProfessionToMoraleDownMapping().get(profession),
                            Mappings.getProfessionToLifeMapping().get(profession),
                            this));
        }

        if (GameInfo.isFriday()) {
            GameInfo.getCharacters().add(new Friday(4, this));
        }
        logger.info("Utworzono postacie.");
    }

    private void createCharacterMarkers() {
        GameInfo.getCharacters().stream().filter(character -> character instanceof Character).forEach(character -> {
            for (int i = 0; i < 2; i++) {
                GameInfo.getAllSelectionMarkers().add(new Marker(
                        Mappings.getProfessionToMarkerMapping().get(((Character) character).getProfession()),
                        character));

            }
        });

        if (GameInfo.isFriday()) {
            GameInfo.getAllSelectionMarkers().add(new Marker(
                    FRIDAY_MARKER,
                    GameInfo.getCharacters().stream().filter(character -> character instanceof Friday).findAny().get()
            ));
        }
        if (GameInfo.isDog()) {
            GameInfo.getAllSelectionMarkers().add(new Marker(DOG_MARKER, null));
        }
        logger.info("Utworzono znaczniki postaci.");
    }

    private void createEventCardsDeck() {
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

        int bookIconsCounter = 0;
        int questionmarkIconsCouter = 0;
        for (EventCard card : allEventsStack) {
            if (card.getEventIcon() == BOOK && bookIconsCounter < scenario.getRoundsNumber() / 2) {
                Decks.getEventStackDeck().add(card);
                bookIconsCounter++;
                continue;
            }
            if ((card.getEventIcon() == BUILDING_ADVENTURE ||
                    card.getEventIcon() == GATHERING_RESOURCES_ADVENTURE ||
                    card.getEventIcon() == EXPLORATION_ADVENTURE) &&
                    questionmarkIconsCouter < scenario.getRoundsNumber() / 2) {
                Decks.getEventStackDeck().add(card);
                questionmarkIconsCouter++;
            }
        }

        List<EventEffectType> wreckageEvents = new ArrayList<>();
        wreckageEvents.add(FOOD_CRATES);
        wreckageEvents.add(WRECKED_LIFEBOAT);
        wreckageEvents.add(CAPTAINS_CHEST);
        EventEffectType wreckageEvent = wreckageEvents.get(new Random().nextInt(wreckageEvents.size()));

        Decks.getEventStackDeck().addFirst(new EventCard(
                wreckageEvent,
                Mappings.getEventEffectToEventIconMapping().get(wreckageEvent),
                Mappings.getEventEffectToThreatActionMapping().get(wreckageEvent),
                Mappings.getEventEffectToThreatEffectMapping().get(wreckageEvent)
        ));

        logger.info("Przygotowano talię wydarzeń.");
    }

    private void createInventionCardsDeck() {
        LinkedList<InventionCard> allInventionsStack = new LinkedList<>();
        Arrays.asList(InventionType.values()).forEach(inventionType -> {
            AtomicBoolean scenarioInvention = new AtomicBoolean(false);
            Mappings.getScenarioIdToInventionMapping().values().forEach(inventionTypes -> {
                if (inventionTypes.contains(inventionType)) {
                    scenarioInvention.set(true);
                }
            });

            if (!scenarioInvention.get()) {
                allInventionsStack.add(new InventionCard(
                        inventionType,
                        Mappings.getInventionToIsMandatoryMapping().get(inventionType),
                        Mappings.getInventionToOwnerMapping().get(inventionType),
                        Mappings.getInventionToMultipleInventionMapping().get(inventionType)
                ));
            }
        });

        for (InventionCard inventionCard : allInventionsStack) {
            if (inventionCard.isMandatory()) {
                GameInfo.getIdeas().add(inventionCard);
            } else {
                ProfessionType owner = inventionCard.getOwner();
                if (owner == null) {
                    Decks.getInventionCardsDeck().add(inventionCard);
                } else {
                    boolean characterEqualsOwner = false;
                    for (ICharacter character : GameInfo.getCharacters()) {
                        if (character instanceof Character) {
                            if (((Character) character).getProfession() == owner) {
                                characterEqualsOwner = true;
                            }
                        }
                    }
                    if (characterEqualsOwner) {
                        GameInfo.getIdeas().add(inventionCard);
                    } else {
                        Decks.getInventionCardsDeck().add(inventionCard);
                    }
                }
            }
        }

        Collections.shuffle(Decks.getInventionCardsDeck());
        for (int i = 0; i < 5; i++) {
            GameInfo.getIdeas().add(Decks.getInventionCardsDeck().removeFirst());
        }
        Mappings.getScenarioIdToInventionMapping().get(scenario.getId()).forEach(inventionType -> GameInfo.getIdeas().add(
                new InventionCard(inventionType,
                        false,
                        null,
                        Mappings.getInventionToMultipleInventionMapping().get(inventionType))
        ));

        logger.info("Wylosowano karty pomysłow.");
        for (InventionCard invention : GameInfo.getIdeas()) {
            logger.debug(invention);
        }
    }

    private void createAdventureCardsDecks() {
        Arrays.asList(BuildingAdventureType.values()).forEach(adventureType -> Decks.getBuildingAdventureCardsDeck().add(
                new BuildingAdventureCard(adventureType, Mappings.getBuildingAdvntureToAdventureEventEffectMapping().get(adventureType))));
        Collections.shuffle(Decks.getBuildingAdventureCardsDeck());

        Arrays.asList(GatheringResourcesAdventureType.values()).forEach(adventureType -> Decks.getGatheringResourcesAdventureCardsDeck().add(
                new GatheringResourcesAdventureCard(adventureType, Mappings.getGatheringAdventureToAdventureEventEffectMapping().get(adventureType))));
        Collections.shuffle(Decks.getGatheringResourcesAdventureCardsDeck());

        Arrays.asList(ExplorationAdventureType.values()).forEach(adventureType -> Decks.getExplorationAdventureCardsDeck().add(
                new ExplorationAdventureCard(adventureType, Mappings.getExplorationAdventureToAdventureEventEffectMapping().get(adventureType))));
        Collections.shuffle(Decks.getExplorationAdventureCardsDeck());
        logger.info("Przygotowano talię przygód.");
    }

    private void createHountingCardsDeck() {
        Arrays.asList(BeastType.values()).forEach(beastType -> Decks.getBeastCardsDeck().add(new BeastCard(
                beastType, Mappings.getBeastToBeastStatsMapping().get(beastType))));
        Collections.shuffle(Decks.getBeastCardsDeck());
        logger.info("Przygotowano talię bestii.");
    }

    private void createMysteryCardsDeck() {
        Arrays.asList(MysteryTreasureType.values()).forEach(mysteryType -> Decks.getMysteryCardsDeck().add(new MysteryTreasureCard(mysteryType)));
        Arrays.asList(MysteryMonsterType.values()).forEach(mysteryType -> Decks.getMysteryCardsDeck().add(new MysteryMonsterCard(mysteryType)));
        Arrays.asList(MysteryTrapType.values()).forEach(mysteryType -> Decks.getMysteryCardsDeck().add(new MysteryTrapCard(mysteryType)));
        Collections.shuffle(Decks.getMysteryCardsDeck());
        logger.info("Przygotowano talię tajemnic.");
    }

    private void createDiscoveryTokensStack() {
        Arrays.asList(DiscoveryTokenType.values()).forEach(discoveryToken -> Decks.getDiscoveryTokensStack().add(
                new DiscoveryToken(discoveryToken)));
        Collections.shuffle(Decks.getDiscoveryTokensStack());
        logger.info("Utworzono stos żetonów odkryć.");
    }

    private void createStartingItems(int startingItemsNumber) {
        LinkedList<StartingItemCard> startingItemStack = new LinkedList<>();
        Arrays.asList(StartingItemType.values()).forEach(startingItemType -> startingItemStack.add(new StartingItemCard(startingItemType)));
        Collections.shuffle(startingItemStack);

        GameInfo.setStartingItems(new ArrayList<>());
        for (int i = 0; i < startingItemsNumber; i++) {
            GameInfo.getStartingItems().add(startingItemStack.removeFirst());
        }
        logger.info("Wylosowano przedmioty startowe.");
        for (StartingItemCard card : GameInfo.getStartingItems()) {
            logger.debug(card);
        }
    }

    private void createIslandTilesStack() {
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
                Decks.getIslandTilesStack().add(islandTile);
            } else {
                GameInfo.getDiscoveredTiles().add(islandTile);
//                todo Do usunięcia klasa board?
//                board.getTilePositionIdToIslandTile().put(1, islandTile);
                islandTile.setTileDiscovered(true);
                islandTile.setPositionOnBoard(1);
                GameInfo.setCamp(islandTile);
            }
        }
        Collections.shuffle(Decks.getIslandTilesStack());

        logger.info("Przygotowano stos kafelków wyspy.");
    }

//    private void createActions() {
//        actionList = new ArrayList<>();
//        Mappings.getActionToAllowedMarkerMapping().forEach((actionType, markerTypes) -> actionList.add(new Action(actionType, markerTypes)));
//    }

    public void handleNextPhase() {
        phaseController.nextPhase();
    }

    @Override
    public void handleGameEnd() {
        logger.info("KONIEC GRY!");
        gameWindowController.showGameEndAlert();
    }

    @Override
    public void handleFridayDeath() {
        logger.info("Piętaszek zginął!");
        gameWindowController.showFridayDeathAlert();
    }

    public GameWindowController getGameWindowController() {
        return gameWindowController;
    }

    public void setGameWindowController(GameWindowController gameWindowController) {
        this.gameWindowController = gameWindowController;
    }

    public PhaseController getPhaseController() {
        return phaseController;
    }

    public void setPhaseController(PhaseController phaseController) {
        this.phaseController = phaseController;
    }

    public Scenario getScenario() {
        return scenario;
    }

    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    public Dices getDices() {
        return dices;
    }

    public void setDices(Dices dices) {
        this.dices = dices;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

//    public List<Action> getActionList() {
//        return actionList;
//    }
//
//    public void setActionList(List<Action> actionList) {
//        this.actionList = actionList;
//    }
}
