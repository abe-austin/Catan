package game.pieces;

import player.Player;
import shared.definitions.Cost;
import shared.definitions.PieceType;

/**
 *
 * @author Kevin MacMaster
 */
public abstract class BoardPiece {
	private Player owner;
	private boolean active;

	public BoardPiece(Player owner) {
		active = false;
		this.owner = owner;
	}

	/**
	 * 
	 * @return BoardPiece owner
	 */
	public Player getOwner() {
		return owner;
	}

	/**
	 *
	 * @return BoardPiece activity
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * 
	 * @param set
	 *            BoardPiece activity
	 */
	public void setActive(boolean set) {
		active = set;
	}

	/**
	 * 
	 * @return the type of board piece
	 */
	abstract public PieceType getPieceType();

	/**
	 *
	 * @return the cost to place board piece
	 */
	abstract public Cost getCost();

}
