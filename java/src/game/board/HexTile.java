package game.board;

import game.pieces.BoardPiece;

import java.util.List;

import shared.definitions.HexType;
import shared.definitions.PieceType;
import player.Player;
import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import shared.locations.HexLocation;

public abstract class HexTile {
	
	private int x;
	private int y;
	
	protected HexType myType;
	protected HexLocation location;
		
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
	
	public void setCoordinates(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	
	public void addEdge(Edge edgeToAdd, EdgeDirection edgeDirection) {
		switch(edgeDirection) {
			case NorthWest: northWestEdge = edgeToAdd; break;
			case North: northEdge = edgeToAdd; break;
			case NorthEast: northEastEdge = edgeToAdd; break;
			case SouthEast: southEastEdge = edgeToAdd; break;
			case South: southEdge = edgeToAdd; break;
			case SouthWest: southWestEdge = edgeToAdd; break;
			default: break;
		}
	}
	
	public void addCorner(Corner cornerToAdd, VertexDirection vertexDirection) {
		switch(vertexDirection) {
		case West: westCorner= cornerToAdd; break;
		case NorthWest: northWestCorner = cornerToAdd; break;
		case NorthEast: northEastCorner = cornerToAdd; break;
		case East: eastCorner = cornerToAdd; break;
		case SouthEast: southEastCorner = cornerToAdd; break;
		case SouthWest: southWestCorner = cornerToAdd; break;
		default: break;
	}
	}

      
        /**
         *
         * @param e Edge to build on
         * @param p players road to build
         */
	public void buildRoad(Edge edge, Player player) {
		BoardPiece theRoad = player.getAvailableBoardPiece(PieceType.ROAD);
		edge.buildStructure(theRoad);
		theRoad.setActive(true);
	}//Build the requested object

        /**
         *
         * @param c Corner to build on
         * @param p players settlement/city to build
         */
	public void buildSettlement(Corner corner,  Player player) {
		BoardPiece theSettlement = player.getAvailableBoardPiece(PieceType.SETTLEMENT);
		corner.buildStructure(theSettlement);
		theSettlement.setActive(true);
	}

        /**
         *
         * @param c Corner to build on
         * @param p players settlement/city to build
         */
	public void buildCity(Corner corner,  Player player) {
		BoardPiece theCity = player.getAvailableBoardPiece(PieceType.CITY);
		BoardPiece theSettlement = corner.getStructure();
		theSettlement.setActive(false);
		corner.buildStructure(theCity);
		theCity.setActive(true);
	}

        abstract HexType getType();
}