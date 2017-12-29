package controller;

import model.Character;
import model.CharactersInfo;
import model.Mappings;
import model.Scenario;
import model.cards.*;
import model.enums.PhaseType;
import model.enums.ProfessionType;
import model.enums.SexType;
import model.enums.cards.BeastType;
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
    private CharactersInfo charactersInfo;
    private boolean isFriday;
    private boolean isDog;
    private Mappings mappings;
    private CardDeck inventionCardsDeck;
    private CardDeck eventCardsDeck;
    private CardDeck buildingAdventuresCardsDeck;
    private CardDeck gatheringResourcesAdventureCardsDeck;
    private CardDeck explorationAdventuresCardsDeck;
    private CardDeck hountingCardsDeck;
    private CardDeck mysteryCardsDeck;
    private PhaseType phase;


    public GameEngineController(int scenarioId,
                                Map<ProfessionType, SexType> choosedCharacters,
                                boolean isFriday,
                                boolean isDog,
                                WreckageEventEffectType wreckageEvent,
                                int startingItemsNumber) {

        mappings = new Mappings();

//        tworzenie scenariusza
        scenario = new Scenario(scenarioId, Mappings.getScenarioIdToRoundsNumberMapping().get(scenarioId));
        logger.info("Utworzono scenariusz");

//        tworzenie postaci
        charactersInfo = new CharactersInfo();
        for (Map.Entry<ProfessionType, SexType> entry : choosedCharacters.entrySet()) {
            ProfessionType profession = entry.getKey();
            SexType sex = entry.getValue();
            charactersInfo.getCharacters().add(new Character(
                    profession,
                    sex,
                    Mappings.getProfessionToPersonalInventionMapping().get(profession),
                    Mappings.getProfessionToSpecialSkillMapping().get(profession),
                    Mappings.getProfessionToMoraleDownMapping().get(profession),
                    Mappings.getProfessionToLifeMapping().get(profession)));
        }
        this.isFriday = isFriday;
        this.isDog = isDog;

//        todo
//        stworzyć piętaszka i psa
        logger.info("Utworzono postacie");

//        karty wydarzeń
        CardDeck allEventsCardsDeck = new CardDeck();
        Arrays.asList(EventEffectType.values()).forEach(eventEffect -> allEventsCardsDeck.getDeck().add(new EventCard(
                eventEffect,
                Mappings.getEventEffectToEventIconMapping().get(eventEffect),
                Mappings.getEventEffectToThreatActionMapping().get(eventEffect),
                Mappings.getEventEffectToThreatEffectMapping().get(eventEffect))));
        Collections.shuffle(allEventsCardsDeck.getDeck());

        eventCardsDeck = new CardDeck();

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

//        karty pomysłów
        inventionCardsDeck = new CardDeck();
        Arrays.asList(InventionType.values()).forEach(inventionType -> inventionCardsDeck.getDeck().add(
                new InventionCard(inventionType, Mappings.getInventionTypeToIsMandatoryMapping().get(inventionType))));

        for (int i = 0; i < 13; i++) {
            InventionCard card = (InventionCard) inventionCardsDeck.getDeck().removeFirst();
            if (i < 9) {
                charactersInfo.getIdeas().add(card);
            } else {
                for (Map.Entry entry : Mappings.getProfessionToPersonalInventionMapping().entrySet()) {
                    if (card.getInvention().equals(entry.getValue())) {
                        for (Character character : charactersInfo.getCharacters()) {
                            if (character.getProfession().equals(entry.getKey())) {
                                charactersInfo.getIdeas().add(card);
                            }
                        }
                    }
                }
            }
        }

        Collections.shuffle(inventionCardsDeck.getDeck());
        for (int i = 0; i < 5; i++) {
            charactersInfo.getIdeas().add((InventionCard) inventionCardsDeck.getDeck().removeFirst());
        }
        logger.info("Wylosowano karty pomysłow");
        for (InventionCard invention : charactersInfo.getIdeas()) {
            logger.info(invention);
        }


        logger.info("Pozostałe:");
        for (ICard card : inventionCardsDeck.getDeck()) {
            InventionCard invention = (InventionCard) card;
            logger.info(invention);
        }

//        karty przygód
        buildingAdventuresCardsDeck = new CardDeck();
        Arrays.asList(BuildingAdventureType.values()).forEach(adventureType -> buildingAdventuresCardsDeck.getDeck().add(
                new BuildingAdventureCard(adventureType, Mappings.getBuildingAdvntureToAdventureEventEffectMapping().get(adventureType))));
        Collections.shuffle(buildingAdventuresCardsDeck.getDeck());

        gatheringResourcesAdventureCardsDeck = new CardDeck();
        Arrays.asList(GatheringResourcesAdventureType.values()).forEach(adventureType -> gatheringResourcesAdventureCardsDeck.getDeck().add(
                new GatheringResourcesAdventureCard(adventureType, Mappings.getGatheringAdventureToAdventureEventEffectMapping().get(adventureType))));
        Collections.shuffle(gatheringResourcesAdventureCardsDeck.getDeck());

        explorationAdventuresCardsDeck = new CardDeck();
        Arrays.asList(ExplorationAdventureType.values()).forEach(adventureType -> explorationAdventuresCardsDeck.getDeck().add(
                new ExplorationAdventureCard(adventureType, Mappings.getExplorationAdventureToAdventureEventEffectMapping().get(adventureType))));
        Collections.shuffle(explorationAdventuresCardsDeck.getDeck());
        logger.info("Przygotowano talie przygód");

//        karty bestii
        hountingCardsDeck = new CardDeck();
        Arrays.asList(BeastType.values()).forEach(beastType -> hountingCardsDeck.getDeck().add(new BeastCard(
                beastType, Mappings.getBeastToBeastStatsMapping().get(beastType))));
        Collections.shuffle(hountingCardsDeck.getDeck());
        logger.info("Przygotowano talię bestii");

//        karty tajemnic
        mysteryCardsDeck = new CardDeck();
        Arrays.asList(MysteryTreasureType.values()).forEach(mysteryType -> mysteryCardsDeck.getDeck().add(new MysteryTreasureCard(mysteryType)));
        Arrays.asList(MysteryMonsterType.values()).forEach(mysteryType -> mysteryCardsDeck.getDeck().add(new MysteryMonsterCard(mysteryType)));
        Arrays.asList(MysteryTrapType.values()).forEach(mysteryType -> mysteryCardsDeck.getDeck().add(new MysteryTrapCard(mysteryType)));
        Collections.shuffle(mysteryCardsDeck.getDeck());
        logger.info("Przygotowano talię tajemnic");

//        karty przedmiotów startowych
        CardDeck startingItemCardsDeck = new CardDeck();
        Arrays.asList(StartingItemType.values()).forEach(startingItemType -> startingItemCardsDeck.getDeck().add(new StartingItemCard(startingItemType)));
        Collections.shuffle(startingItemCardsDeck.getDeck());

        charactersInfo.setStartingItems(new ArrayList<>());
        for (int i = 0; i < startingItemsNumber; i++) {
            charactersInfo.getStartingItems().add((StartingItemCard) startingItemCardsDeck.getDeck().removeFirst());
        }
        logger.info("Wylosowano przedmioty startowe: ");
        for (ICard card : charactersInfo.getStartingItems()) {
            logger.info(card);
        }
    }
}
