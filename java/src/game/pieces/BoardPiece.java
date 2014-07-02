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
     * @param set BoardPiece activity
     */
    public void setActive(boolean set) {
        active = set;
    }

    abstract public PieceType getPieceType();
    abstract public Cost getCost();
    
}
