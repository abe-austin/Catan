package server;

import java.util.ServiceLoader;
import server.data.IDataAccess;

/**
 *
 * @author Kevin MacMaster
 */
public class DataAcess {
    public static IDataAccess getAccess(String type) {
        ServiceLoader<IDataAccess> loaders = ServiceLoader.load(IDataAccess.class);
        for (IDataAccess access: loaders) {
//            if()
            return access;
        }
        throw new Error("Class Load Failure");
    }
    
}
