package model.elements;

import model.ICharacter;
import model.enums.elements.MarkerType;

public class Marker {
    private MarkerType markerType;
    private ICharacter character;
    private boolean isAvailable;

    public Marker(MarkerType markerType, ICharacter character) {
        this.markerType = markerType;
        this.character = character;
        this.isAvailable = true;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public ICharacter getCharacter() {
        return character;
    }

    public void setCharacter(ICharacter character) {
        this.character = character;
    }

    public MarkerType getMarkerType() {
        return markerType;
    }

    public void setMarkerType(MarkerType markerType) {
        this.markerType = markerType;
    }
}
