package model.cards;

import model.enums.cards.EventEffectType;
import model.enums.cards.ExplorationAdventureType;

public class ExplorationAdventureCard implements ICard {
    private ExplorationAdventureType adventureType;
    private EventEffectType effectType;

    public ExplorationAdventureCard(ExplorationAdventureType adventureType, EventEffectType effectType) {
        this.adventureType = adventureType;
        this.effectType = effectType;
    }

    @Override
    public void useCard() {

    }
}
