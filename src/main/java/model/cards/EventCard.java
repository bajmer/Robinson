package model.cards;

import model.ActionRequirements;
import model.Mappings;
import model.elements.Marker;
import model.enums.cards.eventcards.EventEffectType;
import model.enums.cards.eventcards.EventIconType;
import model.enums.cards.eventcards.ThreatActionType;
import model.enums.cards.eventcards.ThreatEffectType;

import java.util.List;

public class EventCard implements RequirableCard {
    private EventEffectType eventEffect;
    private EventIconType eventIcon;
    private ThreatActionType threatAction;
    private ThreatEffectType threatEffect;
    private ActionRequirements actionRequirements;
    private boolean availableToTakeThreatAction;

    public EventCard(EventEffectType eventEffect, EventIconType eventIcon, ThreatActionType threatAction, ThreatEffectType threatEffect) {
        this.eventEffect = eventEffect;
        this.eventIcon = eventIcon;
        this.threatAction = threatAction;
        this.threatEffect = threatEffect;

        actionRequirements = new ActionRequirements(
                Mappings.getThreatActionToRequiredTerrainsMapping().get(threatAction),
                Mappings.getThreatActionToRequiredInventionsMapping().get(threatAction),
                Mappings.getThreatActionToRequiredWoodMapping().get(threatAction),
                Mappings.getThreatActionToRequiredHidesMapping().get(threatAction)
        );
        this.availableToTakeThreatAction = false;
    }

    public EventEffectType getEventEffect() {
        return eventEffect;
    }

    public void setEventEffect(EventEffectType eventEffect) {
        this.eventEffect = eventEffect;
    }

    public EventIconType getEventIcon() {
        return eventIcon;
    }

    public void setEventIcon(EventIconType eventIcon) {
        this.eventIcon = eventIcon;
    }

    public ThreatActionType getThreatAction() {
        return threatAction;
    }

    public void setThreatAction(ThreatActionType threatAction) {
        this.threatAction = threatAction;
    }

    public ThreatEffectType getThreatEffect() {
        return threatEffect;
    }

    public void setThreatEffect(ThreatEffectType threatEffect) {
        this.threatEffect = threatEffect;
    }

    public ActionRequirements getActionRequirements() {
        return actionRequirements;
    }

    public void setActionRequirements(ActionRequirements actionRequirements) {
        this.actionRequirements = actionRequirements;
    }

    public boolean isAvailableToTakeThreatAction() {
        return availableToTakeThreatAction;
    }

    public void setAvailableToTakeThreatAction(boolean availableToTakeThreatAction) {
        this.availableToTakeThreatAction = availableToTakeThreatAction;
    }

    public void runEventEffect() {
        switch (eventEffect) {
            case FOOD_CRATES:
                break;
            case WRECKED_LIFEBOAT:
                break;
            case CAPTAINS_CHEST:
                break;
            case WINTER_DEPRESSION:
                break;
            case CHRONIC_TIREDNESS:
                break;
            case HIGH_WATER:
                break;
            case FROST:
                break;
            case WRECKED_BALLOON:
                break;
            case PREDATORS_IN_THE_VICINITY:
                break;
            case NIGHT_HOWLING:
                break;
            case POISONING:
                break;
            case CULLED_AREA:
                break;
            case BODY_ON_THE_BEACH:
                break;
            case COLD_RAIN_1:
                break;
            case COLD_RAIN_2:
                break;
            case MEMORIES_OF_THE_CRUISE:
                break;
            case THUNDERSTORM:
                break;
            case PREDATORS:
                break;
            case CALL_OF_THE_WILD:
                break;
            case EARTHQUAKE:
                break;
            case DESTRUCTIVE_HURRICANE:
                break;
            case LANDSLIDE:
                break;
            case BAD_FEELINGS:
                break;
            case MESS_IN_THE_CAMP:
                break;
            case THE_ISLAND_REBELS_AGAINST_YOU:
                break;
            case NATURAL_DAM_BREAKS:
                break;
            case RAGING_RIVER:
                break;
            case FLOOD:
                break;
            case VERTIGO:
                break;
            case HEAVY_RAIN:
                break;
            case WEAKNESS:
                break;
            case SLOW_WORK:
                break;
            case RAIN:
                break;
            case PREDATORS_IN_THE_WOODS:
                break;
            case BAD_FATE:
                break;
            case BEAR:
                break;
            case CATACLYSM:
                break;
            case SEARCHING_FOR_A_NEW_PATH:
                break;
            case FIGHT:
                break;// (IF IT HAS THE ? SYMBOL)
            case ARGUMENT:
                break;// (IF IT HAS BOOK SYMBOL)
            case CLOUD_BURST:
                break;
            case HEAVY_CLOUDS:
                break;
            case RAVAGING_HURRICANE:
                break;
            case RAVISHING_WINDSTORM:
                break;
            case FIRE:
                break;
            case SLEEPLESS_NIGHT:
                break;
            case PREDATOR_IS_NEAR:
                break;
            case STORM_1:
                break;
            case STORM_2:
                break;
            case EXHAUSTING_NIGHT:
                break;
            case BROKEN_TREE:
                break;
            case STRONG_WIND:
                break;
            case WEATHER_BREAKDOWN:
                break;
            case MIST:
                break;
            case DEPRESSION:
                break;
            case DISASTER:
                break;
            case FLYING_SURPRISE:
                break;
            case DEVASTATING_BLOWS:
                break;
            case LACK_OF_HOPE:
                break;
            case LOSS_OF_HOPE:
                break;
            case FIGHT_IN_THE_DARK:
                break;
            case DANGEROUS_NIGHT:
                break;
            case NIGHT_ATTACK:
                break;
            case HOWLING_FROM_THE_WOODS:
                break;
            case SMOKE_ON_THE_HORIZON:
                break;
            case DROUGHT:
                break;
            case ROUGH_PASSAGE:
                break;
            case UNUSUALLY_COLD_NIGHT:
                break;
            case COUNCIL:
                break;
            case INSECTS:
                break;
            case HEAVY_RAIN_IS_COMING:
                break;
            case AWFUL_WEATHER:
                break;
            case PRECIPICE:
                break;
            case JAGUAR:
                break;
            case RAVENOUS_PREDATORS:
                break;
            case OTTERS:
                break;
        }
    }

    //    todo Uzupełnić
    public void runEventIconEffect(int scenarioId) {
        if (eventIcon != null) {
            switch (eventIcon) {
                case BOOK:
                    handleBook(scenarioId);
                    break;
                case BUILDING_ADVENTURE:
                    //przygoda przy najbliższej budowie
                    break;
                case EXPLORATION_ADVENTURE:
                    //przygoda przy najbliższej eksploracji
                    break;
                case GATHERING_RESOURCES_ADVENTURE:
                    //przygoda przy najbliższym zbieraniu surowców
                    break;
            }
        }
    }

    private void handleBook(int scenarioId) {
        switch (scenarioId) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            default:
                break;
        }
    }

    // todo Uzupełnić
    public void runThreatAction(List<Marker> markers) {
        int markersNumber = markers.size();
        switch (threatAction) {
            case JAKAS_AKCJA_ZAGROZENIE:
                break;
        }
    }

    // todo Uzupełnić
    public void runThreatEffect() {
        switch (threatEffect) {
            case THREAT_EFFECT:
                break;
        }
    }
}
