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
    private CardsDeck inventionCardsDeck;
    private CardsDeck eventCardsDeck;
    private CardsDeck buildingAdventuresCardsDeck;
    private CardsDeck gatheringResourcesAdventureCardsDeck;
    private CardsDeck explorationAdventuresCardsDeck;
    private CardsDeck hountingCardsDeck;
    private CardsDeck mysteryCardsDeck;
    private CardsDeck islandTilesStack;
    private CardsDeck discoveryTokensStack;
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
        CardsDeck startingItemCardsDeck = new CardsDeck();
        Arrays.asList(StartingItemType.values()).forEach(startingItemType -> startingItemCardsDeck.getDeck().add(new StartingItemCard(startingItemType)));
        Collections.shuffle(startingItemCardsDeck.getDeck());

        gameInfo.setStartingItems(new ArrayList<>());
        for (int i = 0; i < startingItemsNumber; i++) {
            gameInfo.getStartingItems().add((StartingItemCard) startingItemCardsDeck.getDeck().removeFirst());
        }
        logger.info("Wylosowano przedmioty startowe: ");
        for (ICard card : gameInfo.getStartingItems()) {
            logger.info(card);
        }
    }

    private void createMysteryCardsDeck() {
        //        karty tajemnic
        mysteryCardsDeck = new CardsDeck();
        Arrays.asList(MysteryTreasureType.values()).forEach(mysteryType -> mysteryCardsDeck.getDeck().add(new MysteryTreasureCard(mysteryType)));
        Arrays.asList(MysteryMonsterType.values()).forEach(mysteryType -> mysteryCardsDeck.getDeck().add(new MysteryMonsterCard(mysteryType)));
        Arrays.asList(MysteryTrapType.values()).forEach(mysteryType -> mysteryCardsDeck.getDeck().add(new MysteryTrapCard(mysteryType)));
        Collections.shuffle(mysteryCardsDeck.getDeck());
        logger.info("Przygotowano talię tajemnic");
    }

    private void createHountingCardsDeck() {
        //        karty bestii
        hountingCardsDeck = new CardsDeck();
        Arrays.asList(BeastType.values()).forEach(beastType -> hountingCardsDeck.getDeck().add(new BeastCard(
                beastType, Mappings.getBeastToBeastStatsMapping().get(beastType))));
        Collections.shuffle(hountingCardsDeck.getDeck());
        logger.info("Przygotowano talię bestii");
    }

    private void createAdventureCardsDecks() {
        //        karty przygód
        buildingAdventuresCardsDeck = new CardsDeck();
        Arrays.asList(BuildingAdventureType.values()).forEach(adventureType -> buildingAdventuresCardsDeck.getDeck().add(
                new BuildingAdventureCard(adventureType, Mappings.getBuildingAdvntureToAdventureEventEffectMapping().get(adventureType))));
        Collections.shuffle(buildingAdventuresCardsDeck.getDeck());

        gatheringResourcesAdventureCardsDeck = new CardsDeck();
        Arrays.asList(GatheringResourcesAdventureType.values()).forEach(adventureType -> gatheringResourcesAdventureCardsDeck.getDeck().add(
                new GatheringResourcesAdventureCard(adventureType, Mappings.getGatheringAdventureToAdventureEventEffectMapping().get(adventureType))));
        Collections.shuffle(gatheringResourcesAdventureCardsDeck.getDeck());

        explorationAdventuresCardsDeck = new CardsDeck();
        Arrays.asList(ExplorationAdventureType.values()).forEach(adventureType -> explorationAdventuresCardsDeck.getDeck().add(
                new ExplorationAdventureCard(adventureType, Mappings.getExplorationAdventureToAdventureEventEffectMapping().get(adventureType))));
        Collections.shuffle(explorationAdventuresCardsDeck.getDeck());
        logger.info("Przygotowano talie przygód");
    }

    private void createInventionCardsDeck() {
        //        karty pomysłów
        inventionCardsDeck = new CardsDeck();
        Arrays.asList(InventionType.values()).forEach(inventionType -> inventionCardsDeck.getDeck().add(
                new InventionCard(inventionType, Mappings.getInventionTypeToIsMandatoryMapping().get(inventionType))));

//        do poprawy!!
        for (int i = 0; i < 13; i++) {
            InventionCard card = (InventionCard) inventionCardsDeck.getDeck().removeFirst();
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

        Collections.shuffle(inventionCardsDeck.getDeck());
        for (int i = 0; i < 5; i++) {
            gameInfo.getIdeas().add((InventionCard) inventionCardsDeck.getDeck().removeFirst());
        }
        logger.info("Wylosowano karty pomysłow");
        for (InventionCard invention : gameInfo.getIdeas()) {
            logger.info(invention);
        }


        logger.info("Pozostałe:");
        for (ICard card : inventionCardsDeck.getDeck()) {
            InventionCard invention = (InventionCard) card;
            logger.info(invention);
        }
    }

    private void createEventCardsDeck(WreckageEventEffectType wreckageEvent) {
        //        karty wydarzeń
        CardsDeck allEventsCardsDeck = new CardsDeck();
        Arrays.asList(EventEffectType.values()).forEach(eventEffect -> allEventsCardsDeck.getDeck().add(new EventCard(
                eventEffect,
                Mappings.getEventEffectToEventIconMapping().get(eventEffect),
                Mappings.getEventEffectToThreatActionMapping().get(eventEffect),
                Mappings.getEventEffectToThreatEffectMapping().get(eventEffect))));
        Collections.shuffle(allEventsCardsDeck.getDeck());

        eventCardsDeck = new CardsDeck();

        eventCardsDeck.getDeck().add(new WreckageCard(
                wreckageEvent,
                Mappings.getWreckageEventToWreckageThreatActionMapping().get(wreckageEvent),
                Mappings.getWreckageEventToWreckageThreatEffectMapping().get(wreckageEvent)));

        int bookIconsCounter = 0;
        int questionmarkIconsCouter = 0;

        for (ICard card : allEventsCardsDeck.getDeck()) {
            EventCard eventCard = (EventCard) card;
            if (eventCard.getEventIcon() == EventIconType.BOOK && bookIconsCounter < scenario.getRoundsNumber() / 2) {
                eventCardsDeck.getDeck().add(eventCard);
                bookIconsCounter++;
                continue;
            }
            if ((eventCard.getEventIcon() == EventIconType.BUILDING_ADVENTURE ||
                    eventCard.getEventIcon() == EventIconType.GATHERING_RESOURCES_ADVENTURE ||
                    eventCard.getEventIcon() == EventIconType.EXPLORATION_ADVENTURE) &&
                    questionmarkIconsCouter < scenario.getRoundsNumber() / 2) {
                eventCardsDeck.getDeck().add(eventCard);
                questionmarkIconsCouter++;
            }
        }

        logger.info("Przygotowano talię wydarzeń");
        for (ICard iCard : eventCardsDeck.getDeck()) {
            logger.info(iCard.toString());
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
        discoveryTokensStack = new CardsDeck();
        Arrays.asList(DiscoveryTokenType.values()).forEach(discoveryToken -> discoveryTokensStack.getDeck().add(
                new DiscoveryToken(discoveryToken)));
        Collections.shuffle(discoveryTokensStack.getDeck());
        logger.info("Utworzono stos żetonów odkryć");
    }
}
