package model;

public class Scenario implements IScenario {
    private int id;
    private int maxRoundNumber;
    private int round;

    public Scenario(int id) {
        this.id = id;
        this.round = 1;
        switch (this.id) {
            case 1:
                this.maxRoundNumber = 12;
                break;
            case 2:
                this.maxRoundNumber = 10;
                break;
            case 3:
                this.maxRoundNumber = 12;
                break;
            case 4:
                this.maxRoundNumber = 12;
                break;
            case 5:
                this.maxRoundNumber = 12;
                break;
            case 6:
                this.maxRoundNumber = 12;
                break;
            case 7:
                this.maxRoundNumber = 12;
                break;
            default:
                this.maxRoundNumber = 12;
                break;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaxRoundNumber() {
        return maxRoundNumber;
    }

    public void setMaxRoundNumber(int maxRoundNumber) {
        this.maxRoundNumber = maxRoundNumber;
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
        this.round += 1;
        return this.round > maxRoundNumber;
    }
}
