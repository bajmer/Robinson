package model;

import model.enums.ProfessionType;
import model.enums.SpecialSkillType;
import model.enums.cards.BeastType;
import model.enums.cards.InventionType;
import model.enums.cards.adventurecards.AdventureEventEffectType;
import model.enums.cards.adventurecards.BuildingAdventureType;
import model.enums.cards.adventurecards.ExplorationAdventureType;
import model.enums.cards.adventurecards.GatheringResourcesAdventureType;
import model.enums.cards.eventcards.EventEffectType;
import model.enums.cards.eventcards.EventIconType;
import model.enums.cards.eventcards.ThreatActionType;
import model.enums.cards.eventcards.ThreatEffectType;
import model.enums.cards.wreckagecards.WreckageEventEffectType;
import model.enums.cards.wreckagecards.WreckageThreatActionType;
import model.enums.cards.wreckagecards.WreckageThreatEffectType;

import java.util.*;

import static model.enums.ProfessionType.*;
import static model.enums.SpecialSkillType.*;
import static model.enums.cards.BeastType.*;
import static model.enums.cards.InventionType.*;
import static model.enums.cards.adventurecards.AdventureEventEffectType.SOME_ADVENTURE_EVENT_EFFECT;
import static model.enums.cards.adventurecards.BuildingAdventureType.*;
import static model.enums.cards.adventurecards.ExplorationAdventureType.*;
import static model.enums.cards.adventurecards.GatheringResourcesAdventureType.*;
import static model.enums.cards.eventcards.EventEffectType.*;
import static model.enums.cards.eventcards.EventIconType.*;
import static model.enums.cards.eventcards.ThreatActionType.THREAT_ACTION;
import static model.enums.cards.eventcards.ThreatEffectType.THREAT_EFFECT;
import static model.enums.cards.wreckagecards.WreckageEventEffectType.*;
import static model.enums.cards.wreckagecards.WreckageThreatActionType.SOME_WRECKAGE_ACTION;
import static model.enums.cards.wreckagecards.WreckageThreatEffectType.SOME_WRECKAGE_THREAT_EFFECT;

public class Mappings {
    private static Map<EventEffectType, EventIconType> eventEffectToEventIconMapping = new HashMap<>();
    private static Map<EventEffectType, ThreatActionType> eventEffectToThreatActionMapping = new HashMap<>();
    private static Map<EventEffectType, ThreatEffectType> eventEffectToThreatEffectMapping = new HashMap<>();
    private static Map<BuildingAdventureType, AdventureEventEffectType> buildingAdvntureToAdventureEventEffectMapping = new HashMap<>();
    private static Map<GatheringResourcesAdventureType, AdventureEventEffectType> gatheringAdventureToAdventureEventEffectMapping = new HashMap<>();
    private static Map<ExplorationAdventureType, AdventureEventEffectType> explorationAdventureToAdventureEventEffectMapping = new HashMap<>();
    private static Map<BeastType, List<Integer>> beastToBeastStatsMapping = new HashMap<>();
    private static Map<Integer, Integer> scenarioIdToRoundsNumberMapping = new HashMap<>();
    private static Map<InventionType, Boolean> inventionTypeToIsMandatoryMapping = new HashMap<>();
    private static Map<ProfessionType, List<SpecialSkillType>> professionToSpecialSkillMapping = new HashMap<>();
    private static Map<ProfessionType, InventionType> professionToPersonalInventionMapping = new HashMap<>();
    private static Map<ProfessionType, List<Integer>> professionToMoraleDownMapping = new HashMap<>();
    private static Map<ProfessionType, Integer> professionToLifeMapping = new HashMap<>();
    private static Map<WreckageEventEffectType, WreckageThreatActionType> wreckageEventToWreckageThreatActionMapping = new HashMap<>();
    private static Map<WreckageEventEffectType, WreckageThreatEffectType> wreckageEventToWreckageThreatEffectMapping = new HashMap<>();

    public Mappings() {
        eventEffectToEventIconMapping.put(WINTER_DEPRESSION, BOOK);
        eventEffectToEventIconMapping.put(CHRONIC_TIREDNESS, BOOK);
        eventEffectToEventIconMapping.put(HIGH_WATER, BOOK);
        eventEffectToEventIconMapping.put(FROST, BOOK);
        eventEffectToEventIconMapping.put(WRECKED_BALLOON, BOOK);
        eventEffectToEventIconMapping.put(PREDATORS_IN_THE_VICINITY, BOOK);
        eventEffectToEventIconMapping.put(NIGHT_HOWLING, BOOK);
        eventEffectToEventIconMapping.put(POISONING, BOOK);
        eventEffectToEventIconMapping.put(CULLED_AREA, BOOK);
        eventEffectToEventIconMapping.put(BODY_ON_THE_BEACH, BOOK);
        eventEffectToEventIconMapping.put(COLD_RAIN_1, BOOK);
        eventEffectToEventIconMapping.put(COLD_RAIN_2, BOOK);
        eventEffectToEventIconMapping.put(MEMORIES_OF_THE_CRUISE, BUILDING_ADVENTURE);
        eventEffectToEventIconMapping.put(THUNDERSTORM, BUILDING_ADVENTURE);
        eventEffectToEventIconMapping.put(PREDATORS, BUILDING_ADVENTURE);
        eventEffectToEventIconMapping.put(CALL_OF_THE_WILD, BOOK);
        eventEffectToEventIconMapping.put(EARTHQUAKE, BOOK);
        eventEffectToEventIconMapping.put(DESTRUCTIVE_HURRICANE, GATHERING_RESOURCES_ADVENTURE);
        eventEffectToEventIconMapping.put(LANDSLIDE, BUILDING_ADVENTURE);
        eventEffectToEventIconMapping.put(BAD_FEELINGS, EXPLORATION_ADVENTURE);
        eventEffectToEventIconMapping.put(MESS_IN_THE_CAMP, BUILDING_ADVENTURE);
        eventEffectToEventIconMapping.put(THE_ISLAND_REBELS_AGAINST_YOU, BOOK);
        eventEffectToEventIconMapping.put(NATURAL_DAM_BREAKS, GATHERING_RESOURCES_ADVENTURE);
        eventEffectToEventIconMapping.put(RAGING_RIVER, BOOK);
        eventEffectToEventIconMapping.put(FLOOD, BOOK);
        eventEffectToEventIconMapping.put(VERTIGO, BOOK);
        eventEffectToEventIconMapping.put(HEAVY_RAIN, BOOK);
        eventEffectToEventIconMapping.put(WEAKNESS, GATHERING_RESOURCES_ADVENTURE);
        eventEffectToEventIconMapping.put(SLOW_WORK, BOOK);
        eventEffectToEventIconMapping.put(RAIN, EXPLORATION_ADVENTURE);
        eventEffectToEventIconMapping.put(PREDATORS_IN_THE_WOODS, BOOK);
        eventEffectToEventIconMapping.put(BAD_FATE, GATHERING_RESOURCES_ADVENTURE);
        eventEffectToEventIconMapping.put(EventEffectType.BEAR, BOOK);
        eventEffectToEventIconMapping.put(CATACLYSM, BUILDING_ADVENTURE);
        eventEffectToEventIconMapping.put(SEARCHING_FOR_A_NEW_PATH, BOOK);
        eventEffectToEventIconMapping.put(FIGHT, EXPLORATION_ADVENTURE);
        eventEffectToEventIconMapping.put(ARGUMENT, BOOK);
        eventEffectToEventIconMapping.put(CLOUD_BURST, BUILDING_ADVENTURE);
        eventEffectToEventIconMapping.put(HEAVY_CLOUDS, BOOK);
        eventEffectToEventIconMapping.put(RAVAGING_HURRICANE, GATHERING_RESOURCES_ADVENTURE);
        eventEffectToEventIconMapping.put(RAVISHING_WINDSTORM, BUILDING_ADVENTURE);
        eventEffectToEventIconMapping.put(EventEffectType.FIRE, BOOK);
        eventEffectToEventIconMapping.put(SLEEPLESS_NIGHT, GATHERING_RESOURCES_ADVENTURE);
        eventEffectToEventIconMapping.put(PREDATOR_IS_NEAR, BOOK);
        eventEffectToEventIconMapping.put(STORM_1, BOOK);
        eventEffectToEventIconMapping.put(STORM_2, EXPLORATION_ADVENTURE);
        eventEffectToEventIconMapping.put(EXHAUSTING_NIGHT, GATHERING_RESOURCES_ADVENTURE);
        eventEffectToEventIconMapping.put(BROKEN_TREE, EXPLORATION_ADVENTURE);
        eventEffectToEventIconMapping.put(STRONG_WIND, BOOK);
        eventEffectToEventIconMapping.put(EventEffectType.WEATHER_BREAKDOWN, EXPLORATION_ADVENTURE);
        eventEffectToEventIconMapping.put(MIST, EXPLORATION_ADVENTURE);
        eventEffectToEventIconMapping.put(DEPRESSION, BOOK);
        eventEffectToEventIconMapping.put(DISASTER, BUILDING_ADVENTURE);
        eventEffectToEventIconMapping.put(FLYING_SURPRISE, BOOK);
        eventEffectToEventIconMapping.put(DEVASTATING_BLOWS, GATHERING_RESOURCES_ADVENTURE);
        eventEffectToEventIconMapping.put(EventEffectType.LACK_OF_HOPE, BOOK);
        eventEffectToEventIconMapping.put(LOSS_OF_HOPE, EXPLORATION_ADVENTURE);
        eventEffectToEventIconMapping.put(FIGHT_IN_THE_DARK, BUILDING_ADVENTURE);
        eventEffectToEventIconMapping.put(DANGEROUS_NIGHT, BOOK);
        eventEffectToEventIconMapping.put(NIGHT_ATTACK, BUILDING_ADVENTURE);
        eventEffectToEventIconMapping.put(HOWLING_FROM_THE_WOODS, GATHERING_RESOURCES_ADVENTURE);
        eventEffectToEventIconMapping.put(SMOKE_ON_THE_HORIZON, BOOK);
        eventEffectToEventIconMapping.put(DROUGHT, EXPLORATION_ADVENTURE);
        eventEffectToEventIconMapping.put(ROUGH_PASSAGE, GATHERING_RESOURCES_ADVENTURE);
        eventEffectToEventIconMapping.put(UNUSUALLY_COLD_NIGHT, BOOK);
        eventEffectToEventIconMapping.put(COUNCIL, BUILDING_ADVENTURE);
        eventEffectToEventIconMapping.put(INSECTS, BOOK);
        eventEffectToEventIconMapping.put(HEAVY_RAIN_IS_COMING, EXPLORATION_ADVENTURE);
        eventEffectToEventIconMapping.put(AWFUL_WEATHER, EXPLORATION_ADVENTURE);
        eventEffectToEventIconMapping.put(PRECIPICE, BOOK);
        eventEffectToEventIconMapping.put(EventEffectType.JAGUAR, BUILDING_ADVENTURE);
        eventEffectToEventIconMapping.put(RAVENOUS_PREDATORS, BOOK);
        eventEffectToEventIconMapping.put(OTTERS, GATHERING_RESOURCES_ADVENTURE);

        eventEffectToThreatActionMapping.put(WINTER_DEPRESSION, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(CHRONIC_TIREDNESS, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(HIGH_WATER, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(FROST, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(WRECKED_BALLOON, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(PREDATORS_IN_THE_VICINITY, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(NIGHT_HOWLING, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(POISONING, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(CULLED_AREA, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(BODY_ON_THE_BEACH, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(COLD_RAIN_1, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(COLD_RAIN_2, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(MEMORIES_OF_THE_CRUISE, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(THUNDERSTORM, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(PREDATORS, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(CALL_OF_THE_WILD, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(EARTHQUAKE, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(DESTRUCTIVE_HURRICANE, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(LANDSLIDE, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(BAD_FEELINGS, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(MESS_IN_THE_CAMP, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(THE_ISLAND_REBELS_AGAINST_YOU, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(NATURAL_DAM_BREAKS, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(RAGING_RIVER, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(FLOOD, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(VERTIGO, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(HEAVY_RAIN, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(WEAKNESS, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(SLOW_WORK, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(RAIN, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(PREDATORS_IN_THE_WOODS, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(BAD_FATE, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(EventEffectType.BEAR, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(CATACLYSM, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(SEARCHING_FOR_A_NEW_PATH, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(FIGHT, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(ARGUMENT, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(CLOUD_BURST, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(HEAVY_CLOUDS, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(RAVAGING_HURRICANE, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(RAVISHING_WINDSTORM, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(EventEffectType.FIRE, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(SLEEPLESS_NIGHT, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(PREDATOR_IS_NEAR, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(STORM_1, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(STORM_2, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(EXHAUSTING_NIGHT, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(BROKEN_TREE, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(STRONG_WIND, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(EventEffectType.WEATHER_BREAKDOWN, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(MIST, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(DEPRESSION, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(DISASTER, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(FLYING_SURPRISE, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(DEVASTATING_BLOWS, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(EventEffectType.LACK_OF_HOPE, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(LOSS_OF_HOPE, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(FIGHT_IN_THE_DARK, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(DANGEROUS_NIGHT, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(NIGHT_ATTACK, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(HOWLING_FROM_THE_WOODS, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(SMOKE_ON_THE_HORIZON, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(DROUGHT, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(ROUGH_PASSAGE, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(UNUSUALLY_COLD_NIGHT, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(COUNCIL, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(INSECTS, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(HEAVY_RAIN_IS_COMING, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(AWFUL_WEATHER, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(PRECIPICE, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(EventEffectType.JAGUAR, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(RAVENOUS_PREDATORS, THREAT_ACTION);
        eventEffectToThreatActionMapping.put(OTTERS, THREAT_ACTION);

        eventEffectToThreatEffectMapping.put(WINTER_DEPRESSION, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(CHRONIC_TIREDNESS, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(HIGH_WATER, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(FROST, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(WRECKED_BALLOON, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(PREDATORS_IN_THE_VICINITY, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(NIGHT_HOWLING, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(POISONING, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(CULLED_AREA, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(BODY_ON_THE_BEACH, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(COLD_RAIN_1, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(COLD_RAIN_2, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(MEMORIES_OF_THE_CRUISE, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(THUNDERSTORM, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(PREDATORS, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(CALL_OF_THE_WILD, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(EARTHQUAKE, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(DESTRUCTIVE_HURRICANE, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(LANDSLIDE, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(BAD_FEELINGS, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(MESS_IN_THE_CAMP, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(THE_ISLAND_REBELS_AGAINST_YOU, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(NATURAL_DAM_BREAKS, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(RAGING_RIVER, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(FLOOD, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(VERTIGO, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(HEAVY_RAIN, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(WEAKNESS, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(SLOW_WORK, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(RAIN, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(PREDATORS_IN_THE_WOODS, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(BAD_FATE, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(EventEffectType.BEAR, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(CATACLYSM, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(SEARCHING_FOR_A_NEW_PATH, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(FIGHT, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(ARGUMENT, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(CLOUD_BURST, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(HEAVY_CLOUDS, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(RAVAGING_HURRICANE, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(RAVISHING_WINDSTORM, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(EventEffectType.FIRE, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(SLEEPLESS_NIGHT, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(PREDATOR_IS_NEAR, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(STORM_1, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(STORM_2, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(EXHAUSTING_NIGHT, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(BROKEN_TREE, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(STRONG_WIND, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(EventEffectType.WEATHER_BREAKDOWN, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(MIST, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(DEPRESSION, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(DISASTER, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(FLYING_SURPRISE, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(DEVASTATING_BLOWS, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(EventEffectType.LACK_OF_HOPE, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(LOSS_OF_HOPE, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(FIGHT_IN_THE_DARK, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(DANGEROUS_NIGHT, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(NIGHT_ATTACK, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(HOWLING_FROM_THE_WOODS, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(SMOKE_ON_THE_HORIZON, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(DROUGHT, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(ROUGH_PASSAGE, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(UNUSUALLY_COLD_NIGHT, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(COUNCIL, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(INSECTS, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(HEAVY_RAIN_IS_COMING, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(AWFUL_WEATHER, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(PRECIPICE, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(EventEffectType.JAGUAR, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(RAVENOUS_PREDATORS, THREAT_EFFECT);
        eventEffectToThreatEffectMapping.put(OTTERS, THREAT_EFFECT);

        buildingAdvntureToAdventureEventEffectMapping.put(TOOL_BREAKS, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(LABORIOUS_WORK_1, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(LABORIOUS_WORK_2, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(CONSTRUCTION, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(UNMOTIVATED, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(FAST_WORK, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(STING, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(NASTY_WOUND, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(TIRED, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(IN_A_HURRY, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(DARK_CLOUDS_IN_THE_SKY, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(CONSTRUCTION_IS_WEAK, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(PREDATOR_IN_THE_CAMP, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(WIND_STORM, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(FEAR_OF_THE_BEASTS, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(BREAKDOWN, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(BROKEN_LEVER, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(DANGEROUS_WORK, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(BuildingAdventureType.LACK_OF_HOPE, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(HARD_WORK, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(COMING_TO_TERMS, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(CUT_HEAD, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(TOOLS_INSPECTION, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(MONKEYS_WATCH_YOU, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(SAVINGS, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(ACCIDENT, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(HOWLING_IN_THE_BUSHES, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(HUNGRY_PREDATOR, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(YOU_NEED_A_BIGGER_CAMP, SOME_ADVENTURE_EVENT_EFFECT);
        buildingAdvntureToAdventureEventEffectMapping.put(VISIT_OF_A_PREDATOR, SOME_ADVENTURE_EVENT_EFFECT);

        gatheringAdventureToAdventureEventEffectMapping.put(END_OF_SOURCE_1, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(END_OF_SOURCE_2, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(END_OF_SOURCE_3, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(END_OF_SOURCE_4, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(END_OF_SOURCE_5, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(WINTER_FREEZING, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(UNEXPECTED_DISCOVERY, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(FURS, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(SPIDER, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(SKELETON, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(SIGNS_OF_A_PREDATOR, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(PIRATES_CHEST, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(VIPER, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(UNBELIEVABLE_EFFORT, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(TWISTED_ANKLE, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(WINTER_IS_COMING, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(GatheringResourcesAdventureType.WEATHER_BREAKDOWN, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(UNEXPECTED_TROUBLES, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(TRACKS_OF_A_PREDATOR, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(SHORTAGE, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(MUSHROOMS, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(GOLD_COIN, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(FRUIT, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(EYES_IN_THE_BUSHES, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(AFTER_THE_HURRICANE, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(PATH_OF_A_PREDATOR, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(NICE_SURPRISE, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(NESTLINGS, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(NEW_FLOCK, SOME_ADVENTURE_EVENT_EFFECT);
        gatheringAdventureToAdventureEventEffectMapping.put(SURPRISE_IN_THE_BUSHES, SOME_ADVENTURE_EVENT_EFFECT);

        explorationAdventureToAdventureEventEffectMapping.put(WRONG_TRACK, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(SWAMP, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(SURPRISING_DISCOVERY, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(ITS_GOING_TO_RAIN, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(THERES_SOMETHING_IN_THE_AIR, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(BAMBOO, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(OLD_HUT, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(OLD_GRAVE, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(SIGNS_OF_FIRE_1, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(SHRINE, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(WILD_BERRIES, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(VIPERS, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(SECRET_CAVE, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(ExplorationAdventureType.PUMA, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(ExplorationAdventureType.TIGER, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(STORM_ON_THE_HORIZON, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(LOST, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(LOST_IN_THE_WOODS, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(LOST_IN_THE_THICKET, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(DANGEROUS_TERRAIN, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(FLU, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(EMPTY_FOREST, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(COLD_WIND, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(CARCASS, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(RUINED_HUT, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(REMAINS_OF_A_SETTLEMENT, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(THORNY_BUSH, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(MISADVENTURE, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(ExplorationAdventureType.WILD_DOG, SOME_ADVENTURE_EVENT_EFFECT);
        explorationAdventureToAdventureEventEffectMapping.put(SIGNS_OF_FIRE_2, SOME_ADVENTURE_EVENT_EFFECT);

        beastToBeastStatsMapping.put(ALLIGATOR, new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        beastToBeastStatsMapping.put(BeastType.BEAR, new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        beastToBeastStatsMapping.put(BIRDS, new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        beastToBeastStatsMapping.put(BOA, new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        beastToBeastStatsMapping.put(CHAMOIS, new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        beastToBeastStatsMapping.put(CHEETAH, new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        beastToBeastStatsMapping.put(FOX, new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        beastToBeastStatsMapping.put(GOATS, new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        beastToBeastStatsMapping.put(GORILLA, new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        beastToBeastStatsMapping.put(IGUANA, new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        beastToBeastStatsMapping.put(BeastType.JAGUAR, new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        beastToBeastStatsMapping.put(BeastType.PUMA, new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        beastToBeastStatsMapping.put(TAPIR, new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        beastToBeastStatsMapping.put(BeastType.TIGER, new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        beastToBeastStatsMapping.put(BeastType.WILD_DOG, new ArrayList<>(Arrays.asList(0, 0, 0, 0)));
        beastToBeastStatsMapping.put(WILD_PIG, new ArrayList<>(Arrays.asList(0, 0, 0, 0)));

        scenarioIdToRoundsNumberMapping.put(1, 12);
        scenarioIdToRoundsNumberMapping.put(2, 10);
        scenarioIdToRoundsNumberMapping.put(3, 12);
        scenarioIdToRoundsNumberMapping.put(4, 12);
        scenarioIdToRoundsNumberMapping.put(5, 12);
        scenarioIdToRoundsNumberMapping.put(6, 12);
        scenarioIdToRoundsNumberMapping.put(7, 12);

        inventionTypeToIsMandatoryMapping.put(BOW, true);
        inventionTypeToIsMandatoryMapping.put(BRICKS, true);
        inventionTypeToIsMandatoryMapping.put(DAM, true);
        inventionTypeToIsMandatoryMapping.put(InventionType.FIRE, true);
        inventionTypeToIsMandatoryMapping.put(KNIFE, true);
        inventionTypeToIsMandatoryMapping.put(MAP, true);
        inventionTypeToIsMandatoryMapping.put(POT, true);
        inventionTypeToIsMandatoryMapping.put(ROPE, true);
        inventionTypeToIsMandatoryMapping.put(SHOVEL, true);
        inventionTypeToIsMandatoryMapping.put(BASKET, false);
        inventionTypeToIsMandatoryMapping.put(BED, false);
        inventionTypeToIsMandatoryMapping.put(BELTS, false);
        inventionTypeToIsMandatoryMapping.put(CELLAR, false);
        inventionTypeToIsMandatoryMapping.put(CORRAL, false);
        inventionTypeToIsMandatoryMapping.put(CURE, false);
        inventionTypeToIsMandatoryMapping.put(DIARY, false);
        inventionTypeToIsMandatoryMapping.put(DRUMS, false);
        inventionTypeToIsMandatoryMapping.put(FIREPLACE, false);
        inventionTypeToIsMandatoryMapping.put(FURNACE, false);
        inventionTypeToIsMandatoryMapping.put(LANTERN, false);
        inventionTypeToIsMandatoryMapping.put(MOAT, false);
        inventionTypeToIsMandatoryMapping.put(PIT, false);
        inventionTypeToIsMandatoryMapping.put(RAFT, false);
        inventionTypeToIsMandatoryMapping.put(SACK, false);
        inventionTypeToIsMandatoryMapping.put(SHIELD, false);
        inventionTypeToIsMandatoryMapping.put(SHORTCUT, false);
        inventionTypeToIsMandatoryMapping.put(SLING, false);
        inventionTypeToIsMandatoryMapping.put(SNARE, false);
        inventionTypeToIsMandatoryMapping.put(SPEAR, false);
        inventionTypeToIsMandatoryMapping.put(WALL, false);

        professionToPersonalInventionMapping.put(CARPENTER, SNARE);
        professionToPersonalInventionMapping.put(COOK, FIREPLACE);
        professionToPersonalInventionMapping.put(EXPLORER, SHORTCUT);
        professionToPersonalInventionMapping.put(SOLDIER, SPEAR);

        professionToSpecialSkillMapping.put(CARPENTER, new ArrayList<>(Arrays.asList(
                ECONOMICAL_CONSTRUCTION, CRAFT, NEW_IDEA, HANDYMAN)));
        professionToSpecialSkillMapping.put(COOK, new ArrayList<>(Arrays.asList(
                GRANDMAS_RECIPE, SHARP_EYE, NAIL_SOUP, HOOCH)));
        professionToSpecialSkillMapping.put(EXPLORER, new ArrayList<>(Arrays.asList(
                LUCKY_MAN, RECONNAISSANCE, MOTIVATIONAL_SPEECH, SCOUT)));
        professionToSpecialSkillMapping.put(SOLDIER, new ArrayList<>(Arrays.asList(
                TRACKING, HOUNTING, FURY, EMERGENCY_PLAN)));

        professionToMoraleDownMapping.put(CARPENTER, new ArrayList<>(Arrays.asList(8, 5, 3)));
        professionToMoraleDownMapping.put(COOK, new ArrayList<>(Arrays.asList(9, 6, 4, 2)));
        professionToMoraleDownMapping.put(EXPLORER, new ArrayList<>(Arrays.asList(6, 1)));
        professionToMoraleDownMapping.put(SOLDIER, new ArrayList<>(Arrays.asList(7, 3)));

        professionToLifeMapping.put(CARPENTER, 13);
        professionToLifeMapping.put(COOK, 13);
        professionToLifeMapping.put(EXPLORER, 12);
        professionToLifeMapping.put(SOLDIER, 12);

        wreckageEventToWreckageThreatActionMapping.put(FOOD_CRATES, SOME_WRECKAGE_ACTION);
        wreckageEventToWreckageThreatActionMapping.put(CAPTAINS_CHEST, SOME_WRECKAGE_ACTION);
        wreckageEventToWreckageThreatActionMapping.put(WRECKED_LIFEBOAT, SOME_WRECKAGE_ACTION);

        wreckageEventToWreckageThreatEffectMapping.put(FOOD_CRATES, SOME_WRECKAGE_THREAT_EFFECT);
        wreckageEventToWreckageThreatEffectMapping.put(CAPTAINS_CHEST, SOME_WRECKAGE_THREAT_EFFECT);
        wreckageEventToWreckageThreatEffectMapping.put(WRECKED_LIFEBOAT, SOME_WRECKAGE_THREAT_EFFECT);
    }

    public static Map<WreckageEventEffectType, WreckageThreatActionType> getWreckageEventToWreckageThreatActionMapping() {
        return wreckageEventToWreckageThreatActionMapping;
    }

    public static void setWreckageEventToWreckageThreatActionMapping(Map<WreckageEventEffectType, WreckageThreatActionType> wreckageEventToWreckageThreatActionMapping) {
        Mappings.wreckageEventToWreckageThreatActionMapping = wreckageEventToWreckageThreatActionMapping;
    }

    public static Map<WreckageEventEffectType, WreckageThreatEffectType> getWreckageEventToWreckageThreatEffectMapping() {
        return wreckageEventToWreckageThreatEffectMapping;
    }

    public static void setWreckageEventToWreckageThreatEffectMapping(Map<WreckageEventEffectType, WreckageThreatEffectType> wreckageEventToWreckageThreatEffectMapping) {
        Mappings.wreckageEventToWreckageThreatEffectMapping = wreckageEventToWreckageThreatEffectMapping;
    }

    public static Map<ProfessionType, Integer> getProfessionToLifeMapping() {
        return professionToLifeMapping;
    }

    public static void setProfessionToLifeMapping(Map<ProfessionType, Integer> professionToLifeMapping) {
        Mappings.professionToLifeMapping = professionToLifeMapping;
    }

    public static Map<ProfessionType, List<Integer>> getProfessionToMoraleDownMapping() {
        return professionToMoraleDownMapping;
    }

    public static void setProfessionToMoraleDownMapping(Map<ProfessionType, List<Integer>> professionToMoraleDownMapping) {
        Mappings.professionToMoraleDownMapping = professionToMoraleDownMapping;
    }

    public static Map<ProfessionType, InventionType> getProfessionToPersonalInventionMapping() {
        return professionToPersonalInventionMapping;
    }

    public static void setProfessionToPersonalInventionMapping(Map<ProfessionType, InventionType> professionToPersonalInventionMapping) {
        Mappings.professionToPersonalInventionMapping = professionToPersonalInventionMapping;
    }

    public static Map<ProfessionType, List<SpecialSkillType>> getProfessionToSpecialSkillMapping() {
        return professionToSpecialSkillMapping;
    }

    public static void setProfessionToSpecialSkillMapping(Map<ProfessionType, List<SpecialSkillType>> professionToSpecialSkillMapping) {
        Mappings.professionToSpecialSkillMapping = professionToSpecialSkillMapping;
    }

    public static Map<InventionType, Boolean> getInventionTypeToIsMandatoryMapping() {
        return inventionTypeToIsMandatoryMapping;
    }

    public static void setInventionTypeToIsMandatoryMapping(Map<InventionType, Boolean> inventionTypeToIsMandatoryMapping) {
        Mappings.inventionTypeToIsMandatoryMapping = inventionTypeToIsMandatoryMapping;
    }

    public static Map<Integer, Integer> getScenarioIdToRoundsNumberMapping() {
        return scenarioIdToRoundsNumberMapping;
    }

    public static void setScenarioIdToRoundsNumberMapping(Map<Integer, Integer> scenarioIdToRoundsNumberMapping) {
        Mappings.scenarioIdToRoundsNumberMapping = scenarioIdToRoundsNumberMapping;
    }

    public static Map<EventEffectType, EventIconType> getEventEffectToEventIconMapping() {
        return eventEffectToEventIconMapping;
    }

    public static void setEventEffectToEventIconMapping(Map<EventEffectType, EventIconType> eventEffectToEventIconMapping) {
        Mappings.eventEffectToEventIconMapping = eventEffectToEventIconMapping;
    }

    public static Map<EventEffectType, ThreatActionType> getEventEffectToThreatActionMapping() {
        return eventEffectToThreatActionMapping;
    }

    public static void setEventEffectToThreatActionMapping(Map<EventEffectType, ThreatActionType> eventEffectToThreatActionMapping) {
        Mappings.eventEffectToThreatActionMapping = eventEffectToThreatActionMapping;
    }

    public static Map<EventEffectType, ThreatEffectType> getEventEffectToThreatEffectMapping() {
        return eventEffectToThreatEffectMapping;
    }

    public static void setEventEffectToThreatEffectMapping(Map<EventEffectType, ThreatEffectType> eventEffectToThreatEffectMapping) {
        Mappings.eventEffectToThreatEffectMapping = eventEffectToThreatEffectMapping;
    }

    public static Map<BuildingAdventureType, AdventureEventEffectType> getBuildingAdvntureToAdventureEventEffectMapping() {
        return buildingAdvntureToAdventureEventEffectMapping;
    }

    public static void setBuildingAdvntureToAdventureEventEffectMapping(Map<BuildingAdventureType, AdventureEventEffectType> buildingAdvntureToAdventureEventEffectMapping) {
        Mappings.buildingAdvntureToAdventureEventEffectMapping = buildingAdvntureToAdventureEventEffectMapping;
    }

    public static Map<GatheringResourcesAdventureType, AdventureEventEffectType> getGatheringAdventureToAdventureEventEffectMapping() {
        return gatheringAdventureToAdventureEventEffectMapping;
    }

    public static void setGatheringAdventureToAdventureEventEffectMapping(Map<GatheringResourcesAdventureType, AdventureEventEffectType> gatheringAdventureToAdventureEventEffectMapping) {
        Mappings.gatheringAdventureToAdventureEventEffectMapping = gatheringAdventureToAdventureEventEffectMapping;
    }

    public static Map<ExplorationAdventureType, AdventureEventEffectType> getExplorationAdventureToAdventureEventEffectMapping() {
        return explorationAdventureToAdventureEventEffectMapping;
    }

    public static void setExplorationAdventureToAdventureEventEffectMapping(Map<ExplorationAdventureType, AdventureEventEffectType> explorationAdventureToAdventureEventEffectMapping) {
        Mappings.explorationAdventureToAdventureEventEffectMapping = explorationAdventureToAdventureEventEffectMapping;
    }

    public static Map<BeastType, List<Integer>> getBeastToBeastStatsMapping() {
        return beastToBeastStatsMapping;
    }

    public static void setBeastToBeastStatsMapping(Map<BeastType, List<Integer>> beastToBeastStatsMapping) {
        Mappings.beastToBeastStatsMapping = beastToBeastStatsMapping;
    }
}
