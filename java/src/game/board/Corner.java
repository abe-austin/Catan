package game.board;

import java.util.List;

import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import game.pieces.BoardPiece;

public class Corner {
	
	private List<VertexLocation> location;
	BoardPiece builtStructure;
	
	/**
	 * @param loc where on the map the corner is located, comprised of the hex coordinates, and which corner on that hex this comprises
	 * @param tilesBelongedTo, the list of tile(s) that share this particular corner
	 * @pre initialization of this assumes that the world's hexes have been created by the game already, so that they can be included 
	 * in the list for this object's initialization.
	 */
	public Corner(List<VertexLocation> loc) {
		this.location = loc;
		builtStructure = null;		
	}
	
	/**
	 * @return whether there has been an object built here already
	 */
	public boolean hasStructure() {
		return(builtStructure != null);
	}
	
	/**
	 * @return what structure is already built here (for determining
         * whether there is a settlement that a city can be built on)
	 */
	public BoardPiece getStructure() {
		return builtStructure;
	}
	
	/**
         * @pre if there is a structure there, it was a settlement
	 * @param road the game piece that is being placed on this edge
         * @post there is now a BoardPiece on this corner (city or settlement)
	 */
	public void buildStructure(BoardPiece building) {
                if(builtStructure != null)
                    builtStructure.setActive(false);

                builtStructure = building;

                builtStructure.setActive(true);
	}
	
	/**
	 * @return the list of locations that this corner is associated with
	 */
	public List<VertexLocation> getLocations() {
		return location;
	}
	
}