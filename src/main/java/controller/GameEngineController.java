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
                                int wreckageCardId,
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
        for (int i = EventCard.getFirstId(); i <= EventCard.getLastId(); i++) {
            allEventsCardsDeck.getDeck().add(new EventCard(
                    Mappings.getIdToEventEffectMapping().get(i),
                    Mappings.getIdToEventIconMapping().get(i),
                    Mappings.getIdToThreatActionMapping().get(i),
                    Mappings.getIdToThreatEffectMapping().get(i)));
        }
        Collections.shuffle(allEventsCardsDeck.getDeck());

        eventCardsDeck = new CardDeck();
        int bookIconsCounter = 0;
        int questionmarkIconsCouter = 0;

        for (ICard card : allEventsCardsDeck.getDeck()) {
            EventCard eventCard = (EventCard) card;
            if (eventCard.getEventIcon() == EventIconType.BOOK && bookIconsCounter < scenario.getRoundsNumber() / 2) {
                eventCardsDeck.getDeck().add(eventCard);
                bookIconsCounter++;
                continue;
            }
            if ((eventCard.getEventIcon() == EventIconType.BROWN_QUESTIONMARK ||
                    eventCard.getEventIcon() == EventIconType.GRAY_QUESTIONMARK ||
                    eventCard.getEventIcon() == EventIconType.GREEN_QUESTIONMARK) &&
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
//        buildingAdventuresCardsDeck = new CardDeck();
//        Arrays.asList(BuildingAdventureType.values()).forEach((adventureType, effectType) -> buildingAdventuresCardsDeck.getDeck().add(new BuildingAdventureCard(adventureType, effectType)));
//        buildingAdventuresCardsDeck.getDeck();
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
