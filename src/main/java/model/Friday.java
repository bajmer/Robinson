package model;

import controller.engine.GameEventsListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Friday implements ICharacter {
    private Logger logger = LogManager.getLogger(Friday.class);
    private int life;
    private int determination;
    private GameEventsListener listener;

    public Friday(int life, GameEventsListener listener) {
        this.life = life;
        this.determination = 0;
        this.listener = listener;
    }

    public GameEventsListener getListener() {
        return listener;
    }

    public void setListener(GameEventsListener listener) {
        this.listener = listener;
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
    public void changeLife(int lives) {
        life += lives;
        logger.debug("Liczba żyć Piętaszka wynosi:" + life);
        if (life <= 0) listener.handleFridayDeath();
    }

    @Override
    public void changeDetermination(int determinations) {
        determination += determinations;
        if (determination < 0) {
            changeLife(determination);
            determination = 0;
        }
        logger.debug("Liczba żetonów determinacji Piętaszka wynosi: " + determination);
    }
}
