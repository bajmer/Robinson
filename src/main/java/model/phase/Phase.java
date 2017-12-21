package model.phase;

import model.enums.PhaseType;

public class Phase implements IPhase {
    public void playPhase(PhaseType phaseType) {
        switch (phaseType) {
            case EVENT_PHASE:
                break;
            case MORALE_PHASE:
                break;
            case PRODUCTION_PHASE:
                break;
            case ACTION_PHASE:
                break;
            case WEATHER_PHASE:
                break;
            case NIGHT_PHASE:
                break;
        }
    }
}
