package model.cards;

import model.ActionRequirements;
import model.Mappings;
import model.enums.ProfessionType;
import model.enums.cards.InventionType;

public class InventionCard implements RequirableCard {
    private InventionType invention;
    private boolean isMandatory;
    private boolean isIdea;
    private ProfessionType owner;
    private boolean multipleIdea;
    private ActionRequirements actionRequirements;
    private boolean availableToBuild;

    public InventionCard(InventionType invention, boolean isMandatory, ProfessionType owner, boolean multipleIdea) {
        this.invention = invention;
        this.isMandatory = isMandatory;
        this.owner = owner;
        this.multipleIdea = multipleIdea;
        this.isIdea = true;

        actionRequirements = new ActionRequirements(
                Mappings.getInventionToRequiredTerrainsMapping().get(invention),
                Mappings.getInventionToRequiredInventionsMapping().get(invention),
                Mappings.getInventionToRequiredWoodMapping().get(invention),
                Mappings.getInventionToRequiredHidesMapping().get(invention)
        );
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

    public ActionRequirements getActionRequirements() {
        return actionRequirements;
    }

    public void setActionRequirements(ActionRequirements actionRequirements) {
        this.actionRequirements = actionRequirements;
    }

    public boolean isAvailableToBuild() {
        return availableToBuild;
    }

    public void setAvailableToBuild(boolean availableToBuild) {
        this.availableToBuild = availableToBuild;
    }

    @Override
    public String toString() {
        return "InventionCard{" +
                "invention=" + invention +
                '}';
    }
}
