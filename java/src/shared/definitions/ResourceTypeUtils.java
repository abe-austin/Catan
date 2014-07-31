package shared.definitions;

/**
 *
 * @author Kevin MacMaster
 */
public class ResourceTypeUtils {
    
    public static ResourceType getResourceType(String resource) {
        for(ResourceType type : ResourceType.values()) {
            if(type.isResourceType(resource))
                return type;
        }
        
        return null;
    }

}
