package server.data.document;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import system.User;

/**
 *
 * @author Kevin MacMaster
 */
public class DDUtils {    
    protected final static String USER_PATH = "database\\document\\user\\";
    protected final static String CMD_PATH = "database\\document\\command\\";
    protected final static String GAME_PATH = "database\\document\\game\\";    

    /**
     * Returns object from file
     * 
     * @param source where to find
     * @param name of file
     * @return Catan Object
     */
    protected static Object findMatch(String source, String name) {
        for(File file : new File(source).listFiles()) {
            if(file.getName().equals(name + ".catan"))
                return new XStream(new DomDriver()).fromXML(file);
        }
        
        return null;
    }
    
    /*
     * Returns matching user only if password matches
     */
    protected static Object findMatch(String source, String name, String password) {        
        Object obj = findMatch(source, name);
        
        if(obj != null) {
            if(((User)obj).getPassword().getPassword().equals(password))
                return obj;
        }
        
        return null;
    }
    
    /**
     * Finds the last id equivalent
     * 
     * @param source where to find
     * @param name of file
     * @return last id
     */
    protected static int findLast(String source, String name) {
        if(findMatch(source, name) == null) {
            return new File(source).listFiles().length;
        } else {
            return -1;
        }
    }
    
    /**
     * Saves Object as a Catan file
     * 
     * @param source place to save
     * @param name to save as
     * @param obj to save
     */
    protected static void saveFile(String source, String name, Object obj) {
        try {
            File file = new File(source + name + ".catan");
            
            if(file.exists())
                file.delete();            
            
            file.createNewFile();
            
            FileOutputStream out = new FileOutputStream(file);
            XStream xmlStream = new XStream(new DomDriver());
            
            out.write(xmlStream.toXML(obj).getBytes());
            out.close();
        } catch(IOException e) { e.printStackTrace(); }
    }    
}
