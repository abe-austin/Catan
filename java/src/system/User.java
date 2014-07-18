package system;

/**
 * @author Kevin MacMaster
 */
public class User {
	
    private Username username;
    private Password password;
    private int id;
    
    public User() {
    	
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
}
