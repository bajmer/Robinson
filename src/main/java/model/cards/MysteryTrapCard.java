package model.cards;

import model.enums.cards.mysterycards.MysteryTrapType;

public class MysteryTrapCard implements Mysterious {
    private MysteryTrapType trapType;

    public MysteryTrapCard(MysteryTrapType trapType) {
        this.trapType = trapType;
    }
}
