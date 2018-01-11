package model.actions;

import model.GameInfo;

public class CampOrderingAction extends Action {

    public CampOrderingAction() {
        super();
    }

    @Override
    public void runAction() {
        super.getAssignedMarkers().forEach(marker -> {
            marker.getCharacter().changeDetermination(2);
            GameInfo.changeMoraleLevel(1);
            super.getLogger().info("Porządkowanie obozu!");
        });
    }
}