package model;

import model.enums.ProfessionType;
import model.enums.SexType;
import model.enums.SpecialSkillType;

import java.util.ArrayList;
import java.util.List;

public class Character implements ICharacter {
    private static final int MAX_LIFE = 10;
    private ProfessionType profession;
    private SexType sex;
    private int life;
    private int determination;
    private boolean firstPlayer;
    private List<SpecialSkillType> specialSkills;
    private List<Integer> moraleDown;

    public Character(ProfessionType profession, SexType sex) {
        this.profession = profession;
        this.sex = sex;
        this.life = MAX_LIFE;
        this.determination = 0;
        this.firstPlayer = false;
        this.specialSkills = new ArrayList<>();
        this.moraleDown = new ArrayList<>();

        switch (profession) {
            case CARPENTER:
                this.specialSkills.add(SpecialSkillType.ECONOMICAL_CONSTRUCTION);
                this.specialSkills.add(SpecialSkillType.CRAFT);
                this.specialSkills.add(SpecialSkillType.NEW_IDEA);
                this.specialSkills.add(SpecialSkillType.HANDYMAN);
                break;
            case COOK:
                this.specialSkills.add(SpecialSkillType.GRANDMAS_RECIPE);
                this.specialSkills.add(SpecialSkillType.SHARP_EYE);
                this.specialSkills.add(SpecialSkillType.NAIL_SOUP);
                this.specialSkills.add(SpecialSkillType.HOOCH);
                break;
            case EXPLORER:
                this.specialSkills.add(SpecialSkillType.LUCKY_MAN);
                this.specialSkills.add(SpecialSkillType.RECONNAISSANCE);
                this.specialSkills.add(SpecialSkillType.MOTIVATIONAL_SPEECH);
                this.specialSkills.add(SpecialSkillType.SCOUT);
                break;
            case SOLDIER:
                this.specialSkills.add(SpecialSkillType.TRACKING);
                this.specialSkills.add(SpecialSkillType.HOUNTING);
                this.specialSkills.add(SpecialSkillType.FURY);
                this.specialSkills.add(SpecialSkillType.EMERGENCY_PLAN);
                break;
        }

        this.specialSkills = specialSkills;
        this.moraleDown = moraleDown;
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
