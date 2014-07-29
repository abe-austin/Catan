package server;

import shared.communication.ServerResponse;


/**
 *
 * @author Kevin MacMaster
 */
public class UserHandler implements IHandler{
    private ServerController controller;
    
    public UserHandler(ServerController controller) {
        this.controller = controller;
    }

    @Override
    public ServerResponse handle(String command, Object Json) {
        if(!command.contains("/user/"))
            return null;
        
        switch(command) {
            case "/user/login":	
            	
            case "/user/register":
        }
        
        return null;
    }
    
}
