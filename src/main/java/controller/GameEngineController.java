package controller;

import model.*;
import model.Character;
import model.cards.*;
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

import static model.enums.ActionType.*;
import static model.enums.cards.eventcards.EventEffectType.*;
import static model.enums.cards.eventcards.EventIconType.*;
import static model.enums.elements.MarkerType.*;

public class GameEngineController implements GameEventsListener {
    private Logger logger = LogManager.getLogger(GameEngineController.class);
    private GameWindowController gameWindowController;
    private Scenario scenario;
    private Board board;
    private PhaseType phase;
    private Dices dices;
    private boolean isFriday;
    private boolean isDog;
    private List<Action> actionList;

    GameEngineController(
            GameWindowController gameWindowController,
            int scenarioId,
            Map<ProfessionType, SexType> choosedCharacters,
            boolean isFriday,
            boolean isDog,
            int startingItemsNumber) {

        this.gameWindowController = gameWindowController;
        this.isFriday = isFriday;
        this.isDog = isDog;

        new Mappings();
        board = new Board();
        dices = new Dices();

        createScenario(scenarioId);
        createCharacters(choosedCharacters);
        createEventCardsDeck();
        createInventionCardsDeck();
        createAdventureCardsDecks();
        createHountingCardsDeck();
        createMysteryCardsDeck();
        createDiscoveryTokensStack();
        createStartingItems(startingItemsNumber);
        createIslandTilesStack();

        createCharacterMarkers();
        createActions();

    }

    public List<Action> getActionList() {
        return actionList;
    }

    public void setActionList(List<Action> actionList) {
        this.actionList = actionList;
    }

    private void createScenario(int scenarioId) {
        scenario = new Scenario(scenarioId,
                Mappings.getScenarioIdToRoundsNumberMapping().get(scenarioId),
                Mappings.getScenarioIdToRoundWeatherDicesMapMapping().get(scenarioId),
                this);
        logger.info("Utworzono scenariusz.");
    }

    private void createCharacters(Map<ProfessionType, SexType> choosedCharacters) {
        for (Map.Entry<ProfessionType, SexType> entry : choosedCharacters.entrySet()) {
            ProfessionType profession = entry.getKey();
            SexType sex = entry.getValue();
            GameInfo.getCharacters().add(new Character(
                    profession,
                    sex,
                    Mappings.getProfessionToPersonalInventionMapping().get(profession),
                    Mappings.getProfessionToSpecialSkillMapping().get(profession),
                    Mappings.getProfessionToMoraleDownMapping().get(profession),
                    Mappings.getProfessionToLifeMapping().get(profession),
                    this));
        }

        if (isFriday) {
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

        if (isFriday) {
            GameInfo.getAllSelectionMarkers().add(new Marker(
                    FRIDAY_MARKER,
                    GameInfo.getCharacters().stream().filter(character -> character instanceof Friday).findAny().get()
            ));
        }
        if (isDog) {
            GameInfo.getAllSelectionMarkers().add(new Marker(DOG_MARKER, null));
        }
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
        logger.info("Przygotowano talie przygód.");
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
                board.getTilePositionIdToIslandTile().put(1, islandTile);
                GameInfo.setCamp(islandTile);
            }
        }
        Collections.shuffle(Decks.getIslandTilesStack());

        logger.info("Przygotowano stos kafelków wyspy.");
    }

    private void createActions() {
        actionList = new ArrayList<>();
        actionList.add(new Action(THREAD_ACTION, null));
        actionList.add(new Action(HUNTING_ACTION, new ArrayList<>(Arrays.asList(DOG_MARKER, HUNTING_HELPER_MARKER))));
        actionList.add(new Action(BUILDING_ACTION, new ArrayList<>(Collections.singletonList(BUILDING_HELPER_MARKER))));
        actionList.add(new Action(GATHERING_RESOURCES_ACTION, new ArrayList<>(Collections.singletonList(GATHERING_RESOURCES_HELPER_MARKER))));
        actionList.add(new Action(EXPLORATION_ACTION, new ArrayList<>(Arrays.asList(DOG_MARKER, EXPLORATION_HELPER_MARKER))));
        actionList.add(new Action(CAMP_ORDERING_ACTION, null));
        actionList.add(new Action(REST_ACTION, null));
    }

    public void nextPhase() {
        phase = Mappings.getCurrentPhaseToNextPhaseMapping().get(phase);
        if (phase == PhaseType.EVENT_PHASE) {
            scenario.nextRound();
            logger.info("***********************************************************");
            logger.info("Runda numer: " + scenario.getRound());

            int firstPlayerId;
            if (!isFriday) {
                firstPlayerId = (scenario.getRound() - 1) % GameInfo.getCharacters().size();
            } else {
                firstPlayerId = (scenario.getRound() - 1) % (GameInfo.getCharacters().size() - 1);
            }

            GameInfo.setFirstPlayer((Character) GameInfo.getCharacters().get(firstPlayerId));
            logger.debug("Pierwszy gracz: " + GameInfo.getFirstPlayer().getProfession());

            resetAvaibleMarkers();
        }

        logger.info("Obecna faza: " + phase);
        runPhase();
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
        EventCard card = Decks.getEventStackDeck().removeFirst();
        logger.info("--->Wydarzenie: " + card.getEventEffect());
        card.use();

        EventIconType eventIcon = card.getEventIcon();
        //handleIcon(eventIcon);

        GameInfo.getThreatActionCards().addFirst(card);
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

        firstPlayer.changeDetermination(morale);
    }

    private void runProductionPhase() {
        IslandTile camp = GameInfo.getCamp();
        int tmpWood = GameInfo.getAvaibleResources().getWoodAmount();
        int woodProduction = GameInfo.getProductionWoodNumber();
        int tmpFood = GameInfo.getAvaibleResources().getFoodAmount();
        int foodProduction = GameInfo.getProductionFoodNumber();

        if (camp.getLeftSquareResource() == ResourceType.WOOD || camp.getRightSquareResource() == ResourceType.WOOD) {
            GameInfo.getAvaibleResources().setWoodAmount(tmpWood + woodProduction);
            logger.debug("--->Otrzymano " + woodProduction + " drewna.");
        }
        if (camp.getLeftSquareResource() == ResourceType.FOOD || camp.getRightSquareResource() == ResourceType.FOOD) {
            GameInfo.getAvaibleResources().setFoodAmount(tmpFood + foodProduction);
            logger.debug("--->Otrzymano " + foodProduction + " jedzenia.");
        }
    }

    private void runActionPhase() {
        resetAvaibleActions();
        handleMarkerSelect();
//        actionList.forEach(Action::runAction);
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
            logger.debug("--->Liczba chmur ziomowych: " + snowCludsNumber.get() + ". Za każdą chmurę należy odrzucić 1 drewno.");
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
        } else if (palisadeDamage.get()) {
            logger.info("--->Poziom palisady spada o 1!");
            GameInfo.changePalisadeLevel(-1);
        } else if (foodDiscard.get()) {
            logger.info("--->Należy odrzucić 1 jedzenie!");
            GameInfo.changeFoodLevel(-1);
        }
    }

    private void runNightPhase() {
        int requiredFood;
        if (!isFriday) {
            requiredFood = GameInfo.getCharacters().size();
        } else {
            requiredFood = GameInfo.getCharacters().size() - 1;
        }
        int foodAmount = GameInfo.getAvaibleResources().getFoodAmount();
        int longExpiryDateFoodAmount = GameInfo.getAvaibleResources().getLongExpiryDateFoodAmount();
        int allFoodAmount = foodAmount + longExpiryDateFoodAmount;

        logger.debug("--->Kolacja! Każda postać musi zjeść posiłek...");
        resetStarvingCharacters();
        if (requiredFood > allFoodAmount) {
            chooseStarvingCharacters(requiredFood - allFoodAmount);
            GameInfo.changeFoodLevel(-allFoodAmount);
        } else {
            GameInfo.changeFoodLevel(-requiredFood);
        }

//        opcja przeniesienia obozu
        IslandTile camp = GameInfo.getCamp();
        if (!GameInfo.isShelter() && !camp.isHasNaturalShelter()) {
            logger.info("--->Brak schronienia! Tę noc wszyscy spędzą pod gołym niebem!");
            GameInfo.getCharacters().forEach(character -> {
                if (character instanceof Character) {
                    character.changeLife(-1);
                }
            });
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
            GameInfo.getAvaibleResources().setFoodAmount(0);
        }
    }

    private void chooseStarvingCharacters(int value) {
        for (int i = 0; i < value; i++) {
            String result = gameWindowController.showStarvingCharactersAlert();
            Character character = (Character) GameInfo.getCharacters().stream()
                    .filter(x -> (result.equals(((Character) x).getProfession().toString()))).findAny().get();

            character.changeLife(-2);
            character.setStarving(true);
        }
    }

    private void handleMarkerSelect() {
        while (GameInfo.getAllSelectionMarkers().stream().anyMatch(Marker::isAvaible)) {
            String resultMarker = gameWindowController.showSelectMarkerAlert();
            Marker marker = GameInfo.getAllSelectionMarkers().stream().filter(
                    x -> resultMarker.equals(x.getMarkerType().toString())).filter(Marker::isAvaible).findAny().get();

            String resultAction = gameWindowController.showSelectActionAlert();
            Action action = actionList.stream().filter(
                    x -> resultAction.equals(x.getActionType().toString())).findAny().get();

            if (action.getAllowedMarkers().contains(marker.getMarkerType())) {
                action.addMarkerToAction(marker);
                marker.setAvaible(false);
            } else {
                gameWindowController.showCannotConnectMarkerWithActionAlert();
            }
        }
    }

    private void resetAvaibleMarkers() {
        GameInfo.getAllSelectionMarkers().forEach(marker -> marker.setAvaible(true));
    }

    private void resetAvaibleActions() {
        actionList.forEach(action -> action.setAvaible(true));
    }

    private void resetStarvingCharacters() {
        GameInfo.getCharacters().forEach(character -> ((Character) character).setStarving(false));
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

}
