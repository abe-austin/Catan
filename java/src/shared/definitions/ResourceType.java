package shared.definitions;

public enum ResourceType {

	WOOD("wood"), BRICK("brick"), SHEEP("sheep"), WHEAT("wheat"), ORE("ore");
        
        private final String name;
        
        ResourceType(String name) {
            this.name = name;
        }
        
        public boolean isResourceType(String type) {
            return name.equals(type);
        }        
}

