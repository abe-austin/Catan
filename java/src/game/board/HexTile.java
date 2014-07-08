package game.board;

import game.pieces.BoardPiece;

import java.util.List;

import shared.definitions.HexType;
import shared.definitions.PieceType;
import player.Player;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;
import shared.locations.HexLocation;

public abstract class HexTile {
	
	protected HexType myType;
	protected HexLocation location;
	
//	/These all need to be initialized in the board-creation phase, or from the json
	protected Edge northWestEdge;
	protected Edge northEdge;
	protected Edge northEastEdge;
	protected Edge southEastEdge;
	protected Edge southEdge;
	protected Edge southWestEdge;
	
	protected Corner westCorner;
	protected Corner northWestCorner;
	protected Corner northEastCorner;
	protected Corner eastCorner;
	protected Corner southEastCorner;
	protected Corner southWestCorner;
	

      
        /**
         *
         * @param e Edge to build on
         * @param p players road to build
         */
	public void buildRoad(Edge edge, Player player) {
		BoardPiece theRoad = player.getAvaliableBoardPiece(PieceType.ROAD);
		edge.buildStructure(theRoad);
		theRoad.setActive(true);
	}//Build the requested object

        /**
         *
         * @param c Corner to build on
         * @param p players settlement/city to build
         */
	public void buildSettlement(Corner corner,  Player player) {
		BoardPiece theSettlement = player.getAvaliableBoardPiece(PieceType.SETTLEMENT);
		corner.buildStructure(theSettlement);
		theSettlement.setActive(true);
	}

        /**
         *
         * @param c Corner to build on
         * @param p players settlement/city to build
         */
	public void buildCity(Corner corner,  Player player) {
		BoardPiece theCity = player.getAvaliableBoardPiece(PieceType.CITY);
		BoardPiece theSettlement = corner.getStructure();
		theSettlement.setActive(false);
		corner.buildStructure(theCity);
		theCity.setActive(true);
	}

        abstract HexType getType();
}