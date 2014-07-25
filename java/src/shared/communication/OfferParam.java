package shared.communication;

import java.util.Map;
import shared.definitions.ResourceType;

/**
 *
 * @author Cory
 */
public class OfferParam {
    private int brick;
    private int ore;
    private int sheep;
    private int wheat;
    private int wood;
    
    public OfferParam(Map<ResourceType,Integer> offer){
        brick=offer.get(ResourceType.BRICK);
        ore=offer.get(ResourceType.ORE);
        sheep=offer.get(ResourceType.SHEEP);
        wheat=offer.get(ResourceType.WHEAT);
        wood=offer.get(ResourceType.WOOD);
        
    }

    public int getBrick() {
        return brick;
    }

    public void setBrick(int brick) {
        this.brick = brick;
    }

    public int getOre() {
        return ore;
    }

    public void setOre(int ore) {
        this.ore = ore;
    }

    public int getSheep() {
        return sheep;
    }

    public void setSheep(int sheep) {
        this.sheep = sheep;
    }

    public int getWheat() {
        return wheat;
    }

    public void setWheat(int wheat) {
        this.wheat = wheat;
    }

    public int getWood() {
        return wood;
    }

    public void setWood(int wood) {
        this.wood = wood;
    }
    
    
    
}
