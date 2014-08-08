package server;

import game.GameModel;
import java.util.ArrayList;
import java.util.List;
import server.data.IDataAccess;
import shared.definitions.Command;
import system.User;

/**
 *
 * @author Kevin MacMaster
 */
public class ServerModel {
        private ArrayList<GameModel> games;
        private ArrayList<User> users;
        private IDataAccess dac = null;
        
        public ServerModel() {
        	games = new ArrayList<>();
        	users = new ArrayList<>();
        }
        
        public void initialize(String database) {
            dac = new DataAccess().getAccess(database);
            games = (ArrayList)dac.getAllGames();
            for (GameModel game: games){
                List<Command> commands = dac.getCommands(game.getGameId());
                for(Command command : commands){
                    ServerController.getSingleton().applyCommand(command);
                }
            }
            users = (ArrayList)dac.getAllUsers();
        }

        /**
         *
         * @param model to add
         */
        public void addGame(GameModel model) {
            games.add(model);
//            dac.createGame(model);
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
//            dac.createUser(null);
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
