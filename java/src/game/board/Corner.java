package game.board;

import java.util.List;

import shared.locations.VertexLocation;
import game.pieces.BoardPiece;

public class Corner {
	
	private VertexLocation location;
	private List<HexTile> tilesBelongedTo;
	BoardPiece builtStructure;
	
	public Corner(VertexLocation loc, List<HexTile> tilesBelongedTo) {
		this.location = loc;
		this.tilesBelongedTo = tilesBelongedTo;
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
	 * @return the tiles that share this edge
	 */
	public List<HexTile> getConnectedHexes() {
		return tilesBelongedTo;
	}
	
}