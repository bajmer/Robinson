package model.cards;

import model.enums.cards.BeastType;

import java.util.List;

public class BeastCard {
    private BeastType beast;
    private int strength;
    private int weaponLevelDecrease;
    private int foodAmount;
    private int hideAmount;

    public BeastCard(BeastType beast, List<Integer> parameters) {
        this.beast = beast;
        this.strength = parameters.get(0);
        this.weaponLevelDecrease = parameters.get(1);
        this.foodAmount = parameters.get(2);
        this.hideAmount = parameters.get(3);
    }

    public BeastType getBeast() {
        return beast;
    }

    public void setBeast(BeastType beast) {
        this.beast = beast;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getWeaponLevelDecrease() {
        return weaponLevelDecrease;
    }

    public void setWeaponLevelDecrease(int weaponLevelDecrease) {
        this.weaponLevelDecrease = weaponLevelDecrease;
    }

    public int getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        this.foodAmount = foodAmount;
    }

    public int getHideAmount() {
        return hideAmount;
    }

    public void setHideAmount(int hideAmount) {
        this.hideAmount = hideAmount;
    }
}
