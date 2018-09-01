package model.cards;

import model.enums.cards.mysterycards.MysteryMonsterType;

public class MysteryMonsterCard implements Mysterious {
    private MysteryMonsterType monsterType;

    public MysteryMonsterCard(MysteryMonsterType monsterType) {
        this.monsterType = monsterType;
    }
}
