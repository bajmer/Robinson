package model.cards;

import model.enums.cards.mysterycards.MysteryTreasureType;

public class MysteryTreasureCard implements Usable {
    private MysteryTreasureType treasureType;

    public MysteryTreasureCard(MysteryTreasureType treasureType) {
        this.treasureType = treasureType;
    }

    public MysteryTreasureType getTreasureType() {
        return treasureType;
    }

    public void setTreasureType(MysteryTreasureType treasureType) {
        this.treasureType = treasureType;
    }

    @Override
    public void use() {

    }
}
