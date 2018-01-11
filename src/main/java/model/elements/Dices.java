package model.elements;

import model.enums.elements.DiceType;
import model.enums.elements.DiceWallType;

import java.util.*;

import static model.enums.elements.DiceType.*;
import static model.enums.elements.DiceWallType.*;

public class Dices {

    private List<DiceWallType> weatherRainDice;
    private List<DiceWallType> weatherSnowDice;
    private List<DiceWallType> weatherHungryAnimalsDice;
    private List<DiceWallType> brownSuccessDice;
    private List<DiceWallType> brownAdventureDice;
    private List<DiceWallType> brownWoundDice;
    private List<DiceWallType> graySuccessDice;
    private List<DiceWallType> grayAdventureDice;
    private List<DiceWallType> grayWoundDice;
    private List<DiceWallType> greenSuccessDice;
    private List<DiceWallType> greenAdventureDice;
    private List<DiceWallType> greenWoundDice;

    private Map<DiceType, List<DiceWallType>> diceTypeToDiceMapping;

    public Dices() {
        weatherRainDice = new ArrayList<>();
        weatherRainDice.add(SINGLE_RAIN);
        weatherRainDice.add(SINGLE_RAIN);
        weatherRainDice.add(SINGLE_RAIN);
        weatherRainDice.add(DOUBLE_RAIN);
        weatherRainDice.add(DOUBLE_RAIN);
        weatherRainDice.add(SINGLE_SNOW);

        weatherSnowDice = new ArrayList<>();
        weatherSnowDice.add(DOUBLE_RAIN);
        weatherSnowDice.add(DOUBLE_RAIN);
        weatherSnowDice.add(SINGLE_SNOW);
        weatherSnowDice.add(SINGLE_SNOW);
        weatherSnowDice.add(DOUBLE_SNOW);
        weatherSnowDice.add(DOUBLE_SNOW);

        weatherHungryAnimalsDice = new ArrayList<>();
        weatherHungryAnimalsDice.add(NOTHING);
        weatherHungryAnimalsDice.add(NOTHING);
        weatherHungryAnimalsDice.add(BEAST_ATTACK);
        weatherHungryAnimalsDice.add(PALISADE_DAMAGE);
        weatherHungryAnimalsDice.add(PALISADE_DAMAGE);
        weatherHungryAnimalsDice.add(FOOD_DISCARD);

        diceTypeToDiceMapping = new HashMap<>();
        diceTypeToDiceMapping.put(RAIN_CLOUD_DICE, weatherRainDice);
        diceTypeToDiceMapping.put(SNOW_CLOUD_DICE, weatherSnowDice);
        diceTypeToDiceMapping.put(HUNGRY_ANIMALS_DICE, weatherHungryAnimalsDice);
    }

    public Map<DiceType, List<DiceWallType>> getDiceTypeToDiceMapping() {
        return diceTypeToDiceMapping;
    }

    public void setDiceTypeToDiceMapping(Map<DiceType, List<DiceWallType>> diceTypeToDiceMapping) {
        this.diceTypeToDiceMapping = diceTypeToDiceMapping;
    }

    public List<DiceWallType> getWeatherRainDice() {
        return weatherRainDice;
    }

    public void setWeatherRainDice(List<DiceWallType> weatherRainDice) {
        this.weatherRainDice = weatherRainDice;
    }

    public List<DiceWallType> getWeatherSnowDice() {
        return weatherSnowDice;
    }

    public void setWeatherSnowDice(List<DiceWallType> weatherSnowDice) {
        this.weatherSnowDice = weatherSnowDice;
    }

    public List<DiceWallType> getWeatherHungryAnimalsDice() {
        return weatherHungryAnimalsDice;
    }

    public void setWeatherHungryAnimalsDice(List<DiceWallType> weatherHungryAnimalsDice) {
        this.weatherHungryAnimalsDice = weatherHungryAnimalsDice;
    }

    public List<DiceWallType> getBrownSuccessDice() {
        return brownSuccessDice;
    }

    public void setBrownSuccessDice(List<DiceWallType> brownSuccessDice) {
        this.brownSuccessDice = brownSuccessDice;
    }

    public List<DiceWallType> getBrownAdventureDice() {
        return brownAdventureDice;
    }

    public void setBrownAdventureDice(List<DiceWallType> brownAdventureDice) {
        this.brownAdventureDice = brownAdventureDice;
    }

    public List<DiceWallType> getBrownWoundDice() {
        return brownWoundDice;
    }

    public void setBrownWoundDice(List<DiceWallType> brownWoundDice) {
        this.brownWoundDice = brownWoundDice;
    }

    public List<DiceWallType> getGraySuccessDice() {
        return graySuccessDice;
    }

    public void setGraySuccessDice(List<DiceWallType> graySuccessDice) {
        this.graySuccessDice = graySuccessDice;
    }

    public List<DiceWallType> getGrayAdventureDice() {
        return grayAdventureDice;
    }

    public void setGrayAdventureDice(List<DiceWallType> grayAdventureDice) {
        this.grayAdventureDice = grayAdventureDice;
    }

    public List<DiceWallType> getGrayWoundDice() {
        return grayWoundDice;
    }

    public void setGrayWoundDice(List<DiceWallType> grayWoundDice) {
        this.grayWoundDice = grayWoundDice;
    }

    public List<DiceWallType> getGreenSuccessDice() {
        return greenSuccessDice;
    }

    public void setGreenSuccessDice(List<DiceWallType> greenSuccessDice) {
        this.greenSuccessDice = greenSuccessDice;
    }

    public List<DiceWallType> getGreenAdventureDice() {
        return greenAdventureDice;
    }

    public void setGreenAdventureDice(List<DiceWallType> greenAdventureDice) {
        this.greenAdventureDice = greenAdventureDice;
    }

    public List<DiceWallType> getGreenWoundDice() {
        return greenWoundDice;
    }

    public void setGreenWoundDice(List<DiceWallType> greenWoundDice) {
        this.greenWoundDice = greenWoundDice;
    }

    public DiceWallType roll(List<DiceWallType> dice) {
        return dice.get(new Random().nextInt(6));
    }
}
