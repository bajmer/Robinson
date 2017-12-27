package controller;

import model.Character;
import model.CharactersInfo;
import model.Mappings;
import model.Scenario;
import model.cards.*;
import model.enums.PhaseType;
import model.enums.ProfessionType;
import model.enums.SexType;
import model.enums.cards.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
    private CardDeck startingItemCardsDeck;
    private PhaseType phase;


    public GameEngineController(int scenarioId,
                                Map<ProfessionType, SexType> choosedCharacters,
                                boolean isFriday,
                                boolean isDog,
                                int wreckageCardId,
                                int startingItemsNumber) {

        mappings = new Mappings();

//        tworzenie scenariusza
        this.scenario = new Scenario(scenarioId, Mappings.getScenarioIdToRoundsNumberMapping().get(scenarioId));
        logger.info("Utworzono scenariusz");

//        tworzenie postaci
        this.charactersInfo = new CharactersInfo();
        for (Map.Entry<ProfessionType, SexType> entry : choosedCharacters.entrySet()) {
            this.charactersInfo.getCharacters().add(new Character(entry.getKey(), entry.getValue()));
        }
        this.isFriday = isFriday;
        this.isDog = isDog;

//        todo
//        stworzyć piętaszka i psa
        logger.info("Utworzono postacie");

//        karty wydarzeń
        List<ICard> allEventsCards = new ArrayList<>();
        for (int i = EventCard.getFirstId(); i < EventCard.getLastId(); i++) {
            allEventsCards.add(new EventCard(
                    Mappings.getIdToEventEffectMapping().get(i),
                    Mappings.getIdToEventIconMapping().get(i),
                    Mappings.getIdToThreatActionMapping().get(i),
                    Mappings.getIdToThreatEffectMapping().get(i)));
        }
        CardDeck allEventsCardsDeck = new CardDeck(allEventsCards);
        allEventsCardsDeck.shuffle();

        eventCardsDeck = new CardDeck(new ArrayList<>());
        int bookIconsCounter = 0;
        int questionmarkIconsCouter = 0;
        for (ICard card : eventCardsDeck.getDeck()) {
            EventCard eventCard = (EventCard) card;
            if (eventCard.getEventIcon() == EventIconType.BOOK && bookIconsCounter < scenario.getRoundsNumber() / 2) {
                eventCardsDeck.getDeck().add(eventCardsDeck.);
                bookIconsCounter++;
                continue;
            }
            if ((eventCard.getEventIcon() == EventIconType.BROWN_QUESTIONMARK ||
                    eventCard.getEventIcon() == EventIconType.GRAY_QUESTIONMARK ||
                    eventCard.getEventIcon() == EventIconType.GREEN_QUESTIONMARK) &&
                    questionmarkIconsCouter < scenario.getRoundsNumber() / 2) {
                eventCardsDeck.putCartOnTop(eventCard);
                bookIconsCounter++;
            }
        }

        logger.info("Przygotowano talię wydarzeń");
        for (ICard iCard : eventCardsDeck.getDeck()) {
            System.out.println(iCard);
        }


//        karty pomysłów
        List<ICard> inventionCards = new ArrayList<>();
        Arrays.asList(InventionType.values()).forEach(inventionType -> inventionCards.add(new InventionCard(inventionType)));
        this.inventionCardsDeck = new CardDeck(inventionCards);

        for (ICard card : inventionCardsDeck.getDeck()) {
            InventionCard inventionCard = (InventionCard) card;
            if (inventionCard.isMandatory()) {
                charactersInfo.getIdeas().add(inventionCard);
            }
        }
        this.inventionCardsDeck.shuffle();
        for (int i = 0; i < 5; i++) {
            charactersInfo.getIdeas().add((InventionCard) inventionCardsDeck.getCardFromTop());
        }
        logger.info("Wylosowano karty pomysłow");

////        karty przygód
//        List<ICard> buildingAdventureCards = new ArrayList<>();
//        Arrays.asList(BuildingAdventureType.values()).forEach((adventureType, effectType) -> buildingAdventureCards.add(new BuildingAdventureCard(adventureType, effectType)));
//        this.buildingAdventuresCardsDeck = new CardDeck(buildingAdventureCards);
//        this.buildingAdventuresCardsDeck.shuffle();
//
//        List<ICard> gatheringResourceAdventureCards = new ArrayList<>();
//        Arrays.asList(GatheringResourcesAdventureType.values()).forEach(adventureType -> gatheringResourceAdventureCards.add(new GatheringResourcesAdventureCard(adventureType, effectType)));
//        this.gatheringResourcesAdventureCardsDeck = new CardDeck(gatheringResourceAdventureCards);
//        this.gatheringResourcesAdventureCardsDeck.shuffle();
//
//        List<ICard> explorationAdventureCards = new ArrayList<>();
//        Arrays.asList(ExplorationAdventureType.values()).forEach(adventureType -> explorationAdventureCards.add(new ExplorationAdventureCard(adventureType, effectType)));
//        this.explorationAdventuresCardsDeck = new CardDeck(explorationAdventureCards);
//        this.explorationAdventuresCardsDeck.shuffle();
//        logger.info("Przygotowano talie przygód");
//
////        karty wydarzeń
//        List<ICard> eventCards = new ArrayList<>();
//        Arrays.asList(EventEffectType.values()).forEach(eventEffectType -> eventCards.add(new EventCard(eventEffectType, group)));
//        this.eventCardsDeck = new CardDeck(eventCards);
//        logger.info("Przygotowano talię wydarzeń");

//        karty bestii
        List<ICard> beastCards = new ArrayList<>();
        Arrays.asList(BeastType.values()).forEach(beastType -> beastCards.add(new BeastCard(
                beastType,
                Mappings.getBeastToBeastStatsMapping().get(beastType).get(0),
                Mappings.getBeastToBeastStatsMapping().get(beastType).get(1),
                Mappings.getBeastToBeastStatsMapping().get(beastType).get(2),
                Mappings.getBeastToBeastStatsMapping().get(beastType).get(3))));
        this.hountingCardsDeck = new CardDeck(beastCards);
        this.hountingCardsDeck.shuffle();
        logger.info("Przygotowano talię bestii");

//        karty tajemnic
        List<ICard> mysteryCards = new ArrayList<>();
        Arrays.asList(MysteryTreasureType.values()).forEach(mysteryType -> mysteryCards.add(new MysteryTreasureCard(mysteryType)));
        Arrays.asList(MysteryMonsterType.values()).forEach(mysteryType -> mysteryCards.add(new MysteryMonsterCard(mysteryType)));
        Arrays.asList(MysteryTrapType.values()).forEach(mysteryType -> mysteryCards.add(new MysteryTrapCard(mysteryType)));
        this.mysteryCardsDeck = new CardDeck(mysteryCards);
        this.mysteryCardsDeck.shuffle();
        logger.info("Przygotowano talię tajemnic");

//        karty przedmiotów startowych
        List<ICard> startingItemCards = new ArrayList<>();
        Arrays.asList(StartingItemType.values()).forEach(startingItemType -> startingItemCards.add(new StartingItemCard(startingItemType)));
        this.startingItemCardsDeck = new CardDeck(startingItemCards);
        this.startingItemCardsDeck.shuffle();

        this.charactersInfo.setStartingItems(new ArrayList<>());
        for (int i = 0; i < startingItemsNumber; i++) {
            this.charactersInfo.getStartingItems().add((StartingItemCard) this.startingItemCardsDeck.getCardFromTop());
        }
        logger.info("Wylosowano przedmioty startowe: ");
        for (ICard card : this.charactersInfo.getStartingItems()) {
            logger.info(card);
        }
    }
}
