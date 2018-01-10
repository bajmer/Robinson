package model.actions;

import controller.GameEngineController;
import model.enums.elements.MarkerType;

import java.util.List;

public class RestAction extends Action {

    public RestAction(List<MarkerType> allowedMarkers) {
        super(allowedMarkers);
    }

    @Override
    public void runAction(GameEngineController controller) {
        super.getAssignedMarkers().forEach(marker -> {
            MarkerType markerType = marker.getMarkerType();
            controller.getGameInfo().getCharacters().forEach(iCharacter -> {
                if (iCharacter.getCharacterMarkers().get(0).getMarkerType() == markerType) {
                    iCharacter.changeLife(1);
                }
            });
        });
    }
}