package server;

import game.GameModel;
import java.util.ArrayList;
import system.User;

/**
 *
 * @author Kevin MacMaster
 */
public class ServerModel {
        private ArrayList<GameModel> games;
        private ArrayList<User> users;
        
        public ServerModel() {
        	games = new ArrayList<GameModel>();
        	users = new ArrayList<User>();
        }

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
