package controller;

import model.Character;
import model.*;
import model.cards.CardDeck;
import model.cards.ICard;
import model.cards.InventionCard;
import model.enums.PhaseType;
import model.enums.cards.InventionType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class GameEngineController {
    private Logger logger = LogManager.getLogger(GameEngineController.class);
    private Scenario scenario;
    private List<Character> characters;
    private boolean isFriday;
    private boolean isDog;
    private CardDeck inventionCardsDeck;
    private CardDeck eventCardsDeck;
    private CardDeck buildingAdventuresCardsDeck;
    private CardDeck gatheringResourcesAdventureCardsDeck;
    private CardDeck explorationAdventuresCardsDeck;
    private CardDeck hountingCardsDeck;
    private CardDeck mysteryCardsDeck;
    private CardDeck startingItemCardsDeck;
    private PhaseType phase;
    private CharactersStats charactersStats;
    private Resources avaibleResources;
    private Resources futureResources;
    private List<InventionCard> ideas;
    private List<InventionCard> inventions;


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
        this.ideas = new ArrayList<>();
        this.inventions = new ArrayList<>();
        List<ICard> inventionCards = new ArrayList<>();

//        talia pomysłów
        Arrays.asList(InventionType.values()).forEach(inventionType -> inventionCards.add(new InventionCard(inventionType)));
        this.inventionCardsDeck = new CardDeck(inventionCards);

        for (ICard card : inventionCardsDeck.getDeck()) {
            InventionCard inventionCard = (InventionCard) card;
            if (inventionCard.isMandatory()) {
                ideas.add(inventionCard);
            }
        }
        inventionCardsDeck.shuffle();
        for (int i = 0; i < 5; i++) {
            ideas.add((InventionCard) inventionCardsDeck.getCardFromTop());
        }

        for (InventionCard card : ideas) {
            logger.info(card);
        }

//        Arrays.asList(BuildingAdventureType.values()).forEach(adventureType -> cards.add(new BuildingAdventureCard(adventureType)));
//        this.buildingAdventuresCardsDeck = new CardDeck(cards);
//        cards.clear();
//
//        Arrays.asList(GatheringResourcesAdventureType.values()).forEach(adventureType -> cards.add(new GatheringResourcesAdventureCard(adventureType)));
//        this.gatheringResourcesAdventureCardsDeck = new CardDeck(cards);
//        cards.clear();
//
//        Arrays.asList(ExplorationAdventureType.values()).forEach(adventureType -> cards.add(new ExplorationAdventureCard(adventureType)));
//        this.explorationAdventuresCardsDeck = new CardDeck(cards);
//        cards.clear();
//
//        Arrays.asList(EventType.values()).forEach(eventType -> cards.add(new EventCard(eventType)));
//        this.eventCardsDeck = new CardDeck(cards);
//        cards.clear();
//
//        Arrays.asList(BeastType.values()).forEach(beastType -> cards.add(new BeastCard(beastType)));
//        this.hountingCardsDeck = new CardDeck(cards);
//        cards.clear();
//
//
//        Arrays.asList(MysteryTreasureType.values()).forEach(mysteryType -> cards.add(new MysteryTreasureCard(mysteryType)));
//        Arrays.asList(MysteryMonsterType.values()).forEach(mysteryType -> cards.add(new MysteryMonsterCard(mysteryType)));
//        Arrays.asList(MysteryTrapType.values()).forEach(mysteryType -> cards.add(new MysteryTrapCard(mysteryType)));
//        this.mysteryCardsDeck = new CardDeck(cards);
//        cards.clear();
//
//        Arrays.asList(StartingItemType.values()).forEach(startingItemType -> cards.add(new StrtingItemCard(startingItemType)));
//        this.startingItemCardsDeck = new CardDeck(cards);
//        cards.clear();
//
//        logger.info("Utworzono talie kart");
//
////        tasowanie talii
//        this.buildingAdventuresCardsDeck.shuffle();
//        this.gatheringResourcesAdventureCardsDeck.shuffle();
//        this.explorationAdventuresCardsDeck.shuffle();
//        this.eventCardsDeck.shuffle();
//        this.hountingCardsDeck.shuffle();
//        this.mysteryCardsDeck.shuffle();
//        this.startingItemCardsDeck.shuffle();
//
//        logger.info("Potasowano talie kart");
    }
}
