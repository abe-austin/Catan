package player;

import game.cards.CardOwner;
import game.cards.DevelopmentCard;
import game.cards.ResourceCard;
import game.cards.SpecialCard;
import game.pieces.BoardPiece;
import game.pieces.City;
import game.pieces.Road;
import game.pieces.Settlement;
import java.util.HashSet;
import java.util.Set;
import shared.definitions.CatanColor;
import shared.definitions.DevCardType;
import shared.definitions.PieceType;
import shared.definitions.SpecialCardType;



public class Player extends CardOwner {
	private CatanColor color;
        private Set<BoardPiece> boardPieces;
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
            for(int i = 0; i < 5; i++)
                boardPieces.add(new Settlement(this));

            for(int i = 0; i < 4; i++)
                boardPieces.add(new City(this));

            for(int i = 0; i < 15; i++)
                boardPieces.add(new Road(this));
        }

        /**
         * Returns an avaliable board piece of a given type
         * @param type of BoardPiece
         * @return BoardPiece that is not active
         */
        public BoardPiece getAvaliableBoardPiece(PieceType type) {
            for(BoardPiece piece : boardPieces) {
                if(piece.getPieceType().equals(type) && !piece.isActive())
                    return piece;
            }
            return null;
        }

        /**
         *
         * @param type of board piece
         * @return true if player has avaliable piece of type
         */
        public boolean hasAvaiableBoardPiece(PieceType type) {
            for(BoardPiece piece : boardPieces) {
                if(piece.getPieceType().equals(type) && !piece.isActive())
                    return true;
            }

            return false;
        }

        /**
         * In addition to Card Owner functionality
         *   this keeps track of soldier cards played
         *   @post The number of soldiers played has now been incremented by one
         */
        @Override
        public DevelopmentCard giveDevelopmentCard(DevCardType devCardType) {
            DevelopmentCard card = super.giveDevelopmentCard(devCardType);

            if(devCardType.equals(DevCardType.SOLDIER) && card != null)
                soldiersPlayed++;

            return null;
        }

        /**
         * In addition to Card Owner functionality
         *   this keeps track of points that come from
         *   special cards
         *   @post The players points have now been decremented by the special card's specified value
         */
        @Override
        public SpecialCard giveSpecialCard(SpecialCardType specialCardType){
            SpecialCard card = super.giveSpecialCard(specialCardType);

            if(card != null) {
                points.subtractPoint();
                points.subtractPoint();
            }

            return card;
        }

        /**
         * In addition to Card Owner functionality
         *   this keeps track of points that come from
         *   special cards
         *   @post The players points have now been incremented by the the special card's specified value
         */
        @Override
        public void addSpecialCard(SpecialCard specialCard){
            super.addSpecialCard(specialCard);
            points.addPoint();
            points.addPoint();
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
        public int getPoints() {
            return points.getPoints();
        }

        /**
         * @return true if 10 or more points
         *          false otherwise
         */
        public boolean isVictory() {
            return points.isVictory();
        }

        /**
         * Adds point to player
         */
        public void addPoint() {
            points.addPoint();
        }

        /**
         * @return the soldiersPlayed
         */
        public int getSoldiersPlayed() {
            return soldiersPlayed;
        }

        /**
         * @return the number of cards in hand (resources)
         */
        public int getHandSize() {
            return resourceCards.size();
        }
        
        public boolean equals(Object other) {
            if (other == this) 
            	return true;
            if (other == null) 
            	return false;
            if (getClass() != other.getClass()) 
            	return false;
            Player p = (Player)other;
            return color.equals(p.getColor());
        }
}