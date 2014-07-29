package server;

import shared.communication.ServerResponse;

/**
 *
 * @author Kevin MacMaster
 */
public class AllGamesHandler implements IHandler {
    private ServerController controller;
    
    public AllGamesHandler(ServerController controller) {
        this.controller = controller;
    }
    
    @Override
    public ServerResponse handle(String command, Object Json) {
        
        switch(command) {
        	case "/games/list":
        		
        	case "/games/create":
        		
        	case "/games/join":
        		
            case "/games/save":
            	
            case "/games/load":
        }
        return null;
    }
    
}
