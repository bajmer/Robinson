package model.actions;

import model.cards.EventCard;

public class ThreadAction extends Action {

    private EventCard threadCard;

    public ThreadAction() {
        super();
    }

    public EventCard getThreadCard() {
        return threadCard;
    }

    public void setThreadCard(EventCard threadCard) {
        this.threadCard = threadCard;
    }

    @Override
    public void runAction() {
//        threadCard.runThreatAction(super.getAssignedMarkers());
    }
}
