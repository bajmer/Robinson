package controller;

import model.Character;
import model.ChoosedCharacter;
import model.GameInfo;
import model.Scenario;
import model.cards.*;
import model.enums.PhaseType;
import model.enums.cards.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GameEngineController {
    private final GameInfo gameInfo = new GameInfo();
    private Logger logger = LogManager.getLogger(GameEngineController.class);
    private Scenario scenario;
    private List<Character> characters;
    private boolean isFriday;
    private boolean isDog;
    private int startingItemsNumber = 2; //przerobić na parametr konstruktora
    private CardDeck inventionCardsDeck;
    private CardDeck eventCardsDeck;
    private CardDeck buildingAdventuresCardsDeck;
    private CardDeck gatheringResourcesAdventureCardsDeck;
    private CardDeck explorationAdventuresCardsDeck;
    private CardDeck hountingCardsDeck;
    private CardDeck mysteryCardsDeck;
    private CardDeck startingItemCardsDeck;
    private PhaseType phase;


    public GameEngineController(int scenarioId, List<ChoosedCharacter> choosedCharacters, boolean isFriday, boolean isDog) {

//        tworzenie scenariusza i postaci
        this.scenario = new Scenario(scenarioId);
        this.characters = new ArrayList<>();
        for (ChoosedCharacter info : choosedCharacters) {
            this.characters.add(new Character(info.getProfession(), info.getSex()));
        }
        this.isFriday = isFriday;
        this.isDog = isDog;

        logger.info("Utworzono scenariusz i postacie");

//        tworzenie talii kart
        this.gameInfo.setIdeas(new ArrayList<>());
        this.gameInfo.setInventions(new ArrayList<>());

//        talia pomysłów
        List<ICard> inventionCards = new ArrayList<>();
        Arrays.asList(InventionType.values()).forEach(inventionType -> inventionCards.add(new InventionCard(inventionType)));
        this.inventionCardsDeck = new CardDeck(inventionCards);

        for (ICard card : inventionCardsDeck.getDeck()) {
            InventionCard inventionCard = (InventionCard) card;
            if (inventionCard.isMandatory()) {
                gameInfo.getIdeas().add(inventionCard);
            }
        }
        inventionCardsDeck.shuffle();
        for (int i = 0; i < 5; i++) {
            gameInfo.getIdeas().add((InventionCard) inventionCardsDeck.getCardFromTop());
        }
        logger.info("Wylosowano karty pomysłow");

//        karty przygód
        List<ICard> buildingAdventureCards = new ArrayList<>();
        Arrays.asList(BuildingAdventureType.values()).forEach(adventureType -> buildingAdventureCards.add(new BuildingAdventureCard(adventureType)));
        this.buildingAdventuresCardsDeck = new CardDeck(buildingAdventureCards);
        this.buildingAdventuresCardsDeck.shuffle();

        List<ICard> gatheringResourceAdventureCards = new ArrayList<>();
        Arrays.asList(GatheringResourcesAdventureType.values()).forEach(adventureType -> gatheringResourceAdventureCards.add(new GatheringResourcesAdventureCard(adventureType)));
        this.gatheringResourcesAdventureCardsDeck = new CardDeck(gatheringResourceAdventureCards);
        this.gatheringResourcesAdventureCardsDeck.shuffle();

        List<ICard> explorationAdventureCards = new ArrayList<>();
        Arrays.asList(ExplorationAdventureType.values()).forEach(adventureType -> explorationAdventureCards.add(new ExplorationAdventureCard(adventureType)));
        this.explorationAdventuresCardsDeck = new CardDeck(explorationAdventureCards);
        this.explorationAdventuresCardsDeck.shuffle();
        logger.info("Przygotowano talie przygód");

//        karty wydarzeń
        List<ICard> eventCards = new ArrayList<>();
        Arrays.asList(EventType.values()).forEach(eventType -> eventCards.add(new EventCard(eventType)));
        this.eventCardsDeck = new CardDeck(eventCards);
        logger.info("Przygotowano talię wydarzeń");

//        karty bestii
        List<ICard> beastCards = new ArrayList<>();
        Arrays.asList(BeastType.values()).forEach(beastType -> beastCards.add(new BeastCard(beastType)));
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

        this.gameInfo.setStartingItems(new ArrayList<>());
        for (int i = 0; i < startingItemsNumber; i++) {
            this.gameInfo.getStartingItems().add((StartingItemCard) this.startingItemCardsDeck.getCardFromTop());
        }
        logger.info("Wylosowano przedmioty startowe: ");
        for (ICard card : this.gameInfo.getStartingItems()) {
            logger.info(card);
        }
    }
}
