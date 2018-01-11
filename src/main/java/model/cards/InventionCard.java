package model.cards;

import model.enums.ProfessionType;
import model.enums.TerrainType;
import model.enums.cards.InventionType;

public class InventionCard implements Usable {
    private InventionType invention;
    private boolean isMandatory;
    private boolean isIdea;
    private ProfessionType owner;
    private boolean multipleIdea;
    private TerrainType requiredTerrain;
    private InventionCard requiredItem;
    private int requiredWoods;
    private int requiredHides;

    public InventionCard(InventionType invention, boolean isMandatory, ProfessionType owner, boolean multipleIdea) {
        this.invention = invention;
        this.isMandatory = isMandatory;
        this.owner = owner;
        this.multipleIdea = multipleIdea;

        this.isIdea = true;
    }

    public boolean isMultipleIdea() {
        return multipleIdea;
    }

    public void setMultipleIdea(boolean multipleIdea) {
        this.multipleIdea = multipleIdea;
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
