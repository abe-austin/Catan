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

        public void addPoint() {
            points++;
        }

        public void subtractPoint() {
            points--;
        }

        public int getPoints() {
            return points;
        }

        public boolean checkWin() {
            return points >= 10;
        }
}
