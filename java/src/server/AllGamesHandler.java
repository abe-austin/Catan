package server;

import game.GameModel;

import java.util.ArrayList;

import client.data.GameInfo;
import player.Player;
import shared.communication.*;
import shared.definitions.CatanColor;

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
            	return getAllGames();
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
     * Gets a list of all games on server
     * 
     * @return ArrayList<GameModel> or failure
     */
    public ServerResponse getAllGames() {

        ServerResponse response = null;
        ArrayList<GameInfo> gameList = null;
        gameList = controller.getAllGames();
        
        if(gameList == null) {
        	response = new ServerResponse(400, "Failed to get games");
        } else if(gameList.isEmpty()) {
        	response = new ServerResponse(201, gameList);
        } else {
        	response = new ServerResponse(200, gameList);
        }
        
        return response;
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
        if(controller.canCreateGame(param.getName())) {
            GameModel game = controller.createGame(param);
            CreateGameRes result = new CreateGameRes(game.getGameName(), game.getGameId());
            response = new ServerResponse(200, result);
            //response.setCookie(controller.createCookie(game.getGameId()));
        } else {
            response = new ServerResponse(400, "Game Already Exists");
        }
        
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
        CookieObject cookies = controller.getCookieObject();
        System.out.println(cookies.getID());
        System.out.println(cookies.getUsername());
        System.out.println(cookies.getPassword());
        System.out.println(cookies.getGameID());

        
        
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
     * Adds Player to game or re-join
     * 
     * @param param info on game and player
     * @return success or failure
     */
    public ServerResponse joinGame(JoinGameParam param) {
        ServerResponse response = null;
        
        try{
	        GameModel game = controller.getGameModel(param.getID());
	        
	        if(game != null) {
	             for(Player player : game.getPlayers()) {
	            	 if(player != null) {
		                 if(player.getColor().toString().equals(param.getColor())) {
		                     response = new ServerResponse(200, "Success");
		                     response.setCookie(controller.createCookie(game.getGameId()));
		                     return response;
		                 }
	            	 }
	             }
	             int playerCount = controller.getPlayerCount(param.getID());
	             if(playerCount != 4) {
	                 Player player = new Player(CatanColor.valueOf(param.getColor().toUpperCase()),
	                         controller.getCookieObject().getUsername(),playerCount);
	                 
	                 game.addPlayers(player, player.getIndex());
	                 response = new ServerResponse(200, "Success");
	                 response.setCookie(controller.createCookie(param.getID()));
	                 
	             } else if(response == null) {
	                 response = new ServerResponse(400, "Game is full");
	             }
	             
	        } else {
	            response = new ServerResponse(400, "Game does not exist");
	        }
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        if(response != null){
        System.out.println(response.getCode());
        System.out.println(response.getBody());
        }
        else{
        	System.out.println("RESPONSE IS NULL");
        }

        return response;
    }
    
}
