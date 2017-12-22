package model.cards;

import model.enums.cards.MysteryTreasureType;

public class MysteryTreasureCard implements ICard {
    private MysteryTreasureType treasureType;

    public MysteryTreasureCard(MysteryTreasureType treasureType) {
        this.treasureType = treasureType;
    }

    @Override
    public void useCard() {

    }
}
