package player;

import game.cards.CardOwner;
import game.cards.DevelopmentCard;
import game.cards.ResourceCard;
import game.cards.SpecialCard;
import game.pieces.BoardPiece;
import game.pieces.city;
import game.pieces.road;
import game.pieces.settlement;
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
                boardPieces.add(new settlement(this));

            for(int i = 0; i < 4; i++)
                boardPieces.add(new city(this));

            for(int i = 0; i < 15; i++)
                boardPieces.add(new road(this));

            // TO DO add more here
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

        @Override
        public DevelopmentCard giveDevelopmentCard(DevCardType devCardType) {
            DevelopmentCard card = super.giveDevelopmentCard(devCardType);

            if(devCardType.equals(DevCardType.SOLDIER) && card != null)
                soldiersPlayed++;

            return null;
        }

        @Override
        public SpecialCard giveSpecialCard(SpecialCardType specialCardType){
            SpecialCard card = super.giveSpecialCard(specialCardType);

            if(card != null) {
                points.subtractPoint();
                points.subtractPoint();
            }

            return card;
        }

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
        public Points getPoints() {
            return points;
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
         * @return the number of cards in hand
         */
        public int getHandSize() {
            return resourceCards.size();
        }
}