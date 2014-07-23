package shared.locations;


/**
 * Represents the location of a vertex on a hex map
 */
public class VertexLocation {

	private HexLocation hexLoc;
	private int x;
	private int y;
	private VertexDirection direction;
	
	public VertexLocation(HexLocation hexLoc, VertexDirection dir) {
		setHexLoc(hexLoc);
		setX(hexLoc.getX());
		setY(hexLoc.getY());
		setDir(dir);
	}
	
	public HexLocation getHexLoc() {
		return hexLoc;
	}
	
	private void setHexLoc(HexLocation hexLoc) {		
		if (hexLoc == null) {
			throw new IllegalArgumentException("hexLoc cannot be null");
		}		
		this.hexLoc = hexLoc;
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

	public VertexDirection getDir() {
		return direction;
	}
	
	private void setDir(VertexDirection direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "VertexLocation [hexLoc=" + hexLoc + ", dir=" + direction + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((direction == null) ? 0 : direction.hashCode());
		result = prime * result
				+ ((hexLoc == null) ? 0 : hexLoc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VertexLocation other = (VertexLocation) obj;
		if (direction != other.direction)
			return false;
		if (hexLoc == null) {
			if (other.hexLoc != null)
				return false;
		} else if (!hexLoc.equals(other.hexLoc))
			return false;
		return true;
	}
	
	/**
	 * Returns a canonical (i.e., unique) value for this vertex location.
	 * Since each vertex has three different locations on a map,
	 * this method converts a vertex location to a single canonical form.
	 * This is useful for using vertex locations as map keys.
	 * 
	 * @return Normalized vertex location
	 */
	public VertexLocation getNormalizedLocation() {
		
		// Return location that has direction NW or NE
		
		switch (direction) {
			case NorthWest:
			case NorthEast:
				return this;
			case West:
				return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.SouthWest),
											VertexDirection.NorthEast);
			case SouthWest:
				return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.South),
						VertexDirection.NorthWest);
			case SouthEast:
				return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.South),
						VertexDirection.NorthEast);
			case East:
				return new VertexLocation(hexLoc.getNeighborLoc(EdgeDirection.SouthEast),
											VertexDirection.NorthWest);
			default:
				assert false;
				return null;
		}
	}
}

