package model.cards;

import model.enums.cards.wreckagecards.WreckageEventEffectType;
import model.enums.cards.wreckagecards.WreckageThreatActionType;
import model.enums.cards.wreckagecards.WreckageThreatEffectType;

public class WreckageCard implements Usable {
    private WreckageEventEffectType wreckageEventEffect;
    private WreckageThreatActionType wreckageThreatAction;
    private WreckageThreatEffectType wreckageThreatEffect;

    public WreckageCard(WreckageEventEffectType wreckageEventEffect, WreckageThreatActionType wreckageThreatAction, WreckageThreatEffectType wreckageThreatEffect) {
        this.wreckageEventEffect = wreckageEventEffect;
        this.wreckageThreatAction = wreckageThreatAction;
        this.wreckageThreatEffect = wreckageThreatEffect;
    }

    @Override
    public void use() {
        System.out.println("Karta wraku: " + wreckageEventEffect);
    }

    @Override
    public String toString() {
        return "WreckageCard{" +
                "wreckageEventEffect=" + wreckageEventEffect +
                ", wreckageThreatAction=" + wreckageThreatAction +
                ", wreckageThreatEffect=" + wreckageThreatEffect +
                '}';
    }
}
