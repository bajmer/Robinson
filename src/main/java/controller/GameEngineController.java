package controller;

import model.Character;
import model.Scenario;
import model.enums.PhaseType;
import model.enums.ProfessionType;
import model.enums.SexType;

import java.util.ArrayList;
import java.util.List;

public class GameEngineController {
    private Scenario scenario;
    private List<Character> characters;
    private PhaseType phase;
    private int morale;
    private boolean isFriday;
    private boolean isDog;

    public GameEngineController(int scenarioId, List<ProfessionType> professionTypes, List<SexType> sexTypes, boolean isFriday, boolean isDog) {

        this.scenario = new Scenario(scenarioId);
        this.characters = new ArrayList<Character>();
        for (int i = 0; i < professionTypes.size(); i++) {
            ProfessionType profession = professionTypes.get(i);
            SexType sex = sexTypes.get(i);
            this.characters.add(new Character(profession, sex));
        }
        this.isFriday = isFriday;
        this.isDog = isDog;
    }
}
