package server.data.document;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import shared.communication.LoginUserParam;
import shared.communication.RegisterUserParam;
import system.Password;
import system.User;
import system.Username;

/**
 *
 * @author Kevin MacMaster
 */
public class DDUser {
    private DBCollection collection;
    
    public DDUser(DBCollection collection) {
        this.collection = collection;
    }
    
    public User createUser(RegisterUserParam param) {
        if(getUser(new LoginUserParam(param.getUsername(),param.getPassword())) == null) {
            User user = new User();
            
            user.setUsername(new Username(param.getUsername()));
            user.setPassword(new Password(param.getPassword()));
            user.setId((int)collection.count());
            
            collection.insert(user);
            
            return user;
        } else {
            return null;   
        }
    }

    public User getUser(LoginUserParam param) {
        User search = new User();
        search.setUsername(new Username(param.getUsername()));
        search.setPassword(new Password(param.getPassword()));
        
        DBObject obj = (collection.find(search).hasNext()) ? collection.find(search).next() : null;
        
        if(obj != null)
            return (User)obj;
        else
            return null;
    }
}
