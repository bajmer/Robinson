package controller;

import model.*;
import model.Character;
import model.cards.*;
import model.enums.PhaseType;
import model.enums.ProfessionType;
import model.enums.ResourceType;
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
import model.enums.cards.wreckagecards.WreckageEventEffectType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class GameEngineController {
    private Logger logger = LogManager.getLogger(GameEngineController.class);
    private Scenario scenario;
    private GameInfo gameInfo;
    private boolean isFriday;
    private boolean isDog;
    private Mappings mappings;
    private Stack inventionStack;
    private Stack eventStack;
    private Stack buildingAdventuresStack;
    private Stack gatheringResourcesAdventureStack;
    private Stack explorationAdventuresStack;
    private Stack hountingStack;
    private Stack mysteryStack;
    private Stack islandTilesStack;
    private Stack discoveryTokensStack;
    private Board board;
    private PhaseType phase;

    public GameEngineController(int scenarioId,
                                Map<ProfessionType, SexType> choosedCharacters,
                                boolean isFriday,
                                boolean isDog,
                                WreckageEventEffectType wreckageEvent,
                                int startingItemsNumber) {

        this.isFriday = isFriday;
        this.isDog = isDog;
//        todo
//        stworzyć piętaszka i psa

        mappings = new Mappings();
        gameInfo = new GameInfo();
        board = new Board();

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
    }

    private void createScenario(int scenarioId) {
        //        tworzenie scenariusza
        scenario = new Scenario(scenarioId, Mappings.getScenarioIdToRoundsNumberMapping().get(scenarioId));
        logger.info("Utworzono scenariusz");
    }

    private void createCharacters(Map<ProfessionType, SexType> choosedCharacters) {
        //        tworzenie postaci
        for (Map.Entry<ProfessionType, SexType> entry : choosedCharacters.entrySet()) {
            ProfessionType profession = entry.getKey();
            SexType sex = entry.getValue();
            gameInfo.getCharacters().add(new Character(
                    profession,
                    sex,
                    Mappings.getProfessionToPersonalInventionMapping().get(profession),
                    Mappings.getProfessionToSpecialSkillMapping().get(profession),
                    Mappings.getProfessionToMoraleDownMapping().get(profession),
                    Mappings.getProfessionToLifeMapping().get(profession)));
        }
        logger.info("Utworzono postacie");
    }

    private void createEventCardsDeck(WreckageEventEffectType wreckageEvent) {
        //        karty wydarzeń
        Stack allEventsStack = new Stack();
        Arrays.asList(EventEffectType.values()).forEach(eventEffect -> allEventsStack.getStack().add(new EventCard(
                eventEffect,
                Mappings.getEventEffectToEventIconMapping().get(eventEffect),
                Mappings.getEventEffectToThreatActionMapping().get(eventEffect),
                Mappings.getEventEffectToThreatEffectMapping().get(eventEffect))));
        Collections.shuffle(allEventsStack.getStack());

        eventStack = new Stack();

        eventStack.getStack().add(new WreckageCard(
                wreckageEvent,
                Mappings.getWreckageEventToWreckageThreatActionMapping().get(wreckageEvent),
                Mappings.getWreckageEventToWreckageThreatEffectMapping().get(wreckageEvent)));

        int bookIconsCounter = 0;
        int questionmarkIconsCouter = 0;

        for (Usable card : allEventsStack.getStack()) {
            EventCard eventCard = (EventCard) card;
            if (eventCard.getEventIcon() == EventIconType.BOOK && bookIconsCounter < scenario.getRoundsNumber() / 2) {
                eventStack.getStack().add(eventCard);
                bookIconsCounter++;
                continue;
            }
            if ((eventCard.getEventIcon() == EventIconType.BUILDING_ADVENTURE ||
                    eventCard.getEventIcon() == EventIconType.GATHERING_RESOURCES_ADVENTURE ||
                    eventCard.getEventIcon() == EventIconType.EXPLORATION_ADVENTURE) &&
                    questionmarkIconsCouter < scenario.getRoundsNumber() / 2) {
                eventStack.getStack().add(eventCard);
                questionmarkIconsCouter++;
            }
        }

        logger.info("Przygotowano talię wydarzeń");
        for (Usable usable : eventStack.getStack()) {
            logger.debug(usable.toString());
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
                    for (Character character : gameInfo.getCharacters()) {
                        if (character.getProfession() == owner) {
                            characterEqualsOwner = true;
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
            }
        }

        Collections.shuffle(islandTilesStack.getStack());

        logger.info("Przygotowano stos kafelków wyspy");
    }

    public void nextPhase() {
        phase = Mappings.getCurrentPhaseToNextPhaseMapping().get(phase);
        if (phase == PhaseType.EVENT_PHASE) {
            updateGameParams();
        }

        logger.info("Obecna faza: " + phase);
        runPhase();
    }

    private void updateFirstPlayer() {
        int firstPlayerId = (scenario.getRound() - 1) % gameInfo.getCharacters().size();
        gameInfo.setFirstPlayer(gameInfo.getCharacters().get(firstPlayerId));
    }

    private void runPhase() {
        switch (phase) {
            case EVENT_PHASE:
                handleEventPhase();
                break;
            case MORALE_PHASE:
                handleMoralePhase();
                break;
            case PRODUCTION_PHASE:
                handleProductionPhase();
                break;
            case ACTION_PHASE:
                handleActionPhase();
                break;
            case WEATHER_PHASE:
                handleWeatherPhase();
                break;
            case NIGHT_PHASE:
                handleNightPhase();
                break;
        }
    }

    private void handleEventPhase() {
        Usable card = eventStack.getStack().removeFirst();
        card.use();
//        logger.info(card.getEventEffect());
    }

    private void handleMoralePhase() {
        int morale = gameInfo.getMoraleLevel();
        Character firstPlayer = gameInfo.getFirstPlayer();
        int determination = firstPlayer.getDetermination();
        int life = firstPlayer.getLife();

        if (determination + morale < 0) {
            firstPlayer.setLife(life + determination + morale);
            firstPlayer.setDetermination(0);
        }

        if (firstPlayer.getLife() <= 0) {
            handleGameEnd();
        }
    }

    private void handleProductionPhase() {
        IslandTile camp = gameInfo.getCamp();
        int tmpWood = gameInfo.getAvaibleResources().getWoodAmount();
        int woodProduction = gameInfo.getProductionWoodNumber();
        int tmpFood = gameInfo.getAvaibleResources().getFoodAmount();
        int foodProduction = gameInfo.getProductionFoodNumber();

        if (camp.getLeftSquareResource() == ResourceType.WOOD || camp.getRightSquareResource() == ResourceType.WOOD) {
            gameInfo.getAvaibleResources().setWoodAmount(tmpWood + woodProduction);
        }
        if (camp.getLeftSquareResource() == ResourceType.FOOD || camp.getRightSquareResource() == ResourceType.FOOD) {
            gameInfo.getAvaibleResources().setFoodAmount(tmpFood + foodProduction);
        }
    }

    private void handleActionPhase() {

    }

    private void handleWeatherPhase() {

    }

    private void handleNightPhase() {
        int requiredFood = gameInfo.getCharacters().size();
        int foodAmount = gameInfo.getAvaibleResources().getFoodAmount();
        int longExpiryDateFoodAmount = gameInfo.getAvaibleResources().getLongExpiryDateFoodsAmount();
        int allFoodAmount = foodAmount + longExpiryDateFoodAmount;

        if (allFoodAmount < requiredFood) {
            //wybór postaci, która nie zje
        } else {
            gameInfo.getAvaibleResources().setFoodAmount(foodAmount - requiredFood);
            int foodAmountAfterEat = gameInfo.getAvaibleResources().getFoodAmount();
            if (foodAmountAfterEat < 0) {
                gameInfo.getAvaibleResources().setLongExpiryDateFoodsAmount(longExpiryDateFoodAmount + foodAmountAfterEat);
                gameInfo.getAvaibleResources().setFoodAmount(0);
            }
        }

        //opcja przeniesienia obozu

        IslandTile camp = gameInfo.getCamp();
        if (!gameInfo.isShelter() && !camp.isHasNaturalShelter()) {
            gameInfo.getCharacters().forEach(character -> character.setLife(character.getLife() - 1));
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
            gameInfo.getAvaibleResources().setFoodAmount(0);
        }
    }

    private void handleGameEnd() {
        logger.info("Koniec gry!");
    }

    private void updateGameParams() {
        boolean isGameEnd = scenario.nextRound();
        if (isGameEnd) {
            handleGameEnd();
        }
        logger.info("***********************************************************");
        logger.info("Runda numer: " + scenario.getRound());

        updateFirstPlayer();
        logger.info("Pierwszy gracz: " + gameInfo.getFirstPlayer().getProfession());
    }


}
