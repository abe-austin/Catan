package game.board;

import java.util.List;

import shared.locations.EdgeLocation;
import game.pieces.BoardPiece;

public class Edge {
	
	private EdgeLocation location;
	private List<HexTile> tilesBelongedTo;
	BoardPiece builtStructure;
	
	/**
	 * @param loc where on the map the edge is located, comprised of the hex coordinates, and which edge on that hex this comprises
	 * @param tilesBelongedTo, the list of tile(s) that share this particular edge
	 * @pre initialization of this assumes that the world's hexes have been created by the game already, so that they can be included 
	 * in the list for this object's initialization.
	 */
	public Edge(EdgeLocation loc, List<HexTile> tilesBelongedTo) {
		this.location = loc;
		this.tilesBelongedTo = tilesBelongedTo;
		builtStructure = null;		
	}
	
	/**
	 * @return whether there has been an object built here already
	 */
	public boolean hasStructure()
	{
		return(builtStructure != null);
	}
	
	/**
	 * @param road the game piece that is being placed on this Edge
	 */
	public void buildStructure(BoardPiece road){
		builtStructure = road;
	}
	
	/**
	 * @return the tiles that share this Edge
	 */
	public List<HexTile> getConnectedHexes(){
		return tilesBelongedTo;
	}
	
}