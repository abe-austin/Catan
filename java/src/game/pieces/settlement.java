package game.pieces;

import player.Player;
import shared.definitions.Cost;
import shared.definitions.PieceType;

public class settlement extends BoardPiece {
	private boolean isActivated;
	private Player owner;
	private Cost c;
	
	public settlement(Player own) {
		isActivated = false;
		owner = own;
		c = Cost.SETTLEMENT;
	}

        @Override
        public PieceType getPieceType() {
            return PieceType.SETTLEMENT;
        }
	
	/**
	 * Sets the piece as active on the board
	 */
	public void setActive() {
		isActivated = true;
	}
	
	/**
	 * @return Whether the piece is active on the board or not
	 */
	public boolean isActive() {
		return isActivated;
	}
	
	/**
	 * @return Who the controlling Player is
	 */
	public Player getOwner() {
		return owner;
	}
	
	/**
	 * @return What the associated cost is
	 */
	public Cost getCost() {
		return c;
	}
}