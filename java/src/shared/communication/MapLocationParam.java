package shared.communication;

import shared.locations.EdgeDirection;
import shared.locations.EdgeLocation;
import shared.locations.VertexDirection;
import shared.locations.VertexLocation;
import game.board.Corner;

public class MapLocationParam {
	private int x;
	private int y;
	private String direction;
	
	MapLocationParam(VertexLocation c) {
		setX(c.getX());
    	setY(c.getY());
    	setDirection(c.getDir());
	}
	
	MapLocationParam(EdgeLocation c) {
		setX(c.getHexLoc().getX());
    	setY(c.getHexLoc().getY());
    	setDirection(c.getDir());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(VertexDirection v) {
		String s = null;
		System.err.println(v);
		switch(v) {
			case West:
				s = "W";
				break;
			case NorthWest:
				s = "NW";
				break;
			case NorthEast:
				s = "NE";
				break;
			case East:
				s = "E";
				break;
			case SouthEast:
				s = "SE";
				break;
			case SouthWest:
				s = "SW";
				break;
		}
		System.err.println(s);
		this.direction = s;
	}
	
	public void setDirection(EdgeDirection v) {
		String s = null;
		System.err.println(v);
		switch(v) {
			case North:
				s = "N";
				break;
			case NorthWest:
				s = "NW";
				break;
			case NorthEast:
				s = "NE";
				break;
			case South:
				s = "S";
				break;
			case SouthEast:
				s = "SE";
				break;
			case SouthWest:
				s = "SW";
				break;
		}
		System.err.println(s);
		this.direction = s;
	}
}
