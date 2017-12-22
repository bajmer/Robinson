package model.cards;

import model.enums.cards.MysteryTrapType;

public class MysteryTrapCard implements ICard {
    private MysteryTrapType trapType;

    public MysteryTrapCard(MysteryTrapType trapType) {
        this.trapType = trapType;
    }


    @Override
    public void useCard() {

    }
}
