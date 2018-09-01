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

    private static boolean Friday = false;
    private static boolean Dog = false;
    private static List<ICharacter> characters = new ArrayList<>();
    private static List<InventionCard> ideas = new ArrayList<>();
    private static List<InventionCard> inventions = new ArrayList<>();
    private static List<MysteryTreasureCard> treasures = new ArrayList<>();
    private static List<StartingItemCard> startingItems = new ArrayList<>();
    private static List<IslandTile> discoveredTiles = new ArrayList<>();
    private static LinkedList<EventCard> threatActionCards = new LinkedList<>();
    private static LinkedList<BeastCard> availableBeastCards = new LinkedList<>();
    private static List<Marker> allSelectionMarkers = new ArrayList<>();
    private static Resources availableResources = new Resources();
    private static Resources tmpAvailableResources = new Resources();
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
        logger.debug("Zmiana liczby żyć wszystkich postaci o " + value);
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
            logger.info("Zmiana morale o " + value + "! Teraz wynosi: " + moraleLevel);
        }
    }

    public static void changeFoodLevel(int value) {
        logger.debug("Zmiana ilości żywności o " + value);
        availableResources.setFoodAmount(availableResources.getFoodAmount() + value);
        if (availableResources.getFoodAmount() < 0) {
            availableResources.setLongExpiryDateFoodAmount(availableResources.getLongExpiryDateFoodAmount() + availableResources.getFoodAmount());
            availableResources.setFoodAmount(0);
            if (availableResources.getLongExpiryDateFoodAmount() < 0) {
                decreaseLifeForAllCharacters(availableResources.getLongExpiryDateFoodAmount());
                availableResources.setLongExpiryDateFoodAmount(0);
            }
        }
    }

    public static void changeWoodLevel(int value) {
        logger.debug("Zmiana ilości drewna o " + value);
        availableResources.setWoodAmount(availableResources.getWoodAmount() + value);
        if (availableResources.getWoodAmount() < 0) {
            decreaseLifeForAllCharacters(availableResources.getWoodAmount());
            availableResources.setWoodAmount(0);
        }
    }

    public static void changeHideLevel(int value) {
        logger.debug("Zmiana ilości skór o " + value);
        availableResources.setHideAmount(availableResources.getHideAmount() + value);
        if (availableResources.getHideAmount() < 0) {
            decreaseLifeForAllCharacters(availableResources.getHideAmount());
            availableResources.setHideAmount(0);
        }
    }

    public static void changePalisadeLevel(int value) {
        logger.debug("Zmiana poziomu palisady o " + value);
        palisadeLevel += value;
        if (palisadeLevel < 0) {
            decreaseLifeForAllCharacters(palisadeLevel);
            palisadeLevel = 0;
        }
    }

    public static void changeRoofLevel(int value) {
        logger.debug("Zmiana poziomu dachu o " + value);
        roofLevel += value;
        if (roofLevel < 0) {
            decreaseLifeForAllCharacters(roofLevel);
            roofLevel = 0;
        }
    }

    public static void changeWeaponLevel(int value) {
        logger.debug("Zmiana poziomu broni o " + value);
        weaponLevel += value;
        if (weaponLevel < 0) {
            decreaseLifeForAllCharacters(weaponLevel);
            weaponLevel = 0;
        }
    }

    public static boolean isFriday() {
        return Friday;
    }

    public static void setFriday(boolean friday) {
        GameInfo.Friday = friday;
    }

    public static boolean isDog() {
        return Dog;
    }

    public static void setDog(boolean dog) {
        GameInfo.Dog = dog;
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

    public static LinkedList<BeastCard> getAvailableBeastCards() {
        return availableBeastCards;
    }

    public static void setAvailableBeastCards(LinkedList<BeastCard> availableBeastCards) {
        GameInfo.availableBeastCards = availableBeastCards;
    }

    public static List<Marker> getAllSelectionMarkers() {
        return allSelectionMarkers;
    }

    public static void setAllSelectionMarkers(List<Marker> allSelectionMarkers) {
        GameInfo.allSelectionMarkers = allSelectionMarkers;
    }

    public static Resources getAvailableResources() {
        return availableResources;
    }

    public static void setAvailableResources(Resources availableResources) {
        GameInfo.availableResources = availableResources;
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

    public static Resources getTmpAvailableResources() {
        return tmpAvailableResources;
    }

    public static void setTmpAvailableResources(Resources tmpAvailableResources) {
        GameInfo.tmpAvailableResources = tmpAvailableResources;
    }
}