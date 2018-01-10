package model.elements;

import model.enums.elements.MarkerType;

public class Marker {
    private MarkerType markerType;

    public Marker(MarkerType markerType) {
        this.markerType = markerType;
    }

    public MarkerType getMarkerType() {
        return markerType;
    }

    public void setMarkerType(MarkerType markerType) {
        this.markerType = markerType;
    }
}
