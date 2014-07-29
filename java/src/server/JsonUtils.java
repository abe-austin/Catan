package server;

import com.google.gson.Gson;

/**
 *
 * @author Kevin MacMaster
 */
public class JsonUtils {

    /**
     * Changes a Json object to its original form
     * 
     * @pre toConvert object can be changes to converstionType
     * @param conversionType type of object to convert to 
     * @param toConvert object to convert
     * @return correct object type (not Json)
     * @post Json object is now a Catan object
     */
    public static Object convertFromJson(Class conversionType, String toConvert) {       
        return new Gson().fromJson(toConvert, conversionType);
    }
}
