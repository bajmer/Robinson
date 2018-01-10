package model.cards;

import model.elements.Marker;
import model.enums.cards.eventcards.EventEffectType;
import model.enums.cards.eventcards.EventIconType;
import model.enums.cards.eventcards.ThreatActionType;
import model.enums.cards.eventcards.ThreatEffectType;

import java.util.List;

public class EventCard implements Usable {
    private EventEffectType eventEffect;
    private EventIconType eventIcon;
    private ThreatActionType threatAction;
    private ThreatEffectType threatEffect;

    public EventCard(EventEffectType eventEffect, EventIconType eventIcon, ThreatActionType threatAction, ThreatEffectType threatEffect) {
        this.eventEffect = eventEffect;
        this.eventIcon = eventIcon;
        this.threatAction = threatAction;
        this.threatEffect = threatEffect;
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

    public ThreatActionType getThreatAction() {
        return threatAction;
    }

    public void setThreatAction(ThreatActionType threatAction) {
        this.threatAction = threatAction;
    }

    public ThreatEffectType getThreatEffect() {
        return threatEffect;
    }

    public void setThreatEffect(ThreatEffectType threatEffect) {
        this.threatEffect = threatEffect;
    }

    @Override
    public void use() {
        runEventEffect();
    }

    private void runEventEffect() {

    }

    public void runThreatAction(List<Marker> markers) {
        int markersNumber = markers.size();
        switch (threatAction) {
            case THREAT_ACTION:
                break;
        }
    }

    public void runThreatEffect() {

    }
}
