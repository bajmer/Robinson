package model.cards;

import model.enums.cards.BeastType;

public class BeastCard implements ICard {
    private BeastType beast;
    private int strength;
    private int weaponLevelDecrease;
    private int foodAmount;
    private int hideAmount;

    public BeastCard(BeastType beast) {

        this.beast = beast;

        switch (beast) {
            case ALLIGATOR:
                this.strength = 0;
                this.weaponLevelDecrease = 0;
                this.foodAmount = 0;
                this.hideAmount = 0;
                break;
            case BEAR:
                this.strength = 0;
                this.weaponLevelDecrease = 0;
                this.foodAmount = 0;
                this.hideAmount = 0;
                break;
            case BIRDS:
                this.strength = 0;
                this.weaponLevelDecrease = 0;
                this.foodAmount = 0;
                this.hideAmount = 0;
                break;
            case BOA:
                this.strength = 0;
                this.weaponLevelDecrease = 0;
                this.foodAmount = 0;
                this.hideAmount = 0;
                break;
            case CHAMOIS:
                this.strength = 0;
                this.weaponLevelDecrease = 0;
                this.foodAmount = 0;
                this.hideAmount = 0;
                break;
            case CHEETAH:
                this.strength = 0;
                this.weaponLevelDecrease = 0;
                this.foodAmount = 0;
                this.hideAmount = 0;
                break;
            case FOX:
                this.strength = 0;
                this.weaponLevelDecrease = 0;
                this.foodAmount = 0;
                this.hideAmount = 0;
                break;
            case GOATS:
                this.strength = 0;
                this.weaponLevelDecrease = 0;
                this.foodAmount = 0;
                this.hideAmount = 0;
                break;
            case GORILLA:
                this.strength = 0;
                this.weaponLevelDecrease = 0;
                this.foodAmount = 0;
                this.hideAmount = 0;
                break;
            case IGUANA:
                this.strength = 0;
                this.weaponLevelDecrease = 0;
                this.foodAmount = 0;
                this.hideAmount = 0;
                break;
            case JAGUAR:
                this.strength = 0;
                this.weaponLevelDecrease = 0;
                this.foodAmount = 0;
                this.hideAmount = 0;
                break;
            case PUMA:
                this.strength = 0;
                this.weaponLevelDecrease = 0;
                this.foodAmount = 0;
                this.hideAmount = 0;
                break;
            case TAPIR:
                this.strength = 0;
                this.weaponLevelDecrease = 0;
                this.foodAmount = 0;
                this.hideAmount = 0;
                break;
            case TIGER:
                this.strength = 0;
                this.weaponLevelDecrease = 0;
                this.foodAmount = 0;
                this.hideAmount = 0;
                break;
            case WILD_DOG:
                this.strength = 0;
                this.weaponLevelDecrease = 0;
                this.foodAmount = 0;
                this.hideAmount = 0;
                break;
            case WILD_PIG:
                this.strength = 0;
                this.weaponLevelDecrease = 0;
                this.foodAmount = 0;
                this.hideAmount = 0;
                break;
        }

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

    @Override
    public void useCard() {

    }
}
