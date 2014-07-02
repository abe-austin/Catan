package game.pieces;

import player.Player;
import shared.definitions.Cost;
import shared.definitions.PieceType;

public class road extends BoardPiece {
	private Cost cost;
	
	public road(Player owner) {
            super(owner);
            cost = Cost.ROAD;
	}

        @Override
        public PieceType getPieceType() {
            return PieceType.ROAD;
        }
	
	/**
	 * @return What the associated cost is
	 */
	public Cost getCost() {
		return cost;
	}
}