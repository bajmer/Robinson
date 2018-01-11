package model.actions;

import model.GameInfo;
import model.ICharacter;
import model.cards.BeastCard;
import model.enums.cards.BeastType;
import model.enums.elements.MarkerType;

import java.util.List;

public class HuntingAction extends Action {

    public HuntingAction(List<MarkerType> extraMarkers) {
        super(extraMarkers);
    }

    @Override
    public void runAction() {
        int assignedMarkersNumber = super.getAssignedMarkers().size();
        int huntingNumber = assignedMarkersNumber / 2;

        for (int i = 0; i < huntingNumber; i++) {
            ICharacter leaderCharacter = super.getAssignedMarkers().get(2 * i).getCharacter();
            BeastCard beast = GameInfo.getAvaibleBeastCards().removeFirst();
            BeastType name = beast.getBeast();
            int strength = beast.getStrength();
            int weaponLevelDecrease = beast.getWeaponLevelDecrease();
            int foodAmount = beast.getFoodAmount();
            int hideAmount = beast.getHideAmount();

            super.getLogger().info("Walka z bestią: " + name + ". Siła: " + strength +
                    ", spadek poziomu broni: " + weaponLevelDecrease +
                    ", ilość pożywienia: " + foodAmount + ", ilość skór:" + hideAmount);

            int weaponLevel = GameInfo.getWeaponLevel();
            if (weaponLevel < strength) {
                leaderCharacter.changeLife(weaponLevel - strength);
            }

            GameInfo.changeWeaponLevel(beast.getWeaponLevelDecrease());
            GameInfo.changeFoodLevel(beast.getFoodAmount(), null);
            GameInfo.changeHideLevel(beast.getHideAmount());
        }
    }
}