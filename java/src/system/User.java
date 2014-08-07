package system;

import com.mongodb.DBObject;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.bson.BSONObject;

/**
 * @author Kevin MacMaster
 */
public class User implements DBObject {
	
    private Username username;
    private Password password;
    private int id;
    
    public User() {
    	
    }
    
    public User(Username username, Password password, int id) {
    	this.username = username;
    	this.password = password;
    	this.id = id;
    }

    /**
     * @return the username
     */
    public Username getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(Username username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public Password getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(Password password) {
        this.password = password;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }  

    @Override
    public void markAsPartialObject() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isPartialObject() {
        return false;
    }

    @Override
    public Object put(String string, Object o) {
        switch(string) {
            case "username":
                username.setUsername((String)o);
                break;
            case "password":
                password.setPassword((String)o);
        }
        return this;
    }

    @Override
    public void putAll(BSONObject bsono) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void putAll(Map map) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get(String string) {
        switch(string) {
            case "username":
                return username.getUsername();
            case "password":
                return password.getPassword();
        }
        return null;
    }

    @Override
    public Map toMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object removeField(String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean containsKey(String string) {
        return(username.getUsername().equals(string) || password.getPassword().equals(string));
    }

    @Override
    public boolean containsField(String string) {
        return (string.equals("username") || string.equals("password"));
    }

    @Override
    public Set<String> keySet() {
        Set<String> set = new HashSet<>();
        set.add("username");
        set.add("password");
        return set;
    }
}
