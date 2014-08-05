package game.pieces;

import player.Player;
import shared.definitions.Cost;
import shared.definitions.PieceType;

public class Settlement extends BoardPiece {
	private Cost cost;
        
        public Settlement() {
            
        }

	public Settlement(Player owner) {
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