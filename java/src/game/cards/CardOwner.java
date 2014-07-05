package game.cards;

import java.util.HashSet;
import java.util.Set;
import shared.definitions.DevCardType;
import shared.definitions.ResourceType;
import shared.definitions.SpecialCardType;

/**
 *
 * @author Kevin MacMaster
 */
public class CardOwner {
        protected Set<ResourceCard> resourceCards;
        protected Set<DevelopmentCard> developmentCards;
        protected Set<SpecialCard> specialCards;

        public CardOwner() {
            resourceCards = new HashSet<ResourceCard>();
            developmentCards = new HashSet<DevelopmentCard>();
            specialCards = new HashSet<SpecialCard>();
        }

        /**
         * adds a resource to the owner's resource cards
         * @param resourceCard the card to be given to the owner
         */
        public void addResourceCard(ResourceCard resourceCard){
            resourceCards.add(resourceCard);
        }
        /**
         * removes a resource card from the owner and gives it to someone else (or bank)
         * @pre   owner has at least one card of the specified resource type
         * @post  the owner has one less resource card of the specified type
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
         * adds a specified development card to the owner's development cards
         * @param devCard the development card to be given to the owner
         */
        public void addDevelopmentCard(DevelopmentCard devCard){
            developmentCards.add(devCard);
        }

        /**
         * use the specific development card
         * @pre   owner has at least one card of the specified development type
         * @post  the owner has one less development card of the specified type
         * @param devCardType the type of development card to be given
         * @return a Development card of the given type
         */
        public DevelopmentCard giveDevelopmentCard(DevCardType devCardType){
            for(DevelopmentCard card : developmentCards) {
                if(card.getDevelopment().equals(devCardType)) {
                    developmentCards.remove(card);
                    return card;
                }
            }

            return null;
        }

        /**
         * gives a special card (LargestArmy or LongestRoad) to the owner
         * @param specialCard the special card to be given to the owner
         */
        public void addSpecialCard(SpecialCard specialCard){
            specialCards.add(specialCard);
        }

        /**
         * removes a specified special card from the owner and gives it to someone else
         * @pre   the owner has the special card of the specified type
         * @post  the owner does not have a card of the specified type
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
         * @return true if the owner has at the number needed, false otherwise
         */
        public boolean hasResource(ResourceType resourceType, int number){
            for(ResourceCard card : resourceCards) {
                if(card.getResource().equals(resourceType))
                    number--;
            }

            return number <= 0;
        }

        /**
         * same as above with one resource
         */
        public boolean hasResource(ResourceType resourceType) {
            return hasResource(resourceType, 1);
        }

        /**
         * @param devCardType the type of development card being sought after
         * @return true if the owner has at least one, false otherwise
         */
        public boolean hasDevelopmentCard(DevCardType devCardType){
            for(DevelopmentCard card : developmentCards) {
                if(card.getDevelopment().equals(devCardType))
                    return true;
            }

            return false;
        }

        /**
         * @return true if the owner has the longest road special card
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
         * @return true if the owner has the largest army special card
         *         false otherwise
         */
        public boolean hasLargestArmy(){
            for(SpecialCard card : specialCards) {
                if(card.getSpecial().equals(SpecialCardType.LARGEST_ARMY))
                    return true;
            }

            return false;
        }
}
