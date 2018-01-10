package model.actions;

import controller.GameEngineController;
import model.ICharacter;
import model.cards.BeastCard;
import model.enums.elements.MarkerType;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class HuntingAction extends Action {

    public HuntingAction(List<MarkerType> allowedMarkers) {
        super(allowedMarkers);
    }

    @Override
    public void runAction(GameEngineController controller) {
        int assignedMarkersNumber = super.getAssignedMarkers().size();
        MarkerType executiveCharacterMarker = super.getAssignedMarkers().get(0).getMarkerType();
        AtomicReference<ICharacter> executiveCharacter = null;
        controller.getGameInfo().getCharacters().forEach(iCharacter -> {
            if (iCharacter.getCharacterMarkers().get(0).getMarkerType() == executiveCharacterMarker) {
                executiveCharacter.set(iCharacter);
            }
        });

        int weaponLevel = controller.getGameInfo().getWeaponLevel();

        BeastCard beast = controller.getGameInfo().getAvaibleBeastCards().removeFirst();
        int beastStrenght = beast.getStrength();

        if (weaponLevel < beastStrenght) {
            executiveCharacter.get().changeLife(weaponLevel - beastStrenght);
        }

        controller.getGameInfo().changeWeaponLevel(beast.getWeaponLevelDecrease());
        controller.getGameInfo().changeFoodLevel(beast.getFoodAmount(), null);
        controller.getGameInfo().changeHideLevel(beast.getHideAmount());
    }
}