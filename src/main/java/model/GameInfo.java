package model;

import model.cards.InventionCard;
import model.cards.IslandTile;
import model.cards.MysteryTreasureCard;
import model.cards.StartingItemCard;

import java.util.ArrayList;
import java.util.List;

public class GameInfo {
    private List<Character> characters;
    private List<InventionCard> ideas;
    private List<InventionCard> inventions;
    private List<MysteryTreasureCard> treasures;
    private List<StartingItemCard> startingItems;
    private List<IslandTile> discoveredTiles;
    private Resources avaibleResources;
    private Resources futureResources;
    private Character firstPlayer;
    private IslandTile camp;
    private boolean isShelter;
    private int moraleLevel;
    private int roofLevel;
    private int palisadeLevel;
    private int weaponLevel;
    private int productionFoodNumber;
    private int productionWoodNumber;

    public GameInfo() {
        characters = new ArrayList<>();
        ideas = new ArrayList<>();
        inventions = new ArrayList<>();
        treasures = new ArrayList<>();
        startingItems = new ArrayList<>();
        discoveredTiles = new ArrayList<>();
        avaibleResources = new Resources();
        futureResources = new Resources();
        isShelter = false;
        moraleLevel = 0;
        roofLevel = 0;
        palisadeLevel = 0;
        weaponLevel = 0;
        productionFoodNumber = 1;
        productionWoodNumber = 1;
    }

    public List<MysteryTreasureCard> getTreasures() {
        return treasures;
    }

    public void setTreasures(List<MysteryTreasureCard> treasures) {
        this.treasures = treasures;
    }

    public int getProductionFoodNumber() {
        return productionFoodNumber;
    }

    public void setProductionFoodNumber(int productionFoodNumber) {
        this.productionFoodNumber = productionFoodNumber;
    }

    public int getProductionWoodNumber() {
        return productionWoodNumber;
    }

    public void setProductionWoodNumber(int productionWoodNumber) {
        this.productionWoodNumber = productionWoodNumber;
    }

    public List<IslandTile> getDiscoveredTiles() {
        return discoveredTiles;
    }

    public void setDiscoveredTiles(List<IslandTile> discoveredTiles) {
        this.discoveredTiles = discoveredTiles;
    }

    public Character getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Character firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public IslandTile getCamp() {
        return camp;
    }

    public void setCamp(IslandTile camp) {
        this.camp = camp;
    }

    public boolean isShelter() {
        return isShelter;
    }

    public void setShelter(boolean shelter) {
        isShelter = shelter;
    }

    public int getMoraleLevel() {
        return moraleLevel;
    }

    public void setMoraleLevel(int moraleLevel) {
        this.moraleLevel = moraleLevel;
    }

    public int getRoofLevel() {
        return roofLevel;
    }

    public void setRoofLevel(int roofLevel) {
        this.roofLevel = roofLevel;
    }

    public int getPalisadeLevel() {
        return palisadeLevel;
    }

    public void setPalisadeLevel(int palisadeLevel) {
        this.palisadeLevel = palisadeLevel;
    }

    public int getWeaponLevel() {
        return weaponLevel;
    }

    public void setWeaponLevel(int weaponLevel) {
        this.weaponLevel = weaponLevel;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
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