package model.cards;

import model.enums.cards.InventionType;

public class InventionCard implements Usable {
    private InventionType invention;
    private boolean isMandatory;
    private boolean isIdea;

    public InventionCard(InventionType invention, boolean isMandatory) {
        this.invention = invention;
        this.isMandatory = isMandatory;
        this.isIdea = false;
    }

    public InventionType getInvention() {
        return invention;
    }

    public void setInvention(InventionType invention) {
        this.invention = invention;
    }

    public boolean isMandatory() {
        return isMandatory;
    }

    public void setMandatory(boolean mandatory) {
        isMandatory = mandatory;
    }

    public boolean isIdea() {
        return isIdea;
    }

    public void setIdea(boolean idea) {
        isIdea = idea;
    }

    @Override
    public void use() {

    }

    @Override
    public String toString() {
        return "InventionCard{" +
                "invention=" + invention +
                '}';
    }
}
