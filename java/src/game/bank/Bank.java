package game.bank;

import game.cards.*;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.definitions.SpecialCardType;
/**
 * the bank the contains sets of ResourceCards, DevelopmentCards and SpecialCards
 * @author Cory
 */
public class Bank extends CardOwner {
    /**
     * Initializes all starting cards
     */
    public Bank() {
        addSpecialCard(new SpecialCard(SpecialCardType.LARGEST_ARMY));
        addSpecialCard(new SpecialCard(SpecialCardType.LONGEST_ROAD));

        // Adding Resource Cards to Bank
        for(int i = 0; i < 19; i++) {
            addResourceCard(new ResourceCard(ResourceType.BRICK));
            addResourceCard(new ResourceCard(ResourceType.ORE));
            addResourceCard(new ResourceCard(ResourceType.WOOD));
            addResourceCard(new ResourceCard(ResourceType.SHEEP));
            addResourceCard(new ResourceCard(ResourceType.WHEAT));
        }

        // Adding Development Cards to Bank
        for(int i = 0; i < 14; i++) {
            if(i < 2) {
                addDevelopmentCard(new DevelopmentCard(DevCardType.MONOPOLY));
                addDevelopmentCard(new DevelopmentCard(DevCardType.ROAD_BUILD));
                addDevelopmentCard(new DevelopmentCard(DevCardType.YEAR_OF_PLENTY));
            }

            if(i < 5) {
                addDevelopmentCard(new DevelopmentCard(DevCardType.MONUMENT));
            }

            addDevelopmentCard(new DevelopmentCard(DevCardType.SOLDIER));
        }
    }
    
    public void setSpecialCards(boolean largestArmy, boolean longestRoad){
        specialCards.clear();
        if(largestArmy){
            addSpecialCard(new SpecialCard(SpecialCardType.LARGEST_ARMY));
        }
        if(longestRoad){
            addSpecialCard(new SpecialCard(SpecialCardType.LONGEST_ROAD));
        }
    }
    
    public void setDevCards(int monopoly, int roadBuild, int yearOfPlenty, int monument, int soldier){
        developmentCards.clear();
        for(int i=0;i<monopoly;i++){
            addDevelopmentCard(new DevelopmentCard(DevCardType.MONOPOLY));
        }
        for(int i=0;i<roadBuild;i++){
            addDevelopmentCard(new DevelopmentCard(DevCardType.ROAD_BUILD));
        }
        for(int i=0;i<yearOfPlenty;i++){
            addDevelopmentCard(new DevelopmentCard(DevCardType.YEAR_OF_PLENTY));
        }
        for(int i=0;i<monument;i++){
            addDevelopmentCard(new DevelopmentCard(DevCardType.MONUMENT));
        }
        for(int i=0;i<soldier;i++){
            addDevelopmentCard(new DevelopmentCard(DevCardType.SOLDIER));
        }
    }
    public void setResources(int brick, int ore, int sheep, int wheat, int wood){
        resourceCards.clear();
        for(int i=0;i<brick;i++){
            addResourceCard(new ResourceCard(ResourceType.BRICK));
        }
        for(int i=0;i<ore;i++){
            addResourceCard(new ResourceCard(ResourceType.ORE));
        }
        for(int i=0;i<sheep;i++){
            addResourceCard(new ResourceCard(ResourceType.SHEEP));
        }
        for(int i=0;i<wheat;i++){
            addResourceCard(new ResourceCard(ResourceType.WHEAT));
        }
        for(int i=0;i<wood;i++){
            addResourceCard(new ResourceCard(ResourceType.WOOD));
        }
        
    }
    /**
     * gives a random development card from the bank
     * @pre   owner has at least one development card
     * @post  the owner has one less development card
     * @param devCardType
     * @return a development card from the bank
     */
    @Override
    public DevelopmentCard giveDevelopmentCard(DevCardType devCardType){
        //index of random development card
         int index = (int)(Math.random()*developmentCards.size());
         
         int i=0;
            for(DevelopmentCard card : developmentCards) {
                if(i<developmentCards.size() && i==index) {
                    developmentCards.remove(card);
                    return card;
                }
                i++;
            }

            return null;
        }
}
