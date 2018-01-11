package controller;

import model.*;
import model.Character;
import model.actions.*;
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

import static model.enums.ProfessionType.*;
import static model.enums.cards.eventcards.EventEffectType.*;
import static model.enums.cards.eventcards.EventIconType.*;
import static model.enums.elements.MarkerType.*;

public class GameEngineController implements GameEventsListener {
    private Logger logger = LogManager.getLogger(GameEngineController.class);
    private GameWindowController gameWindowController;
    private Scenario scenario;
    private LinkedList<InventionCard> inventionCardsDeck;
    private LinkedList<EventCard> eventStackDeck;
    private LinkedList<BuildingAdventureCard> buildingAdventureCardsDeck;
    private LinkedList<GatheringResourcesAdventureCard> gatheringResourcesAdventureCardsDeck;
    private LinkedList<ExplorationAdventureCard> explorationAdventureCardsDeck;
    private LinkedList<BeastCard> beastCardsDeck;
    private LinkedList<Usable> mysteryCardsDeck;
    private LinkedList<IslandTile> islandTilesStack;
    private LinkedList<DiscoveryToken> discoveryTokensStack;
    private Board board;
    private PhaseType phase;
    private Dices dices;
    private boolean isFriday;

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

        if (isDog) {

        }
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
        GameInfo.getCharacters().forEach(character -> {
            if (character instanceof Character) {
                GameInfo.getAvaibleCharacterMarkers().add(new Marker(
                        Mappings.getProfessionToMarkerMapping().get(((Character) character).getProfession()),
                        character
                ));
            } else {
                GameInfo.getAvaibleCharacterMarkers().add(new Marker(FRIDAY_MARKER, character));
            }
        });
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

        eventStackDeck = new LinkedList<>();
        int bookIconsCounter = 0;
        int questionmarkIconsCouter = 0;

        for (EventCard card : allEventsStack) {
            if (card.getEventIcon() == BOOK && bookIconsCounter < scenario.getRoundsNumber() / 2) {
                eventStackDeck.add(card);
                bookIconsCounter++;
                continue;
            }
            if ((card.getEventIcon() == BUILDING_ADVENTURE ||
                    card.getEventIcon() == GATHERING_RESOURCES_ADVENTURE ||
                    card.getEventIcon() == EXPLORATION_ADVENTURE) &&
                    questionmarkIconsCouter < scenario.getRoundsNumber() / 2) {
                eventStackDeck.add(card);
                questionmarkIconsCouter++;
            }
        }

        List<EventEffectType> wreckageEvents = new ArrayList<>();
        wreckageEvents.add(FOOD_CRATES);
        wreckageEvents.add(WRECKED_LIFEBOAT);
        wreckageEvents.add(CAPTAINS_CHEST);
        EventEffectType wreckageEvent = wreckageEvents.get(new Random().nextInt(wreckageEvents.size()));

        eventStackDeck.addFirst(new EventCard(
                wreckageEvent,
                Mappings.getEventEffectToEventIconMapping().get(wreckageEvent),
                Mappings.getEventEffectToThreatActionMapping().get(wreckageEvent),
                Mappings.getEventEffectToThreatEffectMapping().get(wreckageEvent)
        ));

        logger.info("Przygotowano talię wydarzeń.");
    }

    private void createInventionCardsDeck() {
        LinkedList<InventionCard> allInventionsStack = new LinkedList<>();
        Arrays.asList(InventionType.values()).forEach(inventionType -> allInventionsStack.add(new InventionCard(
                inventionType,
                Mappings.getInventionToIsMandatoryMapping().get(inventionType),
                Mappings.getInventionToOwnerMapping().get(inventionType))));

        inventionCardsDeck = new LinkedList<>();
        for (Usable card : allInventionsStack) {
            InventionCard inventionCard = (InventionCard) card;
            if (inventionCard.isMandatory()) {
                GameInfo.getIdeas().add(inventionCard);
            } else {
                ProfessionType owner = inventionCard.getOwner();
                if (owner == null) {
                    inventionCardsDeck.add(inventionCard);
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
                        inventionCardsDeck.add(inventionCard);
                    }
                }
            }
        }

        Collections.shuffle(inventionCardsDeck);
        for (int i = 0; i < 5; i++) {
            GameInfo.getIdeas().add(inventionCardsDeck.removeFirst());
        }
        logger.info("Wylosowano karty pomysłow.");
        for (InventionCard invention : GameInfo.getIdeas()) {
            logger.debug(invention);
        }
    }

    private void createAdventureCardsDecks() {
        buildingAdventureCardsDeck = new LinkedList<>();
        Arrays.asList(BuildingAdventureType.values()).forEach(adventureType -> buildingAdventureCardsDeck.add(
                new BuildingAdventureCard(adventureType, Mappings.getBuildingAdvntureToAdventureEventEffectMapping().get(adventureType))));
        Collections.shuffle(buildingAdventureCardsDeck);

        gatheringResourcesAdventureCardsDeck = new LinkedList<>();
        Arrays.asList(GatheringResourcesAdventureType.values()).forEach(adventureType -> gatheringResourcesAdventureCardsDeck.add(
                new GatheringResourcesAdventureCard(adventureType, Mappings.getGatheringAdventureToAdventureEventEffectMapping().get(adventureType))));
        Collections.shuffle(gatheringResourcesAdventureCardsDeck);

        explorationAdventureCardsDeck = new LinkedList<>();
        Arrays.asList(ExplorationAdventureType.values()).forEach(adventureType -> explorationAdventureCardsDeck.add(
                new ExplorationAdventureCard(adventureType, Mappings.getExplorationAdventureToAdventureEventEffectMapping().get(adventureType))));
        Collections.shuffle(explorationAdventureCardsDeck);
        logger.info("Przygotowano talie przygód.");
    }

    private void createHountingCardsDeck() {
        beastCardsDeck = new LinkedList<>();
        Arrays.asList(BeastType.values()).forEach(beastType -> beastCardsDeck.add(new BeastCard(
                beastType, Mappings.getBeastToBeastStatsMapping().get(beastType))));
        Collections.shuffle(beastCardsDeck);
        logger.info("Przygotowano talię bestii.");
    }

    private void createMysteryCardsDeck() {
        mysteryCardsDeck = new LinkedList<>();
        Arrays.asList(MysteryTreasureType.values()).forEach(mysteryType -> mysteryCardsDeck.add(new MysteryTreasureCard(mysteryType)));
        Arrays.asList(MysteryMonsterType.values()).forEach(mysteryType -> mysteryCardsDeck.add(new MysteryMonsterCard(mysteryType)));
        Arrays.asList(MysteryTrapType.values()).forEach(mysteryType -> mysteryCardsDeck.add(new MysteryTrapCard(mysteryType)));
        Collections.shuffle(mysteryCardsDeck);
        logger.info("Przygotowano talię tajemnic.");
    }

    private void createDiscoveryTokensStack() {
        discoveryTokensStack = new LinkedList<>();
        Arrays.asList(DiscoveryTokenType.values()).forEach(discoveryToken -> discoveryTokensStack.add(
                new DiscoveryToken(discoveryToken)));
        Collections.shuffle(discoveryTokensStack);
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
        for (Usable card : GameInfo.getStartingItems()) {
            logger.debug(card);
        }
    }

    private void createIslandTilesStack() {
        islandTilesStack = new LinkedList<>();
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
                islandTilesStack.add(islandTile);
            } else {
                GameInfo.getDiscoveredTiles().add(islandTile);
                board.getTilePositionIdToIslandTile().put(1, islandTile);
                GameInfo.setCamp(islandTile);
            }
        }

        Collections.shuffle(islandTilesStack);

        logger.info("Przygotowano stos kafelków wyspy.");
    }

    private void createActions() {
        actionList = new ArrayList<>();
        actionList.add(new ThreadAction());
        actionList.add(new HuntingAction(new ArrayList<>(Arrays.asList(DOG_MARKER, HUNTING_HELPER_MARKER))));
        actionList.add(new BuildingAction(new ArrayList<>(Collections.singletonList(BUILDING_HELPER_MARKER))));
        actionList.add(new GatheringResourcesAction(new ArrayList<>(Collections.singletonList(GATHERING_RESOURCES_HELPER_MARKER))));
        actionList.add(new ExplorationAction(new ArrayList<>(Arrays.asList(DOG_MARKER, EXPLORATION_HELPER_MARKER))));
        actionList.add(new CampOrderingAction());
        actionList.add(new RestAction());
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
        EventCard card = eventStackDeck.removeFirst();
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
//        przygotowanie

//        actionList.forEach(action -> action.runAction());
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
            GameInfo.changeWoodLevel(-snowCludsNumber.get());
        }

        int cloudsSum = rainCloudsNumber.get() + snowCludsNumber.get();
        int missingRoofLevel = cloudsSum - GameInfo.getRoofLevel();
        if (missingRoofLevel > 0) {
            logger.debug("--->Liczba wszystkich chmur: " + cloudsSum + ". Za każdy brakujący poziom dachu należy odrzucić 1 drewno i 1 jedzenie.");
            GameInfo.changeWoodLevel(-missingRoofLevel);
            GameInfo.changeFoodLevel(-missingRoofLevel, null);
        }

        if (beastAttack.get()) {
            logger.info("--->Atak bestii o sile 3!");
        } else if (palisadeDamage.get()) {
            logger.info("--->Poziom palisady spada o 1!");
            GameInfo.changePalisadeLevel(-1);
        } else if (foodDiscard.get()) {
            logger.info("--->Należy odrzucić 1 jedzenie!");
            GameInfo.changeFoodLevel(-1, null);
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
        GameInfo.changeFoodLevel(-requiredFood, chooseStarvingCharacters(requiredFood - allFoodAmount));

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

    private List<ProfessionType> chooseStarvingCharacters(int value) {
        List<ProfessionType> starvingProfessions = null;
        if (value > 1) {
            starvingProfessions = new ArrayList<>();
            List<ProfessionType> notChosenProfessions = new ArrayList<>();
            GameInfo.getCharacters().forEach(character -> {
                if (character instanceof Character) {
                    notChosenProfessions.add(((Character) character).getProfession());
                }
            });

            for (int i = 0; i < value; i++) {
                String result = gameWindowController.showStarvingCharactersAlert(notChosenProfessions);
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
