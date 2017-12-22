package model.cards;

import model.enums.cards.ExplorationAdventureType;

public class ExplorationAdventureCard implements ICard {
    private ExplorationAdventureType adventureType;

    public ExplorationAdventureCard(ExplorationAdventureType adventureType) {
        this.adventureType = adventureType;
    }

    @Override
    public void useCard() {

    }
}
