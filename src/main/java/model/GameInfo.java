package model;

import model.cards.InventionCard;
import model.cards.StartingItemCard;

import java.util.ArrayList;
import java.util.List;

public class GameInfo {
    private List<Character> characters;
    private List<InventionCard> ideas;
    private List<InventionCard> inventions;
    private List<StartingItemCard> startingItems;
    private CharactersStats charactersStats;
    private Resources avaibleResources;
    private Resources futureResources;

    public GameInfo() {
        this.characters = new ArrayList<>();
        this.ideas = new ArrayList<>();
        this.inventions = new ArrayList<>();
        this.startingItems = new ArrayList<>();
        charactersStats = new CharactersStats();
        avaibleResources = new Resources();
        futureResources = new Resources();
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public CharactersStats getCharactersStats() {
        return charactersStats;
    }

    public void setCharactersStats(CharactersStats charactersStats) {
        this.charactersStats = charactersStats;
    }

    public Resources getAvaibleResources() {
        return avaibleResources;
    }

    public void setAvaibleResources(Resources avaibleResources) {
        this.avaibleResources = avaibleResources;
    }

    public Resources getFutureResources() {
        return futureResources;
    }

    public void setFutureResources(Resources futureResources) {
        this.futureResources = futureResources;
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