package model;

import model.enums.SpecialSkillType;

public interface ICharacter {
    void useSpecialSkill(SpecialSkillType specialSkillType);

    int checkIfMoraleDownAndDecrease(int lifesNumber);

    void increaseLife(int lifesNumber);

    void decreaseLife(int lifesNumber);

    void increaseDetermination(int determinationsNumber);

    void decreaseDetermination(int determinationsNumber);

    boolean checkIfDead();
}
