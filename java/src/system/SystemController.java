package system;

import game.GameModel;
import java.util.ArrayList;

/**
 *
 * @author Kevin MacMaster
 */
public class SystemController {
    private SystemModel systemModel;
    private int lastUser;

    public SystemController() {
        systemModel = new SystemModel();
        lastUser = 0;
    }

    /**
     *
     * @param Title to add to GameModel
     * @return new GameModel
     */
    public GameModel createGame(String Title) {
        GameModel game = new GameModel();

        systemModel.addGame(game);

        return game;
    }

    /**
     * 
     * @return all games
     */
    public ArrayList<GameModel> getGames() {
        return systemModel.getGames();
    }

    /**
     * 
     * @param game to remove from system
     */
    public void finishGame(GameModel game) {
        systemModel.removeGame(game);
    }

    /**
     * 
     * @param username
     * @param password
     * @return creates a new user and returns
     */
    public User createUser(String username, String password) {
        User user = new User();
        user.setUsername(new Username(username));
        user.setPassword(new Password(password));
        user.setId(++lastUser);

        systemModel.addUser(user);
        return user;
    }

    /**
     *
     * @param username
     * @param password
     * @return User if user exists with given username and password
     */
    public User getUser(String username, String password) {
        for(User user : systemModel.getUsers()) {
            if(user.getUsername().getUsername().equals(username) &&
               user.getPassword().getPassword().equals(password) ) {
                return user;
            }
        }
        
        return null;
    }
}
