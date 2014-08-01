package game.pieces;

import controller.ControllerFacade;
import player.Player;
import shared.definitions.Cost;
import shared.definitions.PieceType;

/**
 *
 * @author Kevin MacMaster
 */
public abstract class BoardPiece {
	private String player;
	private boolean active;
	
	public BoardPiece(){
		
	}

	public BoardPiece(Player owner) {
		active = false;
		this.player = owner.getUsername();
	}

	/**
	 * 
	 * @return BoardPiece owner
	 */
	public Player getOwner() {
		return ControllerFacade.getSingleton().getPlayerByUsername(player);
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
