package game.board;

import java.util.List;

import shared.locations.VertexLocation;
import game.pieces.BoardPiece;

public class corner {
	
	private VertexLocation loc;
	private List<hexTile> tilesBelongedTo;
	BoardPiece builtStructure;
	
	public corner(VertexLocation loc, List<hexTile> tilesBelongedTo) {
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
	 * @return what structure is already built here (for determining whether there is a settlement that a city can be built on)
	 */
	public BoardPiece getStructure() {
		return builtStructure;
	}
	
	/**
	 * @param road the game piece that is being placed on this edge
	 */
	public void buildStructure(BoardPiece building){
		builtStructure = building;
		//If building city will need to delete the settlement
	}
	
	/**
	 * @return the tiles that share this edge
	 */
	public List<hexTile> getConnectedHexes(){
		return tilesBelongedTo;
	}
	
}