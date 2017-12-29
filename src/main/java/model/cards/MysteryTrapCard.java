package model.cards;

import model.enums.cards.mysterycards.MysteryTrapType;

public class MysteryTrapCard implements Usable {
    private MysteryTrapType trapType;

    public MysteryTrapCard(MysteryTrapType trapType) {
        this.trapType = trapType;
    }


    @Override
    public void use() {

    }
}
