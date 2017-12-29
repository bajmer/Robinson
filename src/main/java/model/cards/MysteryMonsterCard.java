package model.cards;

import model.enums.cards.mysterycards.MysteryMonsterType;

public class MysteryMonsterCard implements Usable {
    private MysteryMonsterType monsterType;

    public MysteryMonsterCard(MysteryMonsterType monsterType) {
        this.monsterType = monsterType;
    }

    @Override
    public void use() {

    }
}
