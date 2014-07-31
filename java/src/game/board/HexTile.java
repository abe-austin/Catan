package game.board;

import game.pieces.BoardPiece;

import java.util.ArrayList;

import shared.definitions.HexType;
import shared.definitions.PieceType;
import player.Player;
import shared.locations.EdgeDirection;
import shared.locations.VertexDirection;
import shared.locations.HexLocation;

public class HexTile {

	private int x;
	private int y;
	private boolean hasRobber;

	protected HexType myType;
	protected HexLocation location;

	public Edge northWestEdge;
	public Edge northEdge;
	public Edge northEastEdge;
	public Edge southEastEdge;
	public Edge southEdge;
	public Edge southWestEdge;

	public Corner westCorner;
	public Corner northWestCorner;
	public Corner northEastCorner;
	public Corner eastCorner;
	public Corner southEastCorner;
	public Corner southWestCorner;
        
        public ArrayList<Edge> getEdges() {
            ArrayList<Edge> edges = new ArrayList<>();
            
            edges.add(northEdge);
            edges.add(northWestEdge);
            edges.add(northEastEdge);
            edges.add(southEdge);
            edges.add(southEastEdge);
            edges.add(southWestEdge);
            
            return edges;
        }
        
        public ArrayList<Corner> getCorners() {
            ArrayList<Corner> corners = new ArrayList<>();
            
            corners.add(westCorner);
            corners.add(northWestCorner);
            corners.add(northEastCorner);
            corners.add(eastCorner);
            corners.add(southEastCorner);
            corners.add(southWestCorner);
            
            return corners;
        }

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

	public boolean getHasRobber() {
		return hasRobber;
	}

	public void setHasRobber(boolean value) {
		hasRobber = value;
	}

	/**
	 * adds edges the hextile
	 * @param edgeToAdd
	 * @param edgeDirection
	 */
	public void addEdge(Edge edgeToAdd, EdgeDirection edgeDirection) {
		switch (edgeDirection) {
		case NorthWest:
			northWestEdge = edgeToAdd;
			break;
		case North:
			northEdge = edgeToAdd;
			break;
		case NorthEast:
			northEastEdge = edgeToAdd;
			break;
		case SouthEast:
			southEastEdge = edgeToAdd;
			break;
		case South:
			southEdge = edgeToAdd;
			break;
		case SouthWest:
			southWestEdge = edgeToAdd;
			break;
		default:
			break;
		}
	}

	/**
	 * adds corner to the hextile
	 * @param cornerToAdd
	 * @param vertexDirection
	 */
	public void addCorner(Corner cornerToAdd, VertexDirection vertexDirection) {
		switch (vertexDirection) {
		case West:
			westCorner = cornerToAdd;
			break;
		case NorthWest:
			northWestCorner = cornerToAdd;
			break;
		case NorthEast:
			northEastCorner = cornerToAdd;
			break;
		case East:
			eastCorner = cornerToAdd;
			break;
		case SouthEast:
			southEastCorner = cornerToAdd;
			break;
		case SouthWest:
			southWestCorner = cornerToAdd;
			break;
		default:
			break;
		}
	}

	/**
	 *
	 * @param e
	 *            Edge to build on
	 * @param p
	 *            players road to build
	 */
	public void buildRoad(Edge edge, Player player) {
		BoardPiece theRoad = player.getAvailableBoardPiece(PieceType.ROAD);
		edge.buildStructure(theRoad);
		//theRoad.setActive(true);
	}// Build the requested object

	/**
	 *
	 * @param c
	 *            Corner to build on
	 * @param p
	 *            players settlement/city to build
	 */
	public void buildSettlement(Corner corner, Player player) {
		BoardPiece theSettlement = player.getAvailableBoardPiece(PieceType.SETTLEMENT);
		corner.buildStructure(theSettlement);
		//theSettlement.setActive(true);
	}

	/**
	 *
	 * @param c
	 *            Corner to build on
	 * @param p
	 *            players settlement/city to build
	 */
	public void buildCity(Corner corner, Player player) {
		BoardPiece theCity = player.getAvailableBoardPiece(PieceType.CITY);
		//BoardPiece theSettlement = corner.getStructure();
		// theSettlement.setActive(false); had to comment it out, was null from
		// parsed data
		corner.buildStructure(theCity);
		//theCity.setActive(true);
	}

	public HexType getType() {
		return null;
	};
}