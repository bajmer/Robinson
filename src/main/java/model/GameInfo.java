package model;

import model.cards.*;
import model.elements.Marker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameInfo {
    private static Logger logger = LogManager.getLogger(GameInfo.class);

    private static List<ICharacter> characters = new ArrayList<>();
    private static List<InventionCard> ideas = new ArrayList<>();
    private static List<InventionCard> inventions = new ArrayList<>();
    private static List<MysteryTreasureCard> treasures = new ArrayList<>();
    private static List<StartingItemCard> startingItems = new ArrayList<>();
    private static List<IslandTile> discoveredTiles = new ArrayList<>();
    private static LinkedList<EventCard> threatActionCards = new LinkedList<>();
    private static LinkedList<BeastCard> avaibleBeastCards = new LinkedList<>();
    private static List<Marker> allSelectionMarkers = new ArrayList<>();
    private static Resources avaibleResources = new Resources();
    private static Resources futureResources = new Resources();
    private static Character firstPlayer;
    private static IslandTile camp;
    private static boolean shelter = false;
    private static int moraleLevel = 0;
    private static int roofLevel = 0;
    private static int palisadeLevel = 0;
    private static int weaponLevel = 0;
    private static int productionFoodNumber = 1;
    private static int productionWoodNumber = 1;
    public GameInfo() {
    }

    private static void decreaseLifeForAllCharacters(int value) {
        characters.forEach(character -> {
            if (character instanceof Character) {
                character.changeLife(value);
            }
        });
    }

    public static void changeMoraleLevel(int value) {
        int beginMoraleLevel = moraleLevel;
        moraleLevel += value;
        if (moraleLevel > 3) {
            moraleLevel = 3;
        } else if (moraleLevel < -3) {
            moraleLevel = -3;
        }

        if (moraleLevel != beginMoraleLevel) {
            logger.info("Zmiana morale! Teraz wynosi: " + moraleLevel);
        }
    }

    public static void changeFoodLevel(int value) {
        avaibleResources.setFoodAmount(avaibleResources.getFoodAmount() + value);
        if (avaibleResources.getFoodAmount() < 0) {
            avaibleResources.setLongExpiryDateFoodAmount(avaibleResources.getLongExpiryDateFoodAmount() + avaibleResources.getFoodAmount());
            avaibleResources.setFoodAmount(0);
            if (avaibleResources.getLongExpiryDateFoodAmount() < 0) {
                decreaseLifeForAllCharacters(avaibleResources.getLongExpiryDateFoodAmount());
                avaibleResources.setLongExpiryDateFoodAmount(0);
            }
        }
    }

    public static void changeWoodLevel(int value) {
        avaibleResources.setWoodAmount(avaibleResources.getWoodAmount() + value);
        if (avaibleResources.getWoodAmount() < 0) {
            decreaseLifeForAllCharacters(avaibleResources.getWoodAmount());
            avaibleResources.setWoodAmount(0);
        }
    }

    public static void changeHideLevel(int value) {
        avaibleResources.setHideAmount(avaibleResources.getHideAmount() + value);
        if (avaibleResources.getHideAmount() < 0) {
            decreaseLifeForAllCharacters(avaibleResources.getHideAmount());
            avaibleResources.setHideAmount(0);
        }
    }

    public static void changePalisadeLevel(int value) {
        palisadeLevel += value;
        if (palisadeLevel < 0) {
            decreaseLifeForAllCharacters(palisadeLevel);
            palisadeLevel = 0;
        }
    }

    public static void changeRoofLevel(int value) {
        roofLevel += value;
        if (roofLevel < 0) {
            decreaseLifeForAllCharacters(roofLevel);
            roofLevel = 0;
        }
    }

    public static void changeWeaponLevel(int value) {
        weaponLevel += value;
        if (weaponLevel < 0) {
            decreaseLifeForAllCharacters(weaponLevel);
            weaponLevel = 0;
        }
    }

    public static int getMoraleLevel() {
        return moraleLevel;
    }

    public static void setMoraleLevel(int moraleLevel) {
        GameInfo.moraleLevel = moraleLevel;
    }

    public static List<ICharacter> getCharacters() {
        return characters;
    }

    public static void setCharacters(List<ICharacter> characters) {
        GameInfo.characters = characters;
    }

    public static List<InventionCard> getIdeas() {
        return ideas;
    }

    public static void setIdeas(List<InventionCard> ideas) {
        GameInfo.ideas = ideas;
    }

    public static List<InventionCard> getInventions() {
        return inventions;
    }

    public static void setInventions(List<InventionCard> inventions) {
        GameInfo.inventions = inventions;
    }

    public static List<MysteryTreasureCard> getTreasures() {
        return treasures;
    }

    public static void setTreasures(List<MysteryTreasureCard> treasures) {
        GameInfo.treasures = treasures;
    }

    public static List<StartingItemCard> getStartingItems() {
        return startingItems;
    }

    public static void setStartingItems(List<StartingItemCard> startingItems) {
        GameInfo.startingItems = startingItems;
    }

    public static List<IslandTile> getDiscoveredTiles() {
        return discoveredTiles;
    }

    public static void setDiscoveredTiles(List<IslandTile> discoveredTiles) {
        GameInfo.discoveredTiles = discoveredTiles;
    }

    public static LinkedList<EventCard> getThreatActionCards() {
        return threatActionCards;
    }

    public static void setThreatActionCards(LinkedList<EventCard> threatActionCards) {
        GameInfo.threatActionCards = threatActionCards;
    }

    public static LinkedList<BeastCard> getAvaibleBeastCards() {
        return avaibleBeastCards;
    }

    public static void setAvaibleBeastCards(LinkedList<BeastCard> avaibleBeastCards) {
        GameInfo.avaibleBeastCards = avaibleBeastCards;
    }

    public static List<Marker> getAllSelectionMarkers() {
        return allSelectionMarkers;
    }

    public static void setAllSelectionMarkers(List<Marker> allSelectionMarkers) {
        GameInfo.allSelectionMarkers = allSelectionMarkers;
    }

    public static Resources getAvaibleResources() {
        return avaibleResources;
    }

    public static void setAvaibleResources(Resources avaibleResources) {
        GameInfo.avaibleResources = avaibleResources;
    }

    public static Resources getFutureResources() {
        return futureResources;
    }

    public static void setFutureResources(Resources futureResources) {
        GameInfo.futureResources = futureResources;
    }

    public static Character getFirstPlayer() {
        return firstPlayer;
    }

    public static void setFirstPlayer(Character firstPlayer) {
        GameInfo.firstPlayer = firstPlayer;
    }

    public static IslandTile getCamp() {
        return camp;
    }

    public static void setCamp(IslandTile camp) {
        GameInfo.camp = camp;
    }

    public static boolean isShelter() {
        return shelter;
    }

    public static void setShelter(boolean shelter) {
        GameInfo.shelter = shelter;
    }

    public static int getRoofLevel() {
        return roofLevel;
    }

    public static void setRoofLevel(int roofLevel) {
        GameInfo.roofLevel = roofLevel;
    }

    public static int getPalisadeLevel() {
        return palisadeLevel;
    }

    public static void setPalisadeLevel(int palisadeLevel) {
        GameInfo.palisadeLevel = palisadeLevel;
    }

    public static int getWeaponLevel() {
        return weaponLevel;
    }

    public static void setWeaponLevel(int weaponLevel) {
        GameInfo.weaponLevel = weaponLevel;
    }

    public static int getProductionFoodNumber() {
        return productionFoodNumber;
    }

    public static void setProductionFoodNumber(int productionFoodNumber) {
        GameInfo.productionFoodNumber = productionFoodNumber;
    }

    public static int getProductionWoodNumber() {
        return productionWoodNumber;
    }

    public static void setProductionWoodNumber(int productionWoodNumber) {
        GameInfo.productionWoodNumber = productionWoodNumber;
    }
}