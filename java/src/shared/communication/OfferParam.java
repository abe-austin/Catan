package shared.communication;

import java.util.List;
import java.util.Map;
import shared.definitions.ResourceType;

/**
 *
 * @author Cory
 */
public class OfferParam {
    private int brick=0;
    private int ore=0;
    private int sheep=0;
    private int wheat=0;
    private int wood=0;
    
    public OfferParam(Map<ResourceType,Integer> offer){
        brick=offer.get(ResourceType.BRICK);
        ore=offer.get(ResourceType.ORE);
        sheep=offer.get(ResourceType.SHEEP);
        wheat=offer.get(ResourceType.WHEAT);
        wood=offer.get(ResourceType.WOOD);
        
    }
    public OfferParam(List<ResourceType> resources){
       for(ResourceType resource:resources){
           if(resource==ResourceType.BRICK){
               brick++;
           }
           else if(resource==ResourceType.ORE){
               ore++;
           }
           else if(resource==ResourceType.SHEEP){
               sheep++;
           }
           else if(resource==ResourceType.WHEAT){
               wheat++;
           }
           else if(resource==ResourceType.WOOD){
               wood++;
           }
       } 
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
