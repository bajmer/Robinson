package model;

import model.elements.Marker;
import model.enums.SpecialSkillType;

import java.util.List;

public interface ICharacter {
    void useSpecialSkill(SpecialSkillType specialSkillType);

    void changeLife(int lives);

    void changeDetermination(int determinations);

    List<Marker> getCharacterMarkers();
}
