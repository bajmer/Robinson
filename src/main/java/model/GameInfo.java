package model;

import model.cards.*;
import model.elements.Marker;
import model.enums.ProfessionType;
import model.enums.elements.MarkerType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static model.enums.elements.MarkerType.*;

public class GameInfo {
    private static int moraleLevel;
    private List<ICharacter> characters;
    private List<InventionCard> ideas;
    private List<InventionCard> inventions;
    private List<MysteryTreasureCard> treasures;
    private List<StartingItemCard> startingItems;
    private List<IslandTile> discoveredTiles;
    private LinkedList<EventCard> threatActionCards;
    private LinkedList<BeastCard> avaibleBeastCards;
    private List<Marker> avaibleCharactersMarkers;
    private List<MarkerType> charactersMarkerTypes;
    private Resources avaibleResources;
    private Resources futureResources;
    private Character firstPlayer;
    private IslandTile camp;
    private boolean isShelter;
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
        threatActionCards = new LinkedList<>();
        avaibleBeastCards = new LinkedList<>();
        avaibleCharactersMarkers = new ArrayList<>();
        avaibleResources = new Resources();
        futureResources = new Resources();
        isShelter = false;
        moraleLevel = 0;
        roofLevel = 0;
        palisadeLevel = 0;
        weaponLevel = 0;
        productionFoodNumber = 1;
        productionWoodNumber = 1;

        charactersMarkerTypes = new ArrayList<>();
        charactersMarkerTypes.add(CARPENTER_MARKER);
        charactersMarkerTypes.add(COOK_MARKER);
        charactersMarkerTypes.add(EXPLORER_MARKER);
        charactersMarkerTypes.add(SOLDIER_MARKER);
        charactersMarkerTypes.add(FRIDAY_MARKER);
    }

    public static int getMoraleLevel() {
        return moraleLevel;
    }

    public static void setMoraleLevel(int moraleLevel) {
        GameInfo.moraleLevel = moraleLevel;
    }

    public static void changeMoraleLevel(int moraleChange) {
        moraleLevel += moraleChange;
        if (moraleLevel > 3) moraleLevel = 3;
        else if (moraleLevel < -3) moraleLevel = -3;
    }

    public LinkedList<BeastCard> getAvaibleBeastCards() {
        return avaibleBeastCards;
    }

    public void setAvaibleBeastCards(LinkedList<BeastCard> avaibleBeastCards) {
        this.avaibleBeastCards = avaibleBeastCards;
    }

    public List<MarkerType> getCharactersMarkerTypes() {
        return charactersMarkerTypes;
    }

    public void setCharactersMarkerTypes(List<MarkerType> charactersMarkerTypes) {
        this.charactersMarkerTypes = charactersMarkerTypes;
    }

    public List<Marker> getAvaibleCharactersMarkers() {
        return avaibleCharactersMarkers;
    }

    public void setAvaibleCharactersMarkers(List<Marker> avaibleCharactersMarkers) {
        this.avaibleCharactersMarkers = avaibleCharactersMarkers;
    }

    public LinkedList<EventCard> getThreatActionCards() {
        return threatActionCards;
    }

    public void setThreatActionCards(LinkedList<EventCard> threatActionCards) {
        this.threatActionCards = threatActionCards;
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

    public List<ICharacter> getCharacters() {
        return characters;
    }

    public void setCharacters(List<ICharacter> characters) {
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

    private void decreaseLifeForAllCharacters(int value) {
        characters.forEach(character -> character.changeLife(value));
    }

    public void changeFoodLevel(int value, List<ProfessionType> starvingProfessions) {
        avaibleResources.setFoodAmount(avaibleResources.getFoodAmount() + value);
        if (avaibleResources.getFoodAmount() < 0) {
            avaibleResources.setLongExpiryDateFoodAmount(avaibleResources.getLongExpiryDateFoodAmount() + avaibleResources.getFoodAmount());
            avaibleResources.setFoodAmount(0);
            if (avaibleResources.getLongExpiryDateFoodAmount() < 0) {
                if (starvingProfessions == null) {
                    decreaseLifeForAllCharacters(avaibleResources.getLongExpiryDateFoodAmount());
                } else {
                    characters.forEach(character -> {
                        if (character instanceof Character) {
                            if (starvingProfessions.contains(((Character) character).getProfession())) {
                                character.changeLife(-2);
                            }
                        }
                    });
                }
                avaibleResources.setLongExpiryDateFoodAmount(0);
            }
        }
    }

    public void changeWoodLevel(int value) {
        avaibleResources.setWoodAmount(avaibleResources.getWoodAmount() + value);
        if (avaibleResources.getWoodAmount() < 0) {
            decreaseLifeForAllCharacters(avaibleResources.getWoodAmount());
            avaibleResources.setWoodAmount(0);
        }
    }

    public void changeHideLevel(int value) {
        avaibleResources.setHideAmount(avaibleResources.getHideAmount() + value);
        if (avaibleResources.getHideAmount() < 0) {
            decreaseLifeForAllCharacters(avaibleResources.getHideAmount());
            avaibleResources.setHideAmount(0);
        }
    }

    public void changePalisadeLevel(int value) {
        palisadeLevel += value;
        if (palisadeLevel < 0) {
            decreaseLifeForAllCharacters(palisadeLevel);
            palisadeLevel = 0;
        }
    }

    public void changeRoofLevel(int value) {
        roofLevel += value;
        if (roofLevel < 0) {
            decreaseLifeForAllCharacters(roofLevel);
            roofLevel = 0;
        }
    }

    public void changeWeaponLevel(int value) {
        weaponLevel += value;
        if (weaponLevel < 0) {
            decreaseLifeForAllCharacters(weaponLevel);
            weaponLevel = 0;
        }
    }
}