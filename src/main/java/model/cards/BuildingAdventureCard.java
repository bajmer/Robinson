package model.cards;

import model.enums.cards.adventurecards.AdventureEventEffectType;
import model.enums.cards.adventurecards.BuildingAdventureType;

public class BuildingAdventureCard implements ICard {
    private BuildingAdventureType adventureType;
    private AdventureEventEffectType effectType;

    public BuildingAdventureCard(BuildingAdventureType adventureType, AdventureEventEffectType effectType) {
        this.adventureType = adventureType;
        this.effectType = effectType;
    }

    @Override
    public void useCard() {

    }
}
