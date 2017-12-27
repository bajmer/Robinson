package model.cards;

import model.enums.cards.BuildingAdventureType;
import model.enums.cards.EventEffectType;

public class BuildingAdventureCard implements ICard {
    private BuildingAdventureType adventureType;
    private EventEffectType effectType;

    public BuildingAdventureCard(BuildingAdventureType adventureType, EventEffectType effectType) {
        this.adventureType = adventureType;
        this.effectType = effectType;
    }

    @Override
    public void useCard() {

    }
}
