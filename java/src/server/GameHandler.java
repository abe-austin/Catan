package server;

/**
 *
 * @author Kevin MacMaster
 */
public class GameHandler implements IHandler {

    @Override
    public Object handle(String command, Object Json) {
        if(!command.contains("/user/"))
            return null;
        
        switch(command) {
            case "/game/reset":
		
				
            case "/game/commands":
                   

            case "game/addAI":
                
            
            case "/game/create":
        }
        return null;
    }
    
}
