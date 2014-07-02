package player;

import game.cards.DevelopmentCard;
import game.cards.ResourceCard;
import game.cards.SpecialCard;
import game.pieces.BoardPiece;
import java.util.HashSet;
import java.util.Set;
import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.definitions.SpecialCardType;

public class Player {
	private CatanColor color;
        private Set<BoardPiece> boardPieces;
        private Set<ResourceCard> resourceCards;
        private Set<DevelopmentCard> developmentCards;
        private Set<SpecialCard> specialCards;
        private Points points;
        private int soldiersPlayed;

        public Player(CatanColor color) {
            this.color = color;
            soldiersPlayed = 0;
            points = new Points();

            resourceCards = new HashSet<ResourceCard>();
            developmentCards = new HashSet<DevelopmentCard>();
            specialCards = new HashSet<SpecialCard>();

            // initalize board pieces
            
        }

        /**
     * adds a resource to the bank's resource cards
     * @param resourceCard the card to be add to the bank
     */
    public void addResourceCard(ResourceCard resourceCard){
        resourceCards.add(resourceCard);
    }
    /**
     * removes a resource card from the bank and gives it to someone else
     * @pre   bank has at least one card of the specified resource type
     * @post  the bank has one less resource card of the specified type
     * @param resourceType the type of resource to be given
     * @return a ResourceCard of the specified type
     */
    public ResourceCard giveResourceCard(ResourceType resourceType){
        for(ResourceCard card : resourceCards) {
            if(card.getResource().equals(resourceType)) {
                resourceCards.remove(card);
                return card;
            }
        }

        return null;
    }
    /**
     * adds a specified development card to the bank's development cards
     * @param devCard the development card to be added to the bank
     */
    public void addDevelopmentCard(DevelopmentCard devCard){
        developmentCards.add(devCard);
    }
    
    /**
     * use the specific development card
     * @pre   player has at least one card of the specified development type
     * @post  the player has one less development card of the specified type
     * @param devCardType the type of development card to be given
     * @return a Development card of the given type
     */
    public DevelopmentCard useDevelopmentCard(DevCardType devCardType){
        for(DevelopmentCard card : developmentCards) {
            if(card.getDevelopment().equals(devCardType)) {
                developmentCards.remove(card);

                if(devCardType.equals(DevCardType.SOLDIER))
                    soldiersPlayed++;

                return card;
            }
        }

        return null;
    }
    
    /**
     * gives a special card (LargestArmy or LongestRoad) to the player
     * @param specialCard the special card to be given to the player
     */
    public void addSpecialCard(SpecialCard specialCard){
        specialCards.add(specialCard);
    }

    /**
     * removes a specified special card from the player and gives it to someone else
     * @pre   the player has the special card of the specified type
     * @post  the player does not have a card of the specified type
     * @param specialCardType the type of special card to be given
     *                          (LARGEST_ARMY or LONGEST_ROAD)
     * @return the specified SpecialCard
     */
    public SpecialCard giveSpecialCard(SpecialCardType specialCardType){
        for(SpecialCard card : specialCards) {
            if(card.getSpecial().equals(specialCardType)) {
                specialCards.remove(card);
                return card;
            }
        }

        return null;
    }

    /**
     * @param resourceType the type of resource being sought after
     * @return true if the player has at the number needed, false otherwise
     */
    public boolean hasResource(ResourceType resourceType, int number){
        for(ResourceCard card : resourceCards) {
            if(card.getResource().equals(resourceType))
                number--;
        }

        return number <= 0;
    }

    /**
     * @param devCardType the type of development card being sought after
     * @return true if the player has at least one, false otherwise
     */
    public boolean hasDevelopmentCard(DevCardType devCardType){
        for(DevelopmentCard card : developmentCards) {
            if(card.getDevelopment().equals(devCardType))
                return true;
        }

        return false;
    }

    /**
     * @return true if the player has the longest road special card
     *         false otherwise
     */
    public boolean hasLongestRoad(){
        for(SpecialCard card : specialCards) {
            if(card.getSpecial().equals(SpecialCardType.LONGEST_ROAD))
                return true;
        }

        return false;
    }

    /**
     * @return true if the player has the largest army special card
     *         false otherwise
     */
    public boolean hasLargestArmy(){
        for(SpecialCard card : specialCards) {
            if(card.getSpecial().equals(SpecialCardType.LARGEST_ARMY))
                return true;
        }

        return false;
    }

    /**
     * @return the color
     */
    public CatanColor getColor() {
        return color;
    }

    /**
     * @return the points
     */
    public Points getPoints() {
        return points;
    }

    /**
     * @return the soldiersPlayed
     */
    public int getSoldiersPlayed() {
        return soldiersPlayed;
    }

    /**
     * @return the number of cards in hand
     */
    public int getHandSize() {
        return resourceCards.size();
    }
}