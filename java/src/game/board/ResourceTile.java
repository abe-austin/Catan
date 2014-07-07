package game.board;

import shared.definitions.HexType;
import shared.definitions.ResourceType;

public class ResourceTile extends HexTile {
	private ResourceType type;
        private NumberToken token;

        public ResourceTile(ResourceType type, NumberToken token) {
            this.type = type;
            this.token = token;
	}

        @Override
        public HexType getType() {
            if(type.equals(ResourceType.BRICK))
                return HexType.BRICK;
            else if(type.equals(ResourceType.ORE))
                return HexType.ORE;
            else if(type.equals(ResourceType.SHEEP))
                return HexType.SHEEP;
            else if(type.equals(ResourceType.WHEAT))
                return HexType.WHEAT;
            else if(type.equals(ResourceType.WOOD))
                return HexType.WOOD;
            else
                return null;
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