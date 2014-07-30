package game.board;

/**
 *
 * @author Kevin MacMaster
 */
public class NumberToken {
	private int value;
	private boolean hasRobber;

	public NumberToken(int value) {
		this.value = value;
		hasRobber = false;
	}

	public int getValue() {
		return value;
	}

	/**
	 *
	 * @param roll
	 *            is the current dice roll
	 * @return true if same as value and there is not a robber on this token
	 */
	public boolean hitTest(int roll) {
		if (value == roll && !hasRobber)
			return true;
		else
			return false;
	}

	/**
	 *
	 * @return if the robber is on this token
	 */
	public boolean hasRobber() {
		return hasRobber;
	}

	/**
	 * 
	 * @param set
	 *            changes the hasRobber boolean
	 */
	public void setRobber(boolean set) {
		hasRobber = set;
	}

}
