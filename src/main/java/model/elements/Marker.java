package model.elements;

import model.ICharacter;
import model.enums.elements.MarkerType;

public class Marker {
    private MarkerType markerType;
    private ICharacter character;
    private boolean isAvaible;

    public Marker(MarkerType markerType, ICharacter character) {
        this.markerType = markerType;
        this.character = character;
        this.isAvaible = true;
    }

    public boolean isAvaible() {
        return isAvaible;
    }

    public void setAvaible(boolean avaible) {
        isAvaible = avaible;
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
