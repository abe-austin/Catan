package server.data;

import client.data.GameInfo;
import game.GameModel;
import java.util.List;
import shared.communication.*;
import shared.definitions.Command;
import system.User;

/**
 *
 * @author Kevin MacMaster
 */
public interface IDataAccess {
    public void createUser(User user);
    public User getUser(LoginUserParam param);
    public void createGame(GameModel game);
    public void updateGame(GameModel game);
    public GameModel getGame(GetGameModelParam param);
    public void addCommand(Command command, int gameID);
    public List<Command> getCommands(int gameID);
    public List<GameInfo> getAllGames(User user);
    public List<GameModel> getAllGames();
    public List<User> getAllUsers();    
}
