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
            URL[] url;
            if(type.equals("document")) {
                File file = new File(".\\java\\dist\\document.jar");
                url = new URL[] {new URL("jar", "","file:" + file.getAbsolutePath()+"!/")};                   
            } else {
                File file = new File(".\\java\\dist\\sql.jar");
                url = new URL[] {new URL("jar", "","file:" + file.getAbsolutePath()+"!/")};                   
            }
            
            URLClassLoader child = new URLClassLoader(url, this.getClass().getClassLoader());
            Class classToLoad = Class.forName("server.data.document.DDAccess", true, child);
            return (IDataAccess)classToLoad.newInstance();
        
        } catch (Exception e) { e.printStackTrace(); }
        
        return null;
    }    
}
