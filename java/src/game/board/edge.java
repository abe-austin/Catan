package game.board;

import java.util.List;

import shared.locations.EdgeLocation;
import game.pieces.BoardPiece;

public class edge {
	
	private EdgeLocation loc;
	private List<hexTile> tilesBelongedTo;
	BoardPiece builtStructure;
	
	public edge(EdgeLocation loc, List<hexTile> tilesBelongedTo) {
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
	 * @param road the game piece that is being placed on this edge
	 */
	public void buildStructure(BoardPiece road){
		builtStructure = road;
	}
	
	/**
	 * @return the tiles that share this edge
	 */
	public List<hexTile> getConnectedHexes(){
		return tilesBelongedTo;
	}
	
}