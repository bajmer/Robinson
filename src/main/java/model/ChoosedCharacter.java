package model;

import model.enums.ProfessionType;
import model.enums.SexType;

public class ChoosedCharacter {
    private ProfessionType profession;
    private SexType sex;

    public ChoosedCharacter(ProfessionType profession, SexType sex) {

        this.profession = profession;
        this.sex = sex;
    }

    public ProfessionType getProfession() {
        return profession;
    }

    public void setProfession(ProfessionType profession) {
        this.profession = profession;
    }

    public SexType getSex() {
        return sex;
    }

    public void setSex(SexType sex) {
        this.sex = sex;
    }
}
