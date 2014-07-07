package game;

import shared.definitions.GameState;

/**
 *
 * @author Kevin MacMaster
 */
public class TurnTracker {
    private GameState status;
    private int currentTurn;

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

}
