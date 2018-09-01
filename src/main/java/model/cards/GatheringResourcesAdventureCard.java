package model.cards;

import model.enums.cards.adventurecards.AdventureEventEffectType;
import model.enums.cards.adventurecards.GatheringResourcesAdventureType;

public class GatheringResourcesAdventureCard {
    private GatheringResourcesAdventureType adventureType;
    private AdventureEventEffectType effectType;

    public GatheringResourcesAdventureCard(GatheringResourcesAdventureType adventureType, AdventureEventEffectType effectType) {
        this.adventureType = adventureType;
        this.effectType = effectType;
    }
}
