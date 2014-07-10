package game;

import shared.definitions.GameState;

/**
 *
 * @author Kevin MacMaster
 */
public class TurnTracker {
    private GameState status;
    private int currentTurn;

    public TurnTracker() {
        status = GameState.Creating;
        currentTurn = 0;
    }

    /**
     * @return the status
     */
    public GameState getStatus() {
        return status;
    }

    /**
     * @param status the GameState to set
     */
    public void setStatus(GameState status) {
        this.status = status;
    }

    /**
     * @return the currentTurn
     */
    public int getCurrentTurn() {
        return currentTurn;
    }

    /**
     * @param currentTurn change the currentTurn to this integer
     */
    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    /**
     * Goes to next turn position
     */
    public void nextTurn() {
        currentTurn++;

        if(currentTurn > 3)
            currentTurn = 0;
    }

}
