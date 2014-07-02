package game.bank;

import game.cards.*;
import java.util.Set;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.definitions.SpecialCardType;
/**
 * the bank the contains sets of ResourceCards, DevelopmentCards and SpecialCards
 * @author Cory
 */
public class Bank {
    private Set<ResourceCard> resourceCards;
    private Set<DevelopmentCard> developmentCards;
    private Set<SpecialCard> specialCards;
    /**
     * default constructor
     */
    public Bank(){}
    /**
     * adds a resource to the bank's resource cards
     * @param resourceCard the card to be add to the bank
     */
    public void addResourceCard(ResourceCard resourceCard){
        
    }
    /**
     * removes a resource card from the bank and gives it to someone else
     * @pre   bank has at least one card of the specified resource type
     * @post  the bank has one less resource card of the specified type
     * @param resourceType the type of resource to be given
     * @return a ResourceCard of the specified type
     */
    public ResourceCard giveResourceCard(ResourceCardType resourceType){
        return null;
    }
    /**
     * adds a specified development card to the bank's development cards
     * @param devCard the development card to be added to the bank
     */
    public void addDevelopmentCard(DevelopmentCard devCard){
        
    }
    /**
     * removes a specific development card from the bank and gives it to someone else
     * @pre   bank has at least one card of the specified development type
     * @post  the bank has one less development card of the specified type
     * @param devCardType the type of development card to be given
     * @return a Development card of the given type
     */
    public DevelopmentCard giveDevelopmentCard(DevCardType devCardType){
        return null;
    }
    /**
     * adds a special card (LargestArmy or LongestRoad) to the bank
     * @param specialCard the special card to be added to the bank
     */
    public void addSpecialCard(SpecialCard specialCard){
        
    }
    /**
     * removes a specified special card from the bank and gives it to someone else
     * @pre   the bank has the special card of the specified type
     * @post  the bank does not have a card of the specified type
     * @param specialCardType the type of special card to be given
     *                          (LARGEST_ARMY or LONGEST_ROAD)
     * @return the specified SpecialCard
     */
    public SpecialCard giveSpecialCard(SpecialCardType specialCardType){
        return null;
    }
    /**
     * 
     * @param resourceType the type of resource being sought after
     * @return true if the bank has at least one, false otherwise
     */
    public boolean hasResource(ResourceType resourceType){
        return false;
    }
    /**
     * 
     * @param devCardType the type of development card being sought after
     * @return true if the bank has at least one, false otherwise
     */
    public boolean hasDevelopmentCard(DevCardType devCardType){
        return false;
    }
    /**
     * 
     * @return true if the bank has the longest road special card
     *         false otherwise
     */
    public boolean hasLongestRoad(){
        return false;
    }
    /**
     * 
     * @return true if the bank has the largest army special card
     *         false otherwise
     */
    public boolean hasLargestArmy(){
        return false;
    }
}
