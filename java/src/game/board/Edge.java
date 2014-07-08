package game.board;

import java.util.List;

import shared.locations.EdgeLocation;
import game.pieces.BoardPiece;

public class Edge {
	
	private List<EdgeLocation> location;
	BoardPiece builtStructure;
	
	/**
	 * @param loc where on the map the edge is located, comprised of the hex coordinates, and which edge on that hex this comprises
	 * @pre initialization of this assumes that the world's hexes have been created by the game already, so that their location can be included
	 * in the list for this object's initialization.
	 */
	public Edge(List<EdgeLocation> loc) {
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
	 * @param road the game piece that is being placed on this Edge
	 */
	public void buildStructure(BoardPiece road){
		builtStructure = road;
	}
	
	/**
	 * @return the list of locations that this edge is associated with
	 */
	public List<EdgeLocation> getLocations() {
		return location;
	}
	
}