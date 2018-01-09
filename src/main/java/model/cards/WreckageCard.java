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

    public WreckageEventEffectType getWreckageEventEffect() {
        return wreckageEventEffect;
    }

    public void setWreckageEventEffect(WreckageEventEffectType wreckageEventEffect) {
        this.wreckageEventEffect = wreckageEventEffect;
    }

    public WreckageThreatActionType getWreckageThreatAction() {
        return wreckageThreatAction;
    }

    public void setWreckageThreatAction(WreckageThreatActionType wreckageThreatAction) {
        this.wreckageThreatAction = wreckageThreatAction;
    }

    public WreckageThreatEffectType getWreckageThreatEffect() {
        return wreckageThreatEffect;
    }

    public void setWreckageThreatEffect(WreckageThreatEffectType wreckageThreatEffect) {
        this.wreckageThreatEffect = wreckageThreatEffect;
    }

    @Override
    public void use() {

    }
}
