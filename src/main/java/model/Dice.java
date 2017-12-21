package model;

import model.enums.DiceGroupType;
import model.enums.DiceType;

public class Dice {
    private DiceGroupType diceGroupType;
    private DiceType diceType;

    public DiceGroupType getDiceGroupType() {
        return diceGroupType;
    }

    public void setDiceGroupType(DiceGroupType diceGroupType) {
        this.diceGroupType = diceGroupType;
    }

    public DiceType getDiceType() {
        return diceType;
    }

    public void setDiceType(DiceType diceType) {
        this.diceType = diceType;
    }
}
