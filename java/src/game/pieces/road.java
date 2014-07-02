package game.pieces;

import player.player;
import shared.definitions.Cost;
import shared.definitions.PieceType;

public class road extends BoardPiece {
	
	private boolean isActivated;
	private player owner;
	private Cost c;
	
	public road(player own) {
		isActivated = false;
		owner = own;
		c = Cost.ROAD;
	}

        @Override
        public PieceType getPieceType() {
            return PieceType.ROAD;
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
	 * @return Who the controlling player is
	 */
	public player getOwner() {
		return owner;
	}
	
	/**
	 * @return What the associated cost is
	 */
	public Cost getCost() {
		return c;
	}
}