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
    public User createUser(RegisterUserParam param);
    public User getUser(LoginUserParam param);
    public GameModel createGame(CreateGameParam param);
    public void deleteGame(String gameName);
    public GameModel getGame(GetGameModelParam param);
    public void addCommand(Command command);
    public List<Command> getCommands(String gameName);
    public List<GameInfo> getAllGames(User user);
}
