package server;

import shared.communication.ServerResponse;

/**
 *
 * @author Kevin MacMaster
 */
public class GameHandler implements IHandler {

    @Override
    public ServerResponse handle(String command, Object Json) {
        if(!command.contains("/user/"))
            return null;
        
        switch(command) {
        	case "/game/model":
        		
            case "/game/reset":	
            	
            case "/game/commands":
            	
            case "/game/addAI":         
        }
        return null;
    }
    
}
