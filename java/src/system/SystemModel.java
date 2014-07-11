package system;

import game.GameModel;
import java.util.ArrayList;

/**
 *
 * @author Kevin MacMaster
 */
public class SystemModel {
    private ArrayList<GameModel> games;
    private ArrayList<User> users;

    /**
     *
     * @param model to add
     */
    public void addGame(GameModel model) {
        games.add(model);
    }

    /**
     *
     * @param model to remove
     */
    public void removeGame(GameModel model) {
        games.remove(model);
    }

    /**
     *
     * @param user to add
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * 
     * @param user to check existing games
     * @return list of games
     */
    public ArrayList<GameModel> getGamesForUser(User user) {
        ArrayList<GameModel> gameList = new ArrayList<GameModel>();

        for(GameModel model : games)
            if(model.hasUser(user))
                gameList.add(model);

        return gameList;
    }

    /**
     * @return the games
     */
    public ArrayList<GameModel> getGames() {
        return games;
    }

    /**
     * @return the users
     */
    public ArrayList<User> getUsers() {
        return users;
    }
}
