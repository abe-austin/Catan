package shared.definitions;

/**
 *
 * @author Kevin MacMaster
 */
public enum Cost {
    SETTLEMENT(1,1,1,1,0),
    ROAD(1,1,0,0,0),
    CITY(0,0,0,2,3),
    DEVELOPMENTCARD(0,0,1,1,1);

    private int wood, brick, wool, wheat, ore;

    Cost(int wood, int brick, int wool, int wheat, int ore) {
        this.wood = wood;
        this.brick = brick;
        this.wool = wool;
        this.wheat = wheat;
        this.ore = ore;
    }

    /**
     * @return the wood
     */
    public int getWood() {
        return wood;
    }

    /**
     * @return the brick
     */
    public int getBrick() {
        return brick;
    }

    /**
     * @return the wool
     */
    public int getWool() {
        return wool;
    }

    /**
     * @return the wheat
     */
    public int getWheat() {
        return wheat;
    }

    /**
     * @return the ore
     */
    public int getOre() {
        return ore;
    }

    
}
