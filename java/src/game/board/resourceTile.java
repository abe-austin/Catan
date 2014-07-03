package game.board;

import shared.definitions.ResourceType;

public class ResourceTile extends HexTile {
	private ResourceType type;
        private NumberToken token;

        public ResourceTile(ResourceType type, NumberToken token) {
            this.type = type;
            this.token = token;
	}

        /**
         * @return the type
         */
        public ResourceType getResourceType() {
            return type;
        }

        /**
         * 
         * @param roll current dice roll
         * @return if the robber is not present and the roll
         *          matches token value
         */
        public boolean hitTest(int roll) {
            return token.hitTest(roll);
        }

        /**
         * 
         * @return true if robber is present, false otherwise
         */
        public boolean hasRobber() {
            return token.hasRobber();
        }

        /**
         *
         * @param set changes robber presence boolean
         */
        public void setRobber(boolean set) {
            token.setRobber(set);
        }
}