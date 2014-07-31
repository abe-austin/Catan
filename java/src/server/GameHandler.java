package server;

import game.GameModel;
import shared.communication.*;

/**
 *
 * @author Kevin MacMaster
 */
public class GameHandler implements IHandler {

    @Override
    public ServerResponse handle(String command, Object Json) {
        switch(command) {
            case "/game/model":
        	return getGameModel((GetGameModelParam)JsonUtils.convertFromJson(
                        GetGameModelParam.class, (String)Json));	
            case "/game/reset":	
            	return createReset((CreateGameRes)JsonUtils.convertFromJson(
                        CreateGameRes.class, (String)Json));
            case "/game/commands":
            	return getGameCommands((DoGameCommandsParam)JsonUtils.convertFromJson(
                        DoGameCommandsParam.class, (String)Json));
            case "/game/addAI":    
                
            default:
                return null;
        }
    }
    
    /**
     * Games the GameModel for a given game
     * 
     * @param param info on game
     * @return GameModel or failure
     */
	public ServerResponse getGameModel(GetGameModelParam param) {
       
		ServerResponse response = null;
//		GameModel gameModel = getGameModel(); //get game id from cookie
//		if(gameModel != null) {
//			response = new ServerResponse(200, gameModel);
//    	  
//		} else {
//			response = new ServerResponse(400, null);
//		}      
			return response;
    }
    
    /**
     * Creates a reset point in the game
     * 
     * @param param info on game
     * @return success or failures
     */
    public ServerResponse createReset(CreateGameRes param) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Gets Commands for a given game
     * 
     * @param param info on commands
     * @return Command list or failure
     */
    public ServerResponse getGameCommands(DoGameCommandsParam param) {
        ServerResponse response = null;
        
        
        
        return response;
    }
}
