package server;

import java.net.URL;
import java.net.URLClassLoader;
import server.data.IDataAccess;

/**
 *
 * @author Kevin MacMaster
 */
public class DataAccess {    
    public IDataAccess getAccess(String type) {
        try {
            URL[] url = {new URL("jar:file:/C:/proj/parser/jar/parser.jar!/test.xml")};
            if(type.equals("document")) {
                URLClassLoader child = new URLClassLoader(url, this.getClass().getClassLoader());
                Class classToLoad = Class.forName("server.data.document.DDAcess", true, child);
                return (IDataAccess)classToLoad.newInstance();
            } else {
                URLClassLoader child = new URLClassLoader(url, this.getClass().getClassLoader());
                Class classToLoad = Class.forName ("server.data.sql.SQLDataAccess.", true, child);
                return (IDataAccess)classToLoad.newInstance();
            }
        
        } catch (Exception e) { e.printStackTrace(); }
        
        return null;
    }    
}
