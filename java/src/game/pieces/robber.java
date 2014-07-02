package game.pieces;

import game.board.boardCoord;
import shared.definitions.PieceType;

public class robber {
	
	private boardCoord location;
	
	/**
	 * @param loc the initial location of the robber (where the desert is)
	 */
	public robber(boardCoord loc) {
		location = loc;
	}

        public PieceType getPieceType() {
            return PieceType.ROBBER;
        }
	
	/**
	 * @param loc new location for the robber (which tile the player has moved it to) 
	 */
	public void updateLocation(boardCoord loc) {
		location = loc;
	}
	
	/**
	 * @return the robber's current location
	 */
	public boardCoord getLocation() {
		return location;
	}
}