package game;

/**
 *
 * @author Kevin MacMaster
 */
public class TurnTracker {
    private String status;
    private int currentTurn;
    private int longestRoad;
    private int largestArmy;
    private boolean setup;
    private boolean firstTurn;
    

    public TurnTracker() {
        status = "Setup";
        currentTurn = 0;
        longestRoad=-1;
        largestArmy=-1;
        setup = false;
        firstTurn = false;
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

    /**
     * @return the setup
     */
    public boolean isSetup() {
        return setup;
    }

    /**
     * @param setup the setup to set
     */
    public void setSetup(boolean setup) {
        this.setup = setup;
    }

    /**
     * @return the firstTurn
     */
    public boolean isFirstTurn() {
        return firstTurn;
    }

    /**
     * @param firstTurn the firstTurn to set
     */
    public void setFirstTurn(boolean firstTurn) {
        this.firstTurn = firstTurn;
    }

}
