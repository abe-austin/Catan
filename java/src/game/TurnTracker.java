package game;

import shared.definitions.GameState;

/**
 *
 * @author Kevin MacMaster
 */
public class TurnTracker {
    private String status;
    private int currentTurn;
    private int longestRoad;
    private int largestArmy;

    

    public TurnTracker() {
        status = "Setup";
        currentTurn = 0;
        longestRoad=-1;
        largestArmy=-1;
    }

    public TurnTracker(String status, int currentTurn, int longestRoad, int largestArmy) {
        this.status = status;
        this.currentTurn = currentTurn;
        this.longestRoad = longestRoad;
        this.largestArmy = largestArmy;
    }

    public int getLongestRoad() {
        return longestRoad;
    }

    public void setLongestRoad(int longestRoad) {
        this.longestRoad = longestRoad;
    }

    public int getLargestArmy() {
        return largestArmy;
    }

    public void setLargestArmy(int largestArmy) {
        this.largestArmy = largestArmy;
    }
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the GameState to set
     */
    public void setStatus(String status) {
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
