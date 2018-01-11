package model.actions;

import model.elements.Marker;
import model.enums.elements.MarkerType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static model.enums.elements.MarkerType.*;

public abstract class Action {
    private Logger logger = LogManager.getLogger(Action.class);
    private List<MarkerType> allowedMarkers;
    private List<Marker> assignedMarkers;

    public Action() {
        this.allowedMarkers = new ArrayList<>(Arrays.asList(CARPENTER_MARKER, COOK_MARKER, EXPLORER_MARKER, SOLDIER_MARKER, FRIDAY_MARKER));
        this.assignedMarkers = new ArrayList<>();
    }

    public Action(List<MarkerType> extraMarkers) {

        this.allowedMarkers = new ArrayList<>(Arrays.asList(CARPENTER_MARKER, COOK_MARKER, EXPLORER_MARKER, SOLDIER_MARKER, FRIDAY_MARKER));
        this.allowedMarkers.addAll(extraMarkers);
        this.assignedMarkers = new ArrayList<>();
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
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

    public void runAction() {

    }
}
