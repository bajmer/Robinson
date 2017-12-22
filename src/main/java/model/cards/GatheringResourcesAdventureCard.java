package model.cards;

import model.enums.cards.GatheringResourcesAdventureType;

public class GatheringResourcesAdventureCard implements ICard {
    private GatheringResourcesAdventureType adventureType;

    public GatheringResourcesAdventureCard(GatheringResourcesAdventureType adventureType) {
        this.adventureType = adventureType;
    }

    @Override
    public void useCard() {

    }
}
