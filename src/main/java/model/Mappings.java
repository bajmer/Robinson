package model;

import model.enums.cards.*;

import java.util.*;

import static model.enums.cards.BeastType.*;
import static model.enums.cards.BuildingAdventureType.*;
import static model.enums.cards.EventEffectType.*;
import static model.enums.cards.EventIconType.BOOK;
import static model.enums.cards.ExplorationAdventureType.*;
import static model.enums.cards.GatheringResourcesAdventureType.*;
import static model.enums.cards.ThreatActionType.THREAT_ACTION;
import static model.enums.cards.ThreatEffectType.THREAT_EFFECT;

public class Mappings {
    private static Map<Integer, EventEffectType> idToEventEffectMapping = new HashMap<>();
    private static Map<Integer, EventIconType> idToEventIconMapping = new HashMap<>();
    private static Map<Integer, ThreatActionType> idToThreatActionMapping = new HashMap<>();
    private static Map<Integer, ThreatEffectType> idToThreatEffectMapping = new HashMap<>();
    private static Map<Integer, BuildingAdventureType> idToBuildingAdvntureMapping = new HashMap<>();
    private static Map<Integer, GatheringResourcesAdventureType> idToGatheringAdventureMapping = new HashMap<>();
    private static Map<Integer, ExplorationAdventureType> idToExplorationAdventureMapping = new HashMap<>();
    //    private static Map<BeastType, Integer> beastToStrenghtMapping = new HashMap<>();
//    private static Map<BeastType, Integer> beastToWeaponLevelDecreaseMapping = new HashMap<>();
//    private static Map<BeastType, Integer> beastToFoodAmountMapping = new HashMap<>();
//    private static Map<BeastType, Integer> beastToHideAmountMapping = new HashMap<>();
    private static Map<BeastType, List<Integer>> beastToBeastStatsMapping = new HashMap<>();
    private static Map<Integer, Integer> scenarioIdToRoundsNumberMapping = new HashMap<>();

    public Mappings() {
//        ******WRECKAGE CARDS******
        idToEventEffectMapping.put(1, FOOD_CRATES);
        idToEventEffectMapping.put(2, WRECKED_LIFEBOAT);
        idToEventEffectMapping.put(3, CAPTAINS_CHEST);
//        *******EVENT CARDS*******
        idToEventEffectMapping.put(4, WINTER_DEPRESSION);
        idToEventEffectMapping.put(5, CHRONIC_TIREDNESS);
        idToEventEffectMapping.put(6, HIGH_WATER);
        idToEventEffectMapping.put(7, FROST);
        idToEventEffectMapping.put(8, WRECKED_BALLOON);
        idToEventEffectMapping.put(9, PREDATORS_IN_THE_VICINITY);
        idToEventEffectMapping.put(10, NIGHT_HOWLING);
        idToEventEffectMapping.put(11, POISONING);
        idToEventEffectMapping.put(12, CULLED_AREA);
        idToEventEffectMapping.put(13, BODY_ON_THE_BEACH);
        idToEventEffectMapping.put(14, COLD_RAIN);
        idToEventEffectMapping.put(15, COLD_RAIN);
        idToEventEffectMapping.put(16, MEMORIES_OF_THE_CRUISE);
        idToEventEffectMapping.put(17, THUNDERSTORM);
        idToEventEffectMapping.put(18, PREDATORS);
        idToEventEffectMapping.put(19, CALL_OF_THE_WILD);
        idToEventEffectMapping.put(20, EARTHQUAKE);
        idToEventEffectMapping.put(21, DESTRUCTIVE_HURRICANE);
        idToEventEffectMapping.put(22, LANDSLIDE);
        idToEventEffectMapping.put(23, BAD_FEELINGS);
        idToEventEffectMapping.put(24, MESS_IN_THE_CAMP);
        idToEventEffectMapping.put(25, THE_ISLAND_REBELS_AGAINST_YOU);
        idToEventEffectMapping.put(26, NATURAL_DAM_BREAKS);
        idToEventEffectMapping.put(27, RAGING_RIVER);
        idToEventEffectMapping.put(28, FLOOD);
        idToEventEffectMapping.put(29, VERTIGO);
        idToEventEffectMapping.put(30, HEAVY_RAIN);
        idToEventEffectMapping.put(31, WEAKNESS);
        idToEventEffectMapping.put(32, SLOW_WORK);
        idToEventEffectMapping.put(33, RAIN);
        idToEventEffectMapping.put(34, PREDATORS_IN_THE_WOODS);
        idToEventEffectMapping.put(35, BAD_FATE);
        idToEventEffectMapping.put(36, EventEffectType.BEAR);
        idToEventEffectMapping.put(37, CATACLYSM);
        idToEventEffectMapping.put(38, SEARCHING_FOR_A_NEW_PATH);
        idToEventEffectMapping.put(39, FIGHT);
        idToEventEffectMapping.put(40, ARGUMENT);
        idToEventEffectMapping.put(41, CLOUD_BURST);
        idToEventEffectMapping.put(42, HEAVY_CLOUDS);
        idToEventEffectMapping.put(43, RAVAGING_HURRICANE);
        idToEventEffectMapping.put(44, RAVISHING_WINDSTORM);
        idToEventEffectMapping.put(45, FIRE);
        idToEventEffectMapping.put(46, SLEEPLESS_NIGHT);
        idToEventEffectMapping.put(47, PREDATOR_IS_NEAR);
        idToEventEffectMapping.put(48, STORM);
        idToEventEffectMapping.put(49, STORM);
        idToEventEffectMapping.put(50, EXHAUSTING_NIGHT);
        idToEventEffectMapping.put(51, BROKEN_TREE);
        idToEventEffectMapping.put(52, STRONG_WIND);
        idToEventEffectMapping.put(53, EventEffectType.WEATHER_BREAKDOWN);
        idToEventEffectMapping.put(54, MIST);
        idToEventEffectMapping.put(55, DEPRESSION);
        idToEventEffectMapping.put(56, DISASTER);
        idToEventEffectMapping.put(57, FLYING_SURPRISE);
        idToEventEffectMapping.put(58, DEVASTATING_BLOWS);
        idToEventEffectMapping.put(59, EventEffectType.LACK_OF_HOPE);
        idToEventEffectMapping.put(60, LOSS_OF_HOPE);
        idToEventEffectMapping.put(61, FIGHT_IN_THE_DARK);
        idToEventEffectMapping.put(62, DANGEROUS_NIGHT);
        idToEventEffectMapping.put(63, NIGHT_ATTACK);
        idToEventEffectMapping.put(64, HOWLING_FROM_THE_WOODS);
        idToEventEffectMapping.put(65, SMOKE_ON_THE_HORIZON);
        idToEventEffectMapping.put(66, DROUGHT);
        idToEventEffectMapping.put(67, ROUGH_PASSAGE);
        idToEventEffectMapping.put(68, UNUSUALLY_COLD_NIGHT);
        idToEventEffectMapping.put(69, COUNCIL);
        idToEventEffectMapping.put(70, INSECTS);
        idToEventEffectMapping.put(71, HEAVY_RAIN_IS_COMING);
        idToEventEffectMapping.put(72, AWFUL_WEATHER);
        idToEventEffectMapping.put(73, PRECIPICE);
        idToEventEffectMapping.put(74, EventEffectType.JAGUAR);
        idToEventEffectMapping.put(75, RAVENOUS_PREDATORS);
        idToEventEffectMapping.put(76, OTTERS);
//        *************BUILDING ADVENTURE CARDS**************
//        idToEventEffectMapping.put(77, );
//        idToEventEffectMapping.put(78, );
//        idToEventEffectMapping.put(79, );
//        idToEventEffectMapping.put(80, );
//        idToEventEffectMapping.put(81, );
//        idToEventEffectMapping.put(82, );
//        idToEventEffectMapping.put(83, );
//        idToEventEffectMapping.put(84, );
//        idToEventEffectMapping.put(85, );
//        idToEventEffectMapping.put(86, );
//        idToEventEffectMapping.put(87, );
//        idToEventEffectMapping.put(88, );
//        idToEventEffectMapping.put(89, );
//        idToEventEffectMapping.put(90, );
//        idToEventEffectMapping.put(91, );
//        idToEventEffectMapping.put(92, );
//        idToEventEffectMapping.put(93, );
//        idToEventEffectMapping.put(94, );
//        idToEventEffectMapping.put(95, );
//        idToEventEffectMapping.put(96, );
//        idToEventEffectMapping.put(97, );
//        idToEventEffectMapping.put(98, );
//        idToEventEffectMapping.put(99, );
//        idToEventEffectMapping.put(100, );
//        idToEventEffectMapping.put(101, );
//        idToEventEffectMapping.put(102, );
//        idToEventEffectMapping.put(103, );
//        idToEventEffectMapping.put(104, );
//        idToEventEffectMapping.put(105, );
//        idToEventEffectMapping.put(106, );
//        *************GATHERING RESOURCES ADVENTURE CARDS**************
//        idToEventEffectMapping.put(107, );
//        idToEventEffectMapping.put(108, );
//        idToEventEffectMapping.put(109, );
//        idToEventEffectMapping.put(110, );
//        idToEventEffectMapping.put(111, );
//        idToEventEffectMapping.put(112, );
//        idToEventEffectMapping.put(113, );
//        idToEventEffectMapping.put(114, );
//        idToEventEffectMapping.put(115, );
//        idToEventEffectMapping.put(116, );
//        idToEventEffectMapping.put(117, );
//        idToEventEffectMapping.put(118, );
//        idToEventEffectMapping.put(119, );
//        idToEventEffectMapping.put(120, );
//        idToEventEffectMapping.put(121, );
//        idToEventEffectMapping.put(122, );
//        idToEventEffectMapping.put(123, );
//        idToEventEffectMapping.put(124, );
//        idToEventEffectMapping.put(125, );
//        idToEventEffectMapping.put(126, );
//        idToEventEffectMapping.put(127, );
//        idToEventEffectMapping.put(128, );
//        idToEventEffectMapping.put(129, );
//        idToEventEffectMapping.put(130, );
//        idToEventEffectMapping.put(131, );
//        idToEventEffectMapping.put(132, );
//        idToEventEffectMapping.put(133, );
//        idToEventEffectMapping.put(134, );
//        idToEventEffectMapping.put(135, );
//        idToEventEffectMapping.put(136, );
//        *************EXPLORATION ADVENTURE CARDS**************
//        idToEventEffectMapping.put(137, );
//        idToEventEffectMapping.put(138, );
//        idToEventEffectMapping.put(139, );
//        idToEventEffectMapping.put(140, );
//        idToEventEffectMapping.put(141, );
//        idToEventEffectMapping.put(142, );
//        idToEventEffectMapping.put(143, );
//        idToEventEffectMapping.put(144, );
//        idToEventEffectMapping.put(145, );
//        idToEventEffectMapping.put(146, );
//        idToEventEffectMapping.put(147, );
//        idToEventEffectMapping.put(148, );
//        idToEventEffectMapping.put(149, );
//        idToEventEffectMapping.put(150, );
//        idToEventEffectMapping.put(151, );
//        idToEventEffectMapping.put(152, );
//        idToEventEffectMapping.put(153, );
//        idToEventEffectMapping.put(154, );
//        idToEventEffectMapping.put(155, );
//        idToEventEffectMapping.put(156, );
//        idToEventEffectMapping.put(157, );
//        idToEventEffectMapping.put(158, );
//        idToEventEffectMapping.put(159, );
//        idToEventEffectMapping.put(160, );
//        idToEventEffectMapping.put(161, );
//        idToEventEffectMapping.put(162, );
//        idToEventEffectMapping.put(163, );
//        idToEventEffectMapping.put(164, );
//        idToEventEffectMapping.put(165, );
//        idToEventEffectMapping.put(166, );
//
        idToEventIconMapping.put(1, BOOK);
//        idToEventIconMapping.put(2, );
//        idToEventIconMapping.put(3, );
//        idToEventIconMapping.put(4, );
//        idToEventIconMapping.put(5, );
//        idToEventIconMapping.put(6, );
//        idToEventIconMapping.put(7, );
//        idToEventIconMapping.put(8, );
//        idToEventIconMapping.put(9, );
//        idToEventIconMapping.put(10, );
//        idToEventIconMapping.put(11, );
//        idToEventIconMapping.put(12, );
//        idToEventIconMapping.put(13, );
//        idToEventIconMapping.put(14, );
//        idToEventIconMapping.put(15, );
//        idToEventIconMapping.put(16, );
//        idToEventIconMapping.put(17, );
//        idToEventIconMapping.put(18, );
//        idToEventIconMapping.put(19, );
//        idToEventIconMapping.put(20, );
//        idToEventIconMapping.put(21, );
//        idToEventIconMapping.put(22, );
//        idToEventIconMapping.put(23, );
//        idToEventIconMapping.put(24, );
//        idToEventIconMapping.put(25, );
//        idToEventIconMapping.put(26, );
//        idToEventIconMapping.put(27, );
//        idToEventIconMapping.put(28, );
//        idToEventIconMapping.put(29, );
//        idToEventIconMapping.put(30, );
//        idToEventIconMapping.put(31, );
//        idToEventIconMapping.put(32, );
//        idToEventIconMapping.put(33, );
//        idToEventIconMapping.put(34, );
//        idToEventIconMapping.put(35, );
//        idToEventIconMapping.put(36, );
//        idToEventIconMapping.put(37, );
//        idToEventIconMapping.put(38, );
//        idToEventIconMapping.put(39, );
//        idToEventIconMapping.put(40, );
//        idToEventIconMapping.put(41, );
//        idToEventIconMapping.put(42, );
//        idToEventIconMapping.put(43, );
//        idToEventIconMapping.put(44, );
//        idToEventIconMapping.put(45, );
//        idToEventIconMapping.put(46, );
//        idToEventIconMapping.put(47, );
//        idToEventIconMapping.put(48, );
//        idToEventIconMapping.put(49, );
//        idToEventIconMapping.put(50, );
//        idToEventIconMapping.put(51, );
//        idToEventIconMapping.put(52, );
//        idToEventIconMapping.put(53, );
//        idToEventIconMapping.put(54, );
//        idToEventIconMapping.put(55, );
//        idToEventIconMapping.put(56, );
//        idToEventIconMapping.put(57, );
//        idToEventIconMapping.put(58, );
//        idToEventIconMapping.put(59, );
//        idToEventIconMapping.put(60, );
//        idToEventIconMapping.put(61, );
//        idToEventIconMapping.put(62, );
//        idToEventIconMapping.put(63, );
//        idToEventIconMapping.put(64, );
//        idToEventIconMapping.put(65, );
//        idToEventIconMapping.put(66, );
//        idToEventIconMapping.put(67, );
//        idToEventIconMapping.put(68, );
//        idToEventIconMapping.put(69, );
//        idToEventIconMapping.put(70, );
//        idToEventIconMapping.put(71, );
//        idToEventIconMapping.put(72, );
//        idToEventIconMapping.put(73, );
////        *******************************************************
//        idToEventIconMapping.put(74, );
//        idToEventIconMapping.put(75, );
//        idToEventIconMapping.put(76, );
//        idToEventIconMapping.put(77, );
//        idToEventIconMapping.put(78, );
//        idToEventIconMapping.put(79, );
//        idToEventIconMapping.put(80, );
//        idToEventIconMapping.put(81, );
//        idToEventIconMapping.put(82, );
//        idToEventIconMapping.put(83, );
//        idToEventIconMapping.put(84, );
//        idToEventIconMapping.put(85, );
//        idToEventIconMapping.put(86, );
//        idToEventIconMapping.put(87, );
//        idToEventIconMapping.put(88, );
//        idToEventIconMapping.put(89, );
//        idToEventIconMapping.put(90, );
//        idToEventIconMapping.put(91, );
//        idToEventIconMapping.put(92, );
//        idToEventIconMapping.put(93, );
//        idToEventIconMapping.put(94, );
//        idToEventIconMapping.put(95, );
//        idToEventIconMapping.put(96, );
//        idToEventIconMapping.put(97, );
//        idToEventIconMapping.put(98, );
//        idToEventIconMapping.put(99, );
//        idToEventIconMapping.put(100, );
//        idToEventIconMapping.put(101, );
//        idToEventIconMapping.put(102, );
//        idToEventIconMapping.put(103, );
//        idToEventIconMapping.put(104, );
//        idToEventIconMapping.put(105, );
//        idToEventIconMapping.put(106, );
//        idToEventIconMapping.put(107, );
//        idToEventIconMapping.put(108, );
//        idToEventIconMapping.put(109, );
//        idToEventIconMapping.put(110, );
//        idToEventIconMapping.put(111, );
//        idToEventIconMapping.put(112, );
//        idToEventIconMapping.put(113, );
//        idToEventIconMapping.put(114, );
//        idToEventIconMapping.put(115, );
//        idToEventIconMapping.put(116, );
//        idToEventIconMapping.put(117, );
//        idToEventIconMapping.put(118, );
//        idToEventIconMapping.put(119, );
//        idToEventIconMapping.put(120, );
//        idToEventIconMapping.put(121, );
//        idToEventIconMapping.put(122, );
//        idToEventIconMapping.put(123, );
//        idToEventIconMapping.put(124, );
//        idToEventIconMapping.put(125, );
//        idToEventIconMapping.put(126, );
//        idToEventIconMapping.put(127, );
//        idToEventIconMapping.put(128, );
//        idToEventIconMapping.put(129, );
//        idToEventIconMapping.put(130, );
//        idToEventIconMapping.put(131, );
//        idToEventIconMapping.put(132, );
//        idToEventIconMapping.put(133, );
//        idToEventIconMapping.put(134, );
//        idToEventIconMapping.put(135, );
//        idToEventIconMapping.put(136, );
//        idToEventIconMapping.put(137, );
//        idToEventIconMapping.put(138, );
//        idToEventIconMapping.put(139, );
//        idToEventIconMapping.put(140, );
//        idToEventIconMapping.put(141, );
//        idToEventIconMapping.put(142, );
//        idToEventIconMapping.put(143, );
//        idToEventIconMapping.put(144, );
//        idToEventIconMapping.put(145, );
//        idToEventIconMapping.put(146, );
//        idToEventIconMapping.put(147, );
//        idToEventIconMapping.put(148, );
//        idToEventIconMapping.put(149, );
//        idToEventIconMapping.put(150, );
//        idToEventIconMapping.put(151, );
//        idToEventIconMapping.put(152, );
//        idToEventIconMapping.put(153, );
//        idToEventIconMapping.put(154, );
//        idToEventIconMapping.put(155, );
//        idToEventIconMapping.put(156, );
//        idToEventIconMapping.put(157, );
//        idToEventIconMapping.put(158, );
//        idToEventIconMapping.put(159, );
//        idToEventIconMapping.put(160, );
//        idToEventIconMapping.put(161, );
//        idToEventIconMapping.put(162, );
//        idToEventIconMapping.put(163, );
//        idToEventIconMapping.put(164, );
//        idToEventIconMapping.put(165, );
//        idToEventIconMapping.put(166, );
//
        idToThreatActionMapping.put(1, THREAT_ACTION);
//        idToThreatActionMapping.put(2, );
//        idToThreatActionMapping.put(3, );
//        idToThreatActionMapping.put(4, );
//        idToThreatActionMapping.put(5, );
//        idToThreatActionMapping.put(6, );
//        idToThreatActionMapping.put(7, );
//        idToThreatActionMapping.put(8, );
//        idToThreatActionMapping.put(9, );
//        idToThreatActionMapping.put(10, );
//        idToThreatActionMapping.put(11, );
//        idToThreatActionMapping.put(12, );
//        idToThreatActionMapping.put(13, );
//        idToThreatActionMapping.put(14, );
//        idToThreatActionMapping.put(15, );
//        idToThreatActionMapping.put(16, );
//        idToThreatActionMapping.put(17, );
//        idToThreatActionMapping.put(18, );
//        idToThreatActionMapping.put(19, );
//        idToThreatActionMapping.put(20, );
//        idToThreatActionMapping.put(21, );
//        idToThreatActionMapping.put(22, );
//        idToThreatActionMapping.put(23, );
//        idToThreatActionMapping.put(24, );
//        idToThreatActionMapping.put(25, );
//        idToThreatActionMapping.put(26, );
//        idToThreatActionMapping.put(27, );
//        idToThreatActionMapping.put(28, );
//        idToThreatActionMapping.put(29, );
//        idToThreatActionMapping.put(30, );
//        idToThreatActionMapping.put(31, );
//        idToThreatActionMapping.put(32, );
//        idToThreatActionMapping.put(33, );
//        idToThreatActionMapping.put(34, );
//        idToThreatActionMapping.put(35, );
//        idToThreatActionMapping.put(36, );
//        idToThreatActionMapping.put(37, );
//        idToThreatActionMapping.put(38, );
//        idToThreatActionMapping.put(39, );
//        idToThreatActionMapping.put(40, );
//        idToThreatActionMapping.put(41, );
//        idToThreatActionMapping.put(42, );
//        idToThreatActionMapping.put(43, );
//        idToThreatActionMapping.put(44, );
//        idToThreatActionMapping.put(45, );
//        idToThreatActionMapping.put(46, );
//        idToThreatActionMapping.put(47, );
//        idToThreatActionMapping.put(48, );
//        idToThreatActionMapping.put(49, );
//        idToThreatActionMapping.put(50, );
//        idToThreatActionMapping.put(51, );
//        idToThreatActionMapping.put(52, );
//        idToThreatActionMapping.put(53, );
//        idToThreatActionMapping.put(54, );
//        idToThreatActionMapping.put(55, );
//        idToThreatActionMapping.put(56, );
//        idToThreatActionMapping.put(57, );
//        idToThreatActionMapping.put(58, );
//        idToThreatActionMapping.put(59, );
//        idToThreatActionMapping.put(60, );
//        idToThreatActionMapping.put(61, );
//        idToThreatActionMapping.put(62, );
//        idToThreatActionMapping.put(63, );
//        idToThreatActionMapping.put(64, );
//        idToThreatActionMapping.put(65, );
//        idToThreatActionMapping.put(66, );
//        idToThreatActionMapping.put(67, );
//        idToThreatActionMapping.put(68, );
//        idToThreatActionMapping.put(69, );
//        idToThreatActionMapping.put(70, );
//        idToThreatActionMapping.put(71, );
//        idToThreatActionMapping.put(72, );
//        idToThreatActionMapping.put(73, );
//        idToThreatActionMapping.put(74, );
//        idToThreatActionMapping.put(75, );
//        idToThreatActionMapping.put(76, );

        idToThreatEffectMapping.put(1, THREAT_EFFECT);
//        idToThreatEffectMapping.put(2, );
//        idToThreatEffectMapping.put(3, );
//        idToThreatEffectMapping.put(4, );
//        idToThreatEffectMapping.put(5, );
//        idToThreatEffectMapping.put(6, );
//        idToThreatEffectMapping.put(7, );
//        idToThreatEffectMapping.put(8, );
//        idToThreatEffectMapping.put(9, );
//        idToThreatEffectMapping.put(10, );
//        idToThreatEffectMapping.put(11, );
//        idToThreatEffectMapping.put(12, );
//        idToThreatEffectMapping.put(13, );
//        idToThreatEffectMapping.put(14, );
//        idToThreatEffectMapping.put(15, );
//        idToThreatEffectMapping.put(16, );
//        idToThreatEffectMapping.put(17, );
//        idToThreatEffectMapping.put(18, );
//        idToThreatEffectMapping.put(19, );
//        idToThreatEffectMapping.put(20, );
//        idToThreatEffectMapping.put(21, );
//        idToThreatEffectMapping.put(22, );
//        idToThreatEffectMapping.put(23, );
//        idToThreatEffectMapping.put(24, );
//        idToThreatEffectMapping.put(25, );
//        idToThreatEffectMapping.put(26, );
//        idToThreatEffectMapping.put(27, );
//        idToThreatEffectMapping.put(28, );
//        idToThreatEffectMapping.put(29, );
//        idToThreatEffectMapping.put(30, );
//        idToThreatEffectMapping.put(31, );
//        idToThreatEffectMapping.put(32, );
//        idToThreatEffectMapping.put(33, );
//        idToThreatEffectMapping.put(34, );
//        idToThreatEffectMapping.put(35, );
//        idToThreatEffectMapping.put(36, );
//        idToThreatEffectMapping.put(37, );
//        idToThreatEffectMapping.put(38, );
//        idToThreatEffectMapping.put(39, );
//        idToThreatEffectMapping.put(40, );
//        idToThreatEffectMapping.put(41, );
//        idToThreatEffectMapping.put(42, );
//        idToThreatEffectMapping.put(43, );
//        idToThreatEffectMapping.put(44, );
//        idToThreatEffectMapping.put(45, );
//        idToThreatEffectMapping.put(46, );
//        idToThreatEffectMapping.put(47, );
//        idToThreatEffectMapping.put(48, );
//        idToThreatEffectMapping.put(49, );
//        idToThreatEffectMapping.put(50, );
//        idToThreatEffectMapping.put(51, );
//        idToThreatEffectMapping.put(52, );
//        idToThreatEffectMapping.put(53, );
//        idToThreatEffectMapping.put(54, );
//        idToThreatEffectMapping.put(55, );
//        idToThreatEffectMapping.put(56, );
//        idToThreatEffectMapping.put(57, );
//        idToThreatEffectMapping.put(58, );
//        idToThreatEffectMapping.put(59, );
//        idToThreatEffectMapping.put(60, );
//        idToThreatEffectMapping.put(61, );
//        idToThreatEffectMapping.put(62, );
//        idToThreatEffectMapping.put(63, );
//        idToThreatEffectMapping.put(64, );
//        idToThreatEffectMapping.put(65, );
//        idToThreatEffectMapping.put(66, );
//        idToThreatEffectMapping.put(67, );
//        idToThreatEffectMapping.put(68, );
//        idToThreatEffectMapping.put(69, );
//        idToThreatEffectMapping.put(70, );
//        idToThreatEffectMapping.put(71, );
//        idToThreatEffectMapping.put(72, );
//        idToThreatEffectMapping.put(73, );
//        idToThreatEffectMapping.put(74, );
//        idToThreatEffectMapping.put(75, );
//        idToThreatEffectMapping.put(76, );

        idToBuildingAdvntureMapping.put(77, TOOL_BREAKS);
        idToBuildingAdvntureMapping.put(78, LABORIOUS_WORK);
        idToBuildingAdvntureMapping.put(79, LABORIOUS_WORK);
        idToBuildingAdvntureMapping.put(80, CONSTRUCTION);
        idToBuildingAdvntureMapping.put(81, UNMOTIVATED);
        idToBuildingAdvntureMapping.put(82, FAST_WORK);
        idToBuildingAdvntureMapping.put(83, STING);
        idToBuildingAdvntureMapping.put(84, NASTY_WOUND);
        idToBuildingAdvntureMapping.put(85, TIRED);
        idToBuildingAdvntureMapping.put(86, IN_A_HURRY);
        idToBuildingAdvntureMapping.put(87, DARK_CLOUDS_IN_THE_SKY);
        idToBuildingAdvntureMapping.put(88, CONSTRUCTION_IS_WEAK);
        idToBuildingAdvntureMapping.put(89, PREDATOR_IN_THE_CAMP);
        idToBuildingAdvntureMapping.put(90, WIND_STORM);
        idToBuildingAdvntureMapping.put(91, FEAR_OF_THE_BEASTS);
        idToBuildingAdvntureMapping.put(92, BREAKDOWN);
        idToBuildingAdvntureMapping.put(93, BROKEN_LEVER);
        idToBuildingAdvntureMapping.put(94, DANGEROUS_WORK);
        idToBuildingAdvntureMapping.put(95, BuildingAdventureType.LACK_OF_HOPE);
        idToBuildingAdvntureMapping.put(96, HARD_WORK);
        idToBuildingAdvntureMapping.put(97, COMING_TO_TERMS);
        idToBuildingAdvntureMapping.put(98, CUT_HEAD);
        idToBuildingAdvntureMapping.put(99, TOOLS_INSPECTION);
        idToBuildingAdvntureMapping.put(100, MONKEYS_WATCH_YOU);
        idToBuildingAdvntureMapping.put(101, SAVINGS);
        idToBuildingAdvntureMapping.put(102, ACCIDENT);
        idToBuildingAdvntureMapping.put(103, HOWLING_IN_THE_BUSHES);
        idToBuildingAdvntureMapping.put(104, HUNGRY_PREDATOR);
        idToBuildingAdvntureMapping.put(105, YOU_NEED_A_BIGGER_CAMP);
        idToBuildingAdvntureMapping.put(106, VISIT_OF_A_PREDATOR);

        idToGatheringAdventureMapping.put(107, END_OF_SOURCE);
        idToGatheringAdventureMapping.put(108, END_OF_SOURCE);
        idToGatheringAdventureMapping.put(109, END_OF_SOURCE);
        idToGatheringAdventureMapping.put(110, END_OF_SOURCE);
        idToGatheringAdventureMapping.put(111, END_OF_SOURCE);
        idToGatheringAdventureMapping.put(112, WINTER_FREEZING);
        idToGatheringAdventureMapping.put(113, UNEXPECTED_DISCOVERY);
        idToGatheringAdventureMapping.put(114, FURS);
        idToGatheringAdventureMapping.put(115, SPIDER);
        idToGatheringAdventureMapping.put(116, SKELETON);
        idToGatheringAdventureMapping.put(117, SIGNS_OF_A_PREDATOR);
        idToGatheringAdventureMapping.put(118, PIRATES_CHEST);
        idToGatheringAdventureMapping.put(119, VIPER);
        idToGatheringAdventureMapping.put(120, UNBELIEVABLE_EFFORT);
        idToGatheringAdventureMapping.put(121, TWISTED_ANKLE);
        idToGatheringAdventureMapping.put(122, WINTER_IS_COMING);
        idToGatheringAdventureMapping.put(123, GatheringResourcesAdventureType.WEATHER_BREAKDOWN);
        idToGatheringAdventureMapping.put(124, UNEXPECTED_TROUBLES);
        idToGatheringAdventureMapping.put(125, TRACKS_OF_A_PREDATOR);
        idToGatheringAdventureMapping.put(126, SHORTAGE);
        idToGatheringAdventureMapping.put(127, MUSHROOMS);
        idToGatheringAdventureMapping.put(128, GOLD_COIN);
        idToGatheringAdventureMapping.put(129, FRUIT);
        idToGatheringAdventureMapping.put(130, EYES_IN_THE_BUSHES);
        idToGatheringAdventureMapping.put(131, AFTER_THE_HURRICANE);
        idToGatheringAdventureMapping.put(132, PATH_OF_A_PREDATOR);
        idToGatheringAdventureMapping.put(133, NICE_SURPRISE);
        idToGatheringAdventureMapping.put(134, NESTLINGS);
        idToGatheringAdventureMapping.put(135, NEW_FLOCK);
        idToGatheringAdventureMapping.put(136, SURPRISE_IN_THE_BUSHES);

        idToExplorationAdventureMapping.put(137, WRONG_TRACK);
        idToExplorationAdventureMapping.put(138, SWAMP);
        idToExplorationAdventureMapping.put(139, SURPRISING_DISCOVERY);
        idToExplorationAdventureMapping.put(140, ITS_GOING_TO_RAIN);
        idToExplorationAdventureMapping.put(141, THERES_SOMETHING_IN_THE_AIR);
        idToExplorationAdventureMapping.put(142, BAMBOO);
        idToExplorationAdventureMapping.put(143, OLD_HUT);
        idToExplorationAdventureMapping.put(144, OLD_GRAVE);
        idToExplorationAdventureMapping.put(145, SIGNS_OF_FIRE);
        idToExplorationAdventureMapping.put(146, SHRINE);
        idToExplorationAdventureMapping.put(147, WILD_BERRIES);
        idToExplorationAdventureMapping.put(148, VIPERS);
        idToExplorationAdventureMapping.put(149, SECRET_CAVE);
        idToExplorationAdventureMapping.put(150, ExplorationAdventureType.PUMA);
        idToExplorationAdventureMapping.put(151, ExplorationAdventureType.TIGER);
        idToExplorationAdventureMapping.put(152, STORM_ON_THE_HORIZON);
        idToExplorationAdventureMapping.put(153, LOST);
        idToExplorationAdventureMapping.put(154, LOST_IN_THE_WOODS);
        idToExplorationAdventureMapping.put(155, LOST_IN_THE_THICKET);
        idToExplorationAdventureMapping.put(156, DANGEROUS_TERRAIN);
        idToExplorationAdventureMapping.put(157, FLU);
        idToExplorationAdventureMapping.put(158, EMPTY_FOREST);
        idToExplorationAdventureMapping.put(159, COLD_WIND);
        idToExplorationAdventureMapping.put(160, CARCASS);
        idToExplorationAdventureMapping.put(161, RUINED_HUT);
        idToExplorationAdventureMapping.put(162, REMAINS_OF_A_SETTLEMENT);
        idToExplorationAdventureMapping.put(163, THORNY_BUSH);
        idToExplorationAdventureMapping.put(164, MISADVENTURE);
        idToExplorationAdventureMapping.put(165, ExplorationAdventureType.WILD_DOG);
        idToExplorationAdventureMapping.put(166, SIGNS_OF_FIRE);

//        beastToStrenghtMapping.put(ALLIGATOR,0);
//        beastToStrenghtMapping.put(BeastType.BEAR, 0);
//        beastToStrenghtMapping.put(BIRDS, 0);
//        beastToStrenghtMapping.put(BOA, 0);
//        beastToStrenghtMapping.put(CHAMOIS, 0);
//        beastToStrenghtMapping.put(CHEETAH, 0);
//        beastToStrenghtMapping.put(FOX, 0);
//        beastToStrenghtMapping.put(GOATS, 0);
//        beastToStrenghtMapping.put(GORILLA, 0);
//        beastToStrenghtMapping.put(IGUANA, 0);
//        beastToStrenghtMapping.put(BeastType.JAGUAR, 0);
//        beastToStrenghtMapping.put(BeastType.PUMA, 0);
//        beastToStrenghtMapping.put(TAPIR, 0);
//        beastToStrenghtMapping.put(BeastType.TIGER, 0);
//        beastToStrenghtMapping.put(BeastType.WILD_DOG, 0);
//        beastToStrenghtMapping.put(WILD_PIG, 0);
//
//        beastToWeaponLevelDecreaseMapping.put(ALLIGATOR,0);
//        beastToWeaponLevelDecreaseMapping.put(BeastType.BEAR, 0);
//        beastToWeaponLevelDecreaseMapping.put(BIRDS, 0);
//        beastToWeaponLevelDecreaseMapping.put(BOA, 0);
//        beastToWeaponLevelDecreaseMapping.put(CHAMOIS, 0);
//        beastToWeaponLevelDecreaseMapping.put(CHEETAH, 0);
//        beastToWeaponLevelDecreaseMapping.put(FOX, 0);
//        beastToWeaponLevelDecreaseMapping.put(GOATS, 0);
//        beastToWeaponLevelDecreaseMapping.put(GORILLA, 0);
//        beastToWeaponLevelDecreaseMapping.put(IGUANA, 0);
//        beastToWeaponLevelDecreaseMapping.put(BeastType.JAGUAR, 0);
//        beastToWeaponLevelDecreaseMapping.put(BeastType.PUMA, 0);
//        beastToWeaponLevelDecreaseMapping.put(TAPIR, 0);
//        beastToWeaponLevelDecreaseMapping.put(BeastType.TIGER, 0);
//        beastToWeaponLevelDecreaseMapping.put(BeastType.WILD_DOG, 0);
//        beastToWeaponLevelDecreaseMapping.put(WILD_PIG, 0);
//
//        beastToFoodAmountMapping.put(ALLIGATOR,0);
//        beastToFoodAmountMapping.put(BeastType.BEAR, 0);
//        beastToFoodAmountMapping.put(BIRDS, 0);
//        beastToFoodAmountMapping.put(BOA, 0);
//        beastToFoodAmountMapping.put(CHAMOIS, 0);
//        beastToFoodAmountMapping.put(CHEETAH, 0);
//        beastToFoodAmountMapping.put(FOX, 0);
//        beastToFoodAmountMapping.put(GOATS, 0);
//        beastToFoodAmountMapping.put(GORILLA, 0);
//        beastToFoodAmountMapping.put(IGUANA, 0);
//        beastToFoodAmountMapping.put(BeastType.JAGUAR, 0);
//        beastToFoodAmountMapping.put(BeastType.PUMA, 0);
//        beastToFoodAmountMapping.put(TAPIR, 0);
//        beastToFoodAmountMapping.put(BeastType.TIGER, 0);
//        beastToFoodAmountMapping.put(BeastType.WILD_DOG, 0);
//        beastToFoodAmountMapping.put(WILD_PIG, 0);
//
//        beastToHideAmountMapping.put(ALLIGATOR,0);
//        beastToHideAmountMapping.put(BeastType.BEAR, 0);
//        beastToHideAmountMapping.put(BIRDS, 0);
//        beastToHideAmountMapping.put(BOA, 0);
//        beastToHideAmountMapping.put(CHAMOIS, 0);
//        beastToHideAmountMapping.put(CHEETAH, 0);
//        beastToStrenghtMapping.put(FOX, 0);
//        beastToStrenghtMapping.put(GOATS, 0);
//        beastToStrenghtMapping.put(GORILLA, 0);
//        beastToStrenghtMapping.put(IGUANA, 0);
//        beastToStrenghtMapping.put(BeastType.JAGUAR, 0);
//        beastToStrenghtMapping.put(BeastType.PUMA, 0);
//        beastToStrenghtMapping.put(TAPIR, 0);
//        beastToStrenghtMapping.put(BeastType.TIGER, 0);
//        beastToStrenghtMapping.put(BeastType.WILD_DOG, 0);
//        beastToStrenghtMapping.put(WILD_PIG, 0);

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
    }

    public static Map<Integer, Integer> getScenarioIdToRoundsNumberMapping() {
        return scenarioIdToRoundsNumberMapping;
    }

    public static void setScenarioIdToRoundsNumberMapping(Map<Integer, Integer> scenarioIdToRoundsNumberMapping) {
        Mappings.scenarioIdToRoundsNumberMapping = scenarioIdToRoundsNumberMapping;
    }

    public static Map<Integer, EventEffectType> getIdToEventEffectMapping() {
        return idToEventEffectMapping;
    }

    public static void setIdToEventEffectMapping(Map<Integer, EventEffectType> idToEventEffectMapping) {
        Mappings.idToEventEffectMapping = idToEventEffectMapping;
    }

    public static Map<Integer, EventIconType> getIdToEventIconMapping() {
        return idToEventIconMapping;
    }

    public static void setIdToEventIconMapping(Map<Integer, EventIconType> idToEventIconMapping) {
        Mappings.idToEventIconMapping = idToEventIconMapping;
    }

    public static Map<Integer, ThreatActionType> getIdToThreatActionMapping() {
        return idToThreatActionMapping;
    }

    public static void setIdToThreatActionMapping(Map<Integer, ThreatActionType> idToThreatActionMapping) {
        Mappings.idToThreatActionMapping = idToThreatActionMapping;
    }

    public static Map<Integer, ThreatEffectType> getIdToThreatEffectMapping() {
        return idToThreatEffectMapping;
    }

    public static void setIdToThreatEffectMapping(Map<Integer, ThreatEffectType> idToThreatEffectMapping) {
        Mappings.idToThreatEffectMapping = idToThreatEffectMapping;
    }

    public static Map<Integer, BuildingAdventureType> getIdToBuildingAdvntureMapping() {
        return idToBuildingAdvntureMapping;
    }

    public static void setIdToBuildingAdvntureMapping(Map<Integer, BuildingAdventureType> idToBuildingAdvntureMapping) {
        Mappings.idToBuildingAdvntureMapping = idToBuildingAdvntureMapping;
    }

    public static Map<Integer, GatheringResourcesAdventureType> getIdToGatheringAdventureMapping() {
        return idToGatheringAdventureMapping;
    }

    public static void setIdToGatheringAdventureMapping(Map<Integer, GatheringResourcesAdventureType> idToGatheringAdventureMapping) {
        Mappings.idToGatheringAdventureMapping = idToGatheringAdventureMapping;
    }

    public static Map<Integer, ExplorationAdventureType> getIdToExplorationAdventureMapping() {
        return idToExplorationAdventureMapping;
    }

    public static void setIdToExplorationAdventureMapping(Map<Integer, ExplorationAdventureType> idToExplorationAdventureMapping) {
        Mappings.idToExplorationAdventureMapping = idToExplorationAdventureMapping;
    }

    public static Map<BeastType, List<Integer>> getBeastToBeastStatsMapping() {
        return beastToBeastStatsMapping;
    }

    public static void setBeastToBeastStatsMapping(Map<BeastType, List<Integer>> beastToBeastStatsMapping) {
        Mappings.beastToBeastStatsMapping = beastToBeastStatsMapping;
    }
}
