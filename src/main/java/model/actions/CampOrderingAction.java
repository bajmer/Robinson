package model.actions;

import controller.GameEngineController;
import model.GameInfo;
import model.enums.elements.MarkerType;

import java.util.List;

public class CampOrderingAction extends Action {

    public CampOrderingAction(List<MarkerType> allowedMarkers) {
        super(allowedMarkers);
    }

    @Override
    public void runAction(GameEngineController controller) {
        super.getAssignedMarkers().forEach(marker -> {
            MarkerType markerType = marker.getMarkerType();
            controller.getGameInfo().getCharacters().forEach(iCharacter -> {
                if (iCharacter.getCharacterMarkers().get(0).getMarkerType() == markerType) {
                    iCharacter.changeDetermination(2);
                    GameInfo.changeMoraleLevel(1);
                }
            });
        });
    }
}