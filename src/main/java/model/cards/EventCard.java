package model.cards;

import model.enums.cards.eventcards.EventEffectType;
import model.enums.cards.eventcards.EventIconType;
import model.enums.cards.eventcards.ThreatActionType;
import model.enums.cards.eventcards.ThreatEffectType;

public class EventCard implements Usable {
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
    public void use() {
        System.out.println("Karta wydarzenia: " + eventEffect);
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
