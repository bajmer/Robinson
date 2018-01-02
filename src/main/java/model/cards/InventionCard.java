package model.cards;

import model.enums.ProfessionType;
import model.enums.cards.InventionType;

public class InventionCard implements Usable {
    private InventionType invention;
    private boolean isMandatory;
    private boolean isIdea;
    private ProfessionType owner;

    public InventionCard(InventionType invention, boolean isMandatory, ProfessionType owner) {
        this.invention = invention;
        this.isMandatory = isMandatory;
        this.owner = owner;

        this.isIdea = true;
    }

    public ProfessionType getOwner() {
        return owner;
    }

    public void setOwner(ProfessionType owner) {
        this.owner = owner;
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
