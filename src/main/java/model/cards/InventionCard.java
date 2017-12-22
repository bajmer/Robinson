package model.cards;

import model.enums.cards.InventionType;

public class InventionCard implements ICard {
    private InventionType invention;
    private boolean isMandatory;
    private boolean isIdea;

    public InventionCard(InventionType invention) {
        this.invention = invention;
        this.isIdea = false;
        isMandatory = invention == InventionType.BOW
                || invention == InventionType.BRICKS
                || invention == InventionType.DAM
                || invention == InventionType.FIRE
                || invention == InventionType.KNIFE
                || invention == InventionType.MAP
                || invention == InventionType.POT
                || invention == InventionType.ROPE
                || invention == InventionType.SHOVEL;
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
    public void useCard() {

    }

    @Override
    public String toString() {
        return "InventionCard{" +
                "invention=" + invention +
                '}';
    }
}
