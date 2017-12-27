package model;

public class Scenario implements IScenario {
    private int id;
    private int roundsNumber;
    private int round;

    public Scenario(int id, int roundsNumber) {
        this.id = id;
        this.roundsNumber = roundsNumber;
        this.round = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoundsNumber() {
        return roundsNumber;
    }

    public void setRoundsNumber(int roundsNumber) {
        this.roundsNumber = roundsNumber;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void handleTotem() {
        switch (this.id) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            default:
                break;
        }
    }

    public void handleBook() {
        switch (this.id) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            default:
                break;
        }
    }

    public boolean nextRound() {
        round += 1;
        return round > roundsNumber;
    }

}
