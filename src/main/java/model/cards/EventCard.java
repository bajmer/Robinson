package model.cards;

import model.enums.cards.EventType;

public class EventCard implements ICard {
    private EventType event;

    public EventCard(EventType event) {
        this.event = event;
    }

    @Override
    public void useCard() {

    }
}
