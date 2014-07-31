package server;

import game.GameModel;

import java.util.ArrayList;
import shared.communication.CookieObject;
import shared.communication.CreateGameParam;

import shared.communication.ServerResponse;
import system.Password;
import system.User;
import system.Username;

/**
 *
 * @author Kevin MacMaster
 */
public class ServerController {
    
    private ServerModel model;
    private ArrayList<IHandler> handlers;
    private int lastUserId;
    private int lastGameId;
    private CookieObject currentCookie;
    
    public ServerController() {
        model = new ServerModel();
        lastUserId = 0;
        lastGameId = 0;
        currentCookie = null;
        
        handlers = new ArrayList<>();
        
        handlers.add(new UserHandler(this));
        handlers.add(new MovesHandler(this));
        handlers.add(new AllGamesHandler(this));
        handlers.add(new GameHandler(this));
    }
    
    /**
     * Checks to see if you can create a user with given 
     *   username and password
     * 
     * @param username to create
     * @param password 
     * @return true if username doesn't exist and password and username
     *          fit proper lengths
     */
    public boolean canCreateUser(String username, String password) {
        for(User user : model.getUsers()) {
            if(user.getUsername().getUsername().equals(username))
                return false;
        }
        
        return true;
    }
    
    /**
     * Creates new User and adds to ServerModel
     * 
     * @pre username is unique and strings meet basic requirements
     * @param username for user
     * @param password for user
     * @return new user
     * @post new user in ServerModel
     * 
     */
    public User createUser(String username, String password) {
        User user = new User(new Username(username), new Password(password), lastUserId++);
        model.addUser(user);
        return user;
    }
    
    /**
     * If parameters match, logins in and returns User
     * 
     * @param username of User
     * @param password of User
     * @return the User with given username and password
     */
    public User loginUser(String username, String password) {
        for(User user : model.getUsers()) {
            if(user.getUsername().getUsername().equals(username) &&
                    user.getPassword().getPassword().equals(password))
                return user;
        }
        
        return null;
    }
    
    /**
     * Gets all of the games on the server
     * 
     * @return list of games
     */
    public ArrayList<GameModel> getAllGames() {
        return model.getGames();
    }
    
    /**
     * 
     * @pre user is a valid user already in the system
     * @param user to check existing games
     * @return list of games
     */
    public ArrayList<GameModel> getGamesForUser(User user) {
        ArrayList<GameModel> gameList = new ArrayList<>();

        for(GameModel game : model.getGames())
            if(game.hasUser(user))
                gameList.add(game);

        return gameList;
    }
    
        /**
         * Returns a game by the name of the game
         * 
         * @pre name is the name of an existing not-complete game
         * @param name of game
         * @return matching GameModel
         */
        public GameModel getGameModel(String name) {
            for(GameModel game : model.getGames()) {
                if(game.getGameName().equals(name))
                    return game;
            }
            
            return null;
        }
        
        /**
         * Returns a game by the id of the game
         * 
         * @pre id is for an existing not-complete game
         * @param id game id
         * @return matching GameModel
         */        
        public GameModel getGameModel(int id) {
            for(GameModel game : model.getGames()) {
                if(game.getVersion() == id)
                    return game;
            }
            
            return null;
        }
        
        public GameModel getGameModel() {
            return getGameModel(currentCookie.getGameID());
        }
        
        /**
         * Checks to see if game already exists
         * 
         * @param gameName name of game
         * @return true if no game of gameName exists and 
         *          gameName string meets basic requirements
         */
        public boolean canCreateGame (String gameName) {
            if(gameName.length() > 4 || gameName.length() > 20)
                return false;
            
            return (getGameModel(gameName) != null);
        }
        
        /**
         * Creates new Game and adds it to model
         * 
         * @pre Game does not have same name as another game
         * @param param info on game
         * @return new Game
         * @post new game is added to ServerModel
         */
        public GameModel createGame(CreateGameParam param) {
            GameModel game = new GameModel();
            
            game.setRandomHexes(param.isRandomHexes());
            game.setRandomNumbers(param.isRandomNumbers());
            game.setRandomPorts(param.isRandomPorts());
            game.setGameName(param.getName());
            game.setGameId(lastGameId++);
            
            model.addGame(game);
            currentCookie.setGameID(game.getGameId());
            
            return game;
        }
    
        /**
         * Handles a given command
         * 
         * @pre command is one of the pre-determined commands 
         *       and object is a catan object related to command
         * @param command header command
         * @param Json object to be passed to game
         * @param cookie player and game info
         * @return Response object
         * @post object updates games/users
         */
        public ServerResponse handleCommand(String command, Object Json, CookieObject cookie) {
            currentCookie = cookie;
            for(IHandler handler : handlers) {
            	ServerResponse response = handler.handle(command, Json);
                if(response != null)
                	return response;
            }
            return new ServerResponse(400, "Command not supported");
        }
        
        public CookieObject getCookieObject() {
            return currentCookie;
        }        
        
        /**
         * Creates Cookie String
         * 
         * @param user username
         * @param pass password
         * @param id user id
         * @param gameId game id
         * @return String to represent cookie
         */
        public String createCookie(String user, String pass, int id, int gameId) {
            String cookie = "catan.user=%7B%22authentication%22%3A%22-1286879297%22%2C%22name%22%3A%22" +
                            user + "%22%2C%22password%22%3A%22" +
                            pass + "%22%2C%22playerID%22%3A" + id;
            
            if(gameId != -1)
                cookie += "%22%2C%22gameID%22%3A" + gameId;
                        
            cookie += "%7D;Path=/;";
            
            return cookie;
        }
        
        public String createCookie(String user, String pass, int id) {
            return createCookie(user, pass, id, -1);
        }
        
        public String createCookie(int gameId) {
            return createCookie(currentCookie.getUsername(),currentCookie.getPassword(),
                                    currentCookie.getID(), gameId);
        }
}
