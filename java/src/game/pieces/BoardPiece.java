package game.pieces;

import player.Player;
import shared.definitions.Cost;
import shared.definitions.PieceType;

/**
 *
 * @author Kevin MacMaster
 */
public interface BoardPiece {
    abstract public PieceType getPieceType();
    abstract public Cost getCost();
    abstract public Player getOwner();
    abstract public boolean isActive();
    abstract public void setActive(boolean set);
}
