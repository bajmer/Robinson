package model.cards;

import model.enums.cards.adventurecards.AdventureEventEffectType;
import model.enums.cards.adventurecards.ExplorationAdventureType;

public class ExplorationAdventureCard implements Usable {
    private ExplorationAdventureType adventureType;
    private AdventureEventEffectType effectType;

    public ExplorationAdventureCard(ExplorationAdventureType adventureType, AdventureEventEffectType effectType) {
        this.adventureType = adventureType;
        this.effectType = effectType;
    }

    @Override
    public void use() {

    }
}
