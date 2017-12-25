package model;

import model.cards.InventionCard;
import model.cards.StartingItemCard;

import java.util.List;

public class GameInfo {
    private CharactersStats charactersStats;
    private Resources avaibleResources;
    private Resources futureResources;
    private List<InventionCard> ideas;
    private List<InventionCard> inventions;
    private List<StartingItemCard> startingItems;

    public GameInfo() {

    }

    public List<InventionCard> getIdeas() {
        return ideas;
    }

    public void setIdeas(List<InventionCard> ideas) {
        this.ideas = ideas;
    }

    public List<InventionCard> getInventions() {
        return inventions;
    }

    public void setInventions(List<InventionCard> inventions) {
        this.inventions = inventions;
    }

    public List<StartingItemCard> getStartingItems() {
        return startingItems;
    }

    public void setStartingItems(List<StartingItemCard> startingItems) {
        this.startingItems = startingItems;
    }
}