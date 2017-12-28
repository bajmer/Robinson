package model;

import model.enums.ProfessionType;
import model.enums.SexType;
import model.enums.SpecialSkillType;
import model.enums.cards.InventionType;

import java.util.List;

public class Character implements ICharacter {
    private ProfessionType profession;
    private SexType sex;
    private InventionType personalInvention;
    private List<SpecialSkillType> specialSkills;
    private List<Integer> moraleDown;
    private int life;
    private int determination;
    private boolean firstPlayer;

    public Character(ProfessionType profession, SexType sex, InventionType personalInvention, List<SpecialSkillType> specialSkills, List<Integer> moraleDown, int life) {
        this.profession = profession;
        this.sex = sex;
        this.personalInvention = personalInvention;
        this.specialSkills = specialSkills;
        this.moraleDown = moraleDown;
        this.life = life;
        this.determination = 0;
        this.firstPlayer = false;
    }

    public InventionType getPersonalInvention() {
        return personalInvention;
    }

    public void setPersonalInvention(InventionType personalInvention) {
        this.personalInvention = personalInvention;
    }

    public ProfessionType getProfession() {
        return profession;
    }

    public void setProfession(ProfessionType profession) {
        this.profession = profession;
    }

    public List<SpecialSkillType> getSpecialSkills() {
        return specialSkills;
    }

    public void setSpecialSkills(List<SpecialSkillType> specialSkills) {
        this.specialSkills = specialSkills;
    }

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getDetermination() {
        return determination;
    }

    public void setDetermination(int determination) {
        this.determination = determination;
    }

    public boolean isFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(boolean firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public List<Integer> getMoraleDown() {
        return moraleDown;
    }

    public void setMoraleDown(List<Integer> moraleDown) {
        this.moraleDown = moraleDown;
    }

    public void useSpecialSkill(SpecialSkillType specialSkillType) {

    }

    public int checkIfMoraleDownAndDecrease(int lifesNumber) {
        int moraleDownCounter = 0;
        for (int i = this.life + lifesNumber; i >= this.life; i--) {
            for (Integer j : this.moraleDown) {
                if (i == j) {
                    moraleDownCounter++;
                }
            }
        }
        return moraleDownCounter;
    }

    public void increaseLife(int lifesNumber) {
        this.life += lifesNumber;
    }

    public void decreaseLife(int lifesNumber) {
        this.life -= lifesNumber;
    }

    public void increaseDetermination(int determinationsNumber) {
        this.determination += determinationsNumber;
    }

    public void decreaseDetermination(int determinationsNumber) {
        this.determination -= determinationsNumber;
        if (this.determination < 0) {
            decreaseLife(0 - this.determination);
        }
    }

    public boolean checkIfDead() {
        return this.life < 1;
    }
}
