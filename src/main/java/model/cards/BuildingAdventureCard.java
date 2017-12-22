package model.cards;

import model.enums.cards.BuildingAdventureType;

public class BuildingAdventureCard implements ICard {
    private BuildingAdventureType adventureType;

    public BuildingAdventureCard(BuildingAdventureType adventureType) {
        this.adventureType = adventureType;
    }

    @Override
    public void useCard() {

    }
}
