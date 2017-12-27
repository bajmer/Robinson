package model.cards;

import model.enums.cards.EventEffectType;
import model.enums.cards.GatheringResourcesAdventureType;

public class GatheringResourcesAdventureCard implements ICard {
    private GatheringResourcesAdventureType adventureType;
    private EventEffectType effectType;

    public GatheringResourcesAdventureCard(GatheringResourcesAdventureType adventureType, EventEffectType effectType) {
        this.adventureType = adventureType;
        this.effectType = effectType;
    }

    @Override
    public void useCard() {

    }
}
