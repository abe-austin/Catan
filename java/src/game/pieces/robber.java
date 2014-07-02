package game.pieces;

import shared.locations.HexLocation;
import shared.definitions.PieceType;

public class robber {
	
	private HexLocation location;
	
	/**
	 * @param loc the initial location of the robber (where the desert is)
	 */
	public robber(HexLocation loc) {
		location = loc;
	}

        public PieceType getPieceType() {
            return PieceType.ROBBER;
        }
	
	/**
	 * @param loc new location for the robber (which tile the player has moved it to) 
	 */
	public void updateLocation(HexLocation loc) {
		location = loc;
	}
	
	/**
	 * @return the robber's current location
	 */
	public HexLocation getLocation() {
		return location;
	}
}