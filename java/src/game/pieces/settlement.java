package game.pieces;

import player.Player;
import shared.definitions.Cost;
import shared.definitions.PieceType;

public class settlement extends BoardPiece {
	private Cost cost;
	
	public settlement(Player owner) {
            super(owner);
            cost = Cost.SETTLEMENT;
	}

        @Override
        public PieceType getPieceType() {
            return PieceType.SETTLEMENT;
        }
	
	/**
	 * @return What the associated cost is
	 */
	public Cost getCost() {
		return cost;
	}
}