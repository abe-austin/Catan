package server;

import shared.communication.*;

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
                return createGame((CreateGameParam)JsonUtils.convertFromJson(
                        CreateGameParam.class, (String)Json));
            case "/games/join":
        	return joinGame((JoinGameParam)JsonUtils.convertFromJson(
                        JoinGameParam.class, (String)Json));	
            case "/games/save":
            	return saveGame((SaveGameParam)JsonUtils.convertFromJson(
                        SaveGameParam.class, (String)Json));
            case "/games/load":
                return loadGame((LoadGameParam)JsonUtils.convertFromJson(
                        LoadGameParam.class, (String)Json));
            default:
                return null;
        }
    }
    
    /**
     * Creates new game with given name, if possible
     * 
     * @param param info on game
     * @return GameModel or failure
     * @post new GameModel added and returned
     */
    public ServerResponse createGame(CreateGameParam param) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Games the GameModel for a given game
     * 
     * @param param info on game
     * @return GameModel or failure
     */
    public ServerResponse getGameModel(GetGameModelParam param) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Saves the GameModel of a given game
     * 
     * @param param info on game
     * @return success or failure
     */
    public ServerResponse saveGame(SaveGameParam param) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Loads game of given name
     * 
     * @param param info on game
     * @return GameModel or failure
     */
    public ServerResponse loadGame(LoadGameParam param) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
    /**
     * Adds Player to game
     * 
     * @param param info on game and player
     * @return success or failure
     */
    public ServerResponse joinGame(JoinGameParam param) {
        ServerResponse response = null;
        
        
        
        return response;
    }
    
}
