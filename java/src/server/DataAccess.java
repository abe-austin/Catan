package server;

import java.io.File;
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
            if(type.equals("document")) {
                File file = new File(".\\plugins\\document.jar");
                URL[] url = {new URL("jar", "","file:" + file.getAbsolutePath()+"!/")};   
                URLClassLoader child = new URLClassLoader(url, this.getClass().getClassLoader());
                Class classToLoad = Class.forName("server.data.document.DDAcess", true, child);
                return (IDataAccess)classToLoad.newInstance();
            } else {
                File file = new File(".\\plugins\\sql.jar");
                URL[] url = {new URL("jar", "","file:" + file.getAbsolutePath()+"!/")};   
                URLClassLoader child = new URLClassLoader(url, this.getClass().getClassLoader());
                Class classToLoad = Class.forName ("server.data.sql.SQLDataAccess.", true, child);
                return (IDataAccess)classToLoad.newInstance();
            }
        
        } catch (Exception e) { e.printStackTrace(); }
        
        return null;
    }    
}
