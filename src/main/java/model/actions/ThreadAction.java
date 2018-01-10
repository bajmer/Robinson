package model.actions;

import controller.GameEngineController;
import model.cards.EventCard;
import model.enums.elements.MarkerType;

import java.util.List;

public class ThreadAction extends Action {

    private EventCard threadCard;

    public ThreadAction(List<MarkerType> allowedMarkers) {
        super(allowedMarkers);
    }

    public EventCard getThreadCard() {
        return threadCard;
    }

    public void setThreadCard(EventCard threadCard) {
        this.threadCard = threadCard;
    }

    @Override
    public void runAction(GameEngineController controller) {
//        threadCard.runThreatAction(super.getAssignedMarkers());
    }
}
