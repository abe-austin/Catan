package game.pieces;

import shared.locations.HexLocation;
import shared.definitions.PieceType;

public class Robber {
	
	private HexLocation location;
	
	/**
	 * @param loc the initial location of the Robber (where the desert is)
	 */
	public Robber(HexLocation loc) {
		location = loc;
	}

        public PieceType getPieceType() {
            return PieceType.ROBBER;
        }
	
	/**
	 * @param loc new location for the Robber (which tile the player has moved it to)
	 * @post robber has now been moved to a different hex location
	 */
	public void updateLocation(HexLocation loc) {
		location = loc;
	}
	
	/**
	 * @return the Robber's current location
	 */
	public HexLocation getLocation() {
		return location;
	}
}