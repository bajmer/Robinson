package model.actions;

import controller.GameEngineController;
import model.enums.elements.MarkerType;

import java.util.List;

public class GatheringResourcesAction extends Action {

    public GatheringResourcesAction(List<MarkerType> allowedMarkers) {
        super(allowedMarkers);
    }

    @Override
    public void runAction(GameEngineController controller) {

    }
}