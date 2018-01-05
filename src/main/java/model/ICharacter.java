package model;

import model.enums.SpecialSkillType;

public interface ICharacter {
    void useSpecialSkill(SpecialSkillType specialSkillType);

    void changeLife(int lives);

    void changeDetermination(int determinations);

    boolean isDead();
}
