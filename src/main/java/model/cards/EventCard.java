package model.cards;

import model.enums.cards.EventEffectType;
import model.enums.cards.EventIconType;
import model.enums.cards.ThreatActionType;
import model.enums.cards.ThreatEffectType;

public class EventCard implements ICard {
    private static final int firstId = 4;
    private static final int lastId = 76;
    private EventEffectType eventEffect;
    private EventIconType eventIcon;
    private ThreatActionType dangerAction;
    private ThreatEffectType dangerEffect;

    public EventCard(EventEffectType eventEffect, EventIconType eventIcon, ThreatActionType dangerAction, ThreatEffectType dangerEffect) {
        this.eventEffect = eventEffect;
        this.eventIcon = eventIcon;
        this.dangerAction = dangerAction;
        this.dangerEffect = dangerEffect;
    }

    public static int getFirstId() {
        return firstId;
    }

    public static int getLastId() {
        return lastId;
    }

    public EventEffectType getEventEffect() {
        return eventEffect;
    }

    public void setEventEffect(EventEffectType eventEffect) {
        this.eventEffect = eventEffect;
    }

    public EventIconType getEventIcon() {
        return eventIcon;
    }

    public void setEventIcon(EventIconType eventIcon) {
        this.eventIcon = eventIcon;
    }

    public ThreatActionType getDangerAction() {
        return dangerAction;
    }

    public void setDangerAction(ThreatActionType dangerAction) {
        this.dangerAction = dangerAction;
    }

    public ThreatEffectType getDangerEffect() {
        return dangerEffect;
    }

    public void setDangerEffect(ThreatEffectType dangerEffect) {
        this.dangerEffect = dangerEffect;
    }

    @Override
    public void useCard() {

    }

    @Override
    public String toString() {
        return "EventCard{" +
                "eventEffect=" + eventEffect +
                ", eventIcon=" + eventIcon +
                ", dangerAction=" + dangerAction +
                ", dangerEffect=" + dangerEffect +
                '}';
    }
}
