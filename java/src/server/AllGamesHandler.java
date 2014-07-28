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
        if(!command.contains("/user/"))
            return null;
        
        switch(command) {
            case "/games/save":
                
	
            case "/games/load":
        }
        return null;
    }
    
}
