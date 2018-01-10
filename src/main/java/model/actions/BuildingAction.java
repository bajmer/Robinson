package model.actions;

import controller.GameEngineController;
import model.enums.elements.MarkerType;

import java.util.List;

public class BuildingAction extends Action {

    public BuildingAction(List<MarkerType> allowedMarkers) {
        super(allowedMarkers);
    }

    @Override
    public void runAction(GameEngineController controller) {

    }
}