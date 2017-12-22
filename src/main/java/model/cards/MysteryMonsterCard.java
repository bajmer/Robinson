package model.cards;

import model.enums.cards.MysteryMonsterType;

public class MysteryMonsterCard implements ICard {
    private MysteryMonsterType monsterType;

    public MysteryMonsterCard(MysteryMonsterType monsterType) {
        this.monsterType = monsterType;
    }

    @Override
    public void useCard() {

    }
}
