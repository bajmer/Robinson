package model;

import model.elements.Marker;
import model.enums.SpecialSkillType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class Friday implements ICharacter {
    private Logger logger = LogManager.getLogger(Friday.class);
    private int life;
    private int determination;
    private List<Marker> characterMarkers;

    public Friday(int life, List<Marker> characterMarkers) {
        this.life = life;
        this.characterMarkers = characterMarkers;
        this.determination = 0;
    }

    public List<Marker> getCharacterMarkers() {
        return characterMarkers;
    }

    public void setCharacterMarkers(List<Marker> characterMarkers) {
        this.characterMarkers = characterMarkers;
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

    @Override
    public void useSpecialSkill(SpecialSkillType specialSkillType) {

    }

    @Override
    public void changeLife(int lives) {
        life += lives;
        logger.debug("Liczba żyć Piętaszka:" + life);

        if (life <= 0) {
//            listener.handleGameEnd();
        }
    }

    @Override
    public void changeDetermination(int determinations) {
        determination += determinations;
        if (determination < 0) {
            changeLife(determination);
            determination = 0;
        }
    }
}
