package controller;

import model.*;
import model.Character;
import model.cards.*;
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
import model.enums.cards.wreckagecards.WreckageEventEffectType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

class GameEngineController {
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
        prepareStartingItems(startingItemsNumber);
    }

    private void prepareStartingItems(int startingItemsNumber) {
        //        karty przedmiotów startowych
        Stack startingItemStack = new Stack();
        Arrays.asList(StartingItemType.values()).forEach(startingItemType -> startingItemStack.getStack().add(new StartingItemCard(startingItemType)));
        Collections.shuffle(startingItemStack.getStack());

        gameInfo.setStartingItems(new ArrayList<>());
        for (int i = 0; i < startingItemsNumber; i++) {
            gameInfo.getStartingItems().add((StartingItemCard) startingItemStack.getStack().removeFirst());
        }
        logger.info("Wylosowano przedmioty startowe: ");
        for (Usable card : gameInfo.getStartingItems()) {
            logger.info(card);
        }
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

    private void createHountingCardsDeck() {
        //        karty bestii
        hountingStack = new Stack();
        Arrays.asList(BeastType.values()).forEach(beastType -> hountingStack.getStack().add(new BeastCard(
                beastType, Mappings.getBeastToBeastStatsMapping().get(beastType))));
        Collections.shuffle(hountingStack.getStack());
        logger.info("Przygotowano talię bestii");
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

    private void createInventionCardsDeck() {
        //        karty pomysłów
        inventionStack = new Stack();
        Arrays.asList(InventionType.values()).forEach(inventionType -> inventionStack.getStack().add(
                new InventionCard(inventionType, Mappings.getInventionTypeToIsMandatoryMapping().get(inventionType))));

//        do poprawy!!
        for (int i = 0; i < 13; i++) {
            InventionCard card = (InventionCard) inventionStack.getStack().removeFirst();
            if (i < 9) {
                gameInfo.getIdeas().add(card);
            } else {
                for (Map.Entry entry : Mappings.getProfessionToPersonalInventionMapping().entrySet()) {
                    if (card.getInvention().equals(entry.getValue())) {
                        for (Character character : gameInfo.getCharacters()) {
                            if (character.getProfession().equals(entry.getKey())) {
                                gameInfo.getIdeas().add(card);
                            }
                        }
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
            logger.info(invention);
        }


        logger.info("Pozostałe:");
        for (Usable card : inventionStack.getStack()) {
            InventionCard invention = (InventionCard) card;
            logger.info(invention);
        }
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
            logger.info(usable.toString());
        }
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

    private void createDiscoveryTokensStack() {
//        tworzenie stosu żetonów odkryć
        discoveryTokensStack = new Stack();
        Arrays.asList(DiscoveryTokenType.values()).forEach(discoveryToken -> discoveryTokensStack.getStack().add(
                new DiscoveryToken(discoveryToken)));
        Collections.shuffle(discoveryTokensStack.getStack());
        logger.info("Utworzono stos żetonów odkryć");
    }
}
