package model.cards;

import model.enums.cards.adventurecards.AdventureEventEffectType;
import model.enums.cards.adventurecards.ExplorationAdventureType;

public class ExplorationAdventureCard implements ICard {
    private ExplorationAdventureType adventureType;
    private AdventureEventEffectType effectType;

    public ExplorationAdventureCard(ExplorationAdventureType adventureType, AdventureEventEffectType effectType) {
        this.adventureType = adventureType;
        this.effectType = effectType;
    }

    @Override
    public void useCard() {

    }
}
