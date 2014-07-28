package server;

/**
 *
 * @author Kevin MacMaster
 */
public class UserHandler implements IHandler{

    @Override
    public void handle(String command, Object Json) {
        if(!command.contains("/user/"))
            return;
        
        switch(command) {
            
        }
    }
    
}
