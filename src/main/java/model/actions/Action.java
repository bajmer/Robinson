package model.actions;

import controller.GameEngineController;
import model.elements.Marker;
import model.enums.elements.MarkerType;

import java.util.ArrayList;
import java.util.List;

public abstract class Action {
    private List<MarkerType> allowedMarkers;
    private List<Marker> assignedMarkers;

    public Action(List<MarkerType> allowedMarkers) {

        this.allowedMarkers = allowedMarkers;
        this.assignedMarkers = new ArrayList<>();
    }

    public List<MarkerType> getAllowedMarkers() {
        return allowedMarkers;
    }

    public void setAllowedMarkers(List<MarkerType> allowedMarkers) {
        this.allowedMarkers = allowedMarkers;
    }

    public List<Marker> getAssignedMarkers() {
        return assignedMarkers;
    }

    public void setAssignedMarkers(List<Marker> assignedMarkers) {
        this.assignedMarkers = assignedMarkers;
    }

    public void runAction(GameEngineController controller) {

    }
}
