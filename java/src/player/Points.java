package player;

/**
 *
 * @author Kevin MacMaster
 */
public class Points {
	private int points;

	public Points() {
		points = 0;
	}

	/**
	 * adds a single point
	 * @post player now has one more point
	 */
	public void addPoint() {
		points++;
	}

	/**
	 * subtracts a single point
	 * @post player now has one less point
	 */
	public void subtractPoint() {
		points--;
	}

	/**
	 *
	 * @return current points
	 */
	public int getPoints() {
		return points;
	}

	/**
	 *
	 * @return if the player has enough points for a victory
	 */
	public boolean isVictory() {
		return points >= 10;
	}
}
