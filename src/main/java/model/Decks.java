package model;

import model.cards.*;

import java.util.LinkedList;

public class Decks {
    private static LinkedList<InventionCard> inventionCardsDeck = new LinkedList<>();
    private static LinkedList<EventCard> eventStackDeck = new LinkedList<>();
    private static LinkedList<BuildingAdventureCard> buildingAdventureCardsDeck = new LinkedList<>();
    private static LinkedList<GatheringResourcesAdventureCard> gatheringResourcesAdventureCardsDeck = new LinkedList<>();
    private static LinkedList<ExplorationAdventureCard> explorationAdventureCardsDeck = new LinkedList<>();
    private static LinkedList<BeastCard> beastCardsDeck = new LinkedList<>();
    private static LinkedList<Usable> mysteryCardsDeck = new LinkedList<>();
    private static LinkedList<IslandTile> islandTilesStack = new LinkedList<>();
    private static LinkedList<DiscoveryToken> discoveryTokensStack = new LinkedList<>();

    public static LinkedList<InventionCard> getInventionCardsDeck() {
        return inventionCardsDeck;
    }

    public static void setInventionCardsDeck(LinkedList<InventionCard> inventionCardsDeck) {
        Decks.inventionCardsDeck = inventionCardsDeck;
    }

    public static LinkedList<EventCard> getEventStackDeck() {
        return eventStackDeck;
    }

    public static void setEventStackDeck(LinkedList<EventCard> eventStackDeck) {
        Decks.eventStackDeck = eventStackDeck;
    }

    public static LinkedList<BuildingAdventureCard> getBuildingAdventureCardsDeck() {
        return buildingAdventureCardsDeck;
    }

    public static void setBuildingAdventureCardsDeck(LinkedList<BuildingAdventureCard> buildingAdventureCardsDeck) {
        Decks.buildingAdventureCardsDeck = buildingAdventureCardsDeck;
    }

    public static LinkedList<GatheringResourcesAdventureCard> getGatheringResourcesAdventureCardsDeck() {
        return gatheringResourcesAdventureCardsDeck;
    }

    public static void setGatheringResourcesAdventureCardsDeck(LinkedList<GatheringResourcesAdventureCard> gatheringResourcesAdventureCardsDeck) {
        Decks.gatheringResourcesAdventureCardsDeck = gatheringResourcesAdventureCardsDeck;
    }

    public static LinkedList<ExplorationAdventureCard> getExplorationAdventureCardsDeck() {
        return explorationAdventureCardsDeck;
    }

    public static void setExplorationAdventureCardsDeck(LinkedList<ExplorationAdventureCard> explorationAdventureCardsDeck) {
        Decks.explorationAdventureCardsDeck = explorationAdventureCardsDeck;
    }

    public static LinkedList<BeastCard> getBeastCardsDeck() {
        return beastCardsDeck;
    }

    public static void setBeastCardsDeck(LinkedList<BeastCard> beastCardsDeck) {
        Decks.beastCardsDeck = beastCardsDeck;
    }

    public static LinkedList<Usable> getMysteryCardsDeck() {
        return mysteryCardsDeck;
    }

    public static void setMysteryCardsDeck(LinkedList<Usable> mysteryCardsDeck) {
        Decks.mysteryCardsDeck = mysteryCardsDeck;
    }

    public static LinkedList<IslandTile> getIslandTilesStack() {
        return islandTilesStack;
    }

    public static void setIslandTilesStack(LinkedList<IslandTile> islandTilesStack) {
        Decks.islandTilesStack = islandTilesStack;
    }

    public static LinkedList<DiscoveryToken> getDiscoveryTokensStack() {
        return discoveryTokensStack;
    }

    public static void setDiscoveryTokensStack(LinkedList<DiscoveryToken> discoveryTokensStack) {
        Decks.discoveryTokensStack = discoveryTokensStack;
    }
}
