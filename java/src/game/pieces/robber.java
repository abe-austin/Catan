package game.pieces;

import game.board.boardCoord;

public class robber {
	
	private boardCoord location;
	
	/**
	 * @param loc the initial location of the robber (where the desert is)
	 */
	public robber(boardCoord loc) {
		location = loc;
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