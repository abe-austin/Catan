package game.board;

import java.util.List;

import shared.locations.EdgeLocation;
import game.pieces.BoardPiece;

public class Edge {
	
	private EdgeLocation loc;
	private List<HexTile> tilesBelongedTo;
	BoardPiece builtStructure;
	
	public Edge(EdgeLocation loc, List<HexTile> tilesBelongedTo) {
		this.loc = loc;
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