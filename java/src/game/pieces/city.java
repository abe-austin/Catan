package game.pieces;

import player.Player;
import shared.definitions.Cost;
import shared.definitions.PieceType;

public class city extends BoardPiece {
	private Cost cost;
	
	public city(Player owner) {
            super(owner);
            cost = Cost.CITY;
	}

        @Override
        public PieceType getPieceType() {
            return PieceType.CITY;
        }
	
	/**
	 * @return What the associated cost is
	 */
	public Cost getCost() {
		return cost;
	}
}