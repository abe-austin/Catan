package server.data.document;

import client.data.GameInfo;
import game.GameModel;
import java.util.List;
import server.data.IDataAccess;
import static server.data.document.DDUtils.*;
import shared.communication.*;
import shared.definitions.Command;
import system.*;

/**
 *
 * @author Kevin MacMaster
 */
public class DDAccess implements IDataAccess {        
    
    @Override
    public User createUser(RegisterUserParam param) {
        int next = findLast(USER_PATH, param.getUsername());
        
        if(next != -1) {
            User user = new User(new Username(param.getUsername()),
                                 new Password(param.getPassword()),
                                 next + 1);
            saveFile(USER_PATH, user.getUsername().getUsername(), user);
            return user;
        } else {
            return null;
        }
    }

    @Override
    public User getUser(LoginUserParam param) {
        return (User)findMatch(USER_PATH, param.getUsername(), param.getPassword());
    }

    @Override
    public GameModel createGame(CreateGameParam param) {
        int next = findLast(GAME_PATH, param.getName());
        
        if(next != -1) {
            GameModel game = new GameModel();
            
            game.setRandomHexes(param.isRandomHexes());
            game.setRandomNumbers(param.isRandomNumbers());
            game.setRandomPorts(param.isRandomPorts());
            game.setGameName(param.getName());
            
            saveFile(GAME_PATH, game.getGameName(), game);
            
            return game;
        } else {
            return null;
        }
    }

    @Override
    public void deleteGame(String gameName) {
        // DO NOTHING
    }

    @Override
    public GameModel getGame(GetGameModelParam param) {
        return (GameModel)findMatch(GAME_PATH, param.getName());
    }

    @Override
    public void addCommand(Command command) {
        
    }

    @Override
    public List<Command> getCommands(String gameName) {
        return null;
    }

    @Override
    public List<GameModel> getAllGames() {
        return null;
    }    
    
    @Override
    public List<User> getAllUsers() {
        return null;
    }
    
    @Override
    public List<GameInfo> getAllGames(User user) {
        return null;
    }
    
    // FOR TESTING PURPOSES
    public static void main(String[] args) {
//        DDAccess dd = new DDAccess();
//        RegisterUserParam param = new RegisterUserParam("kevin", "kevin");
//        LoginUserParam param = new LoginUserParam("kevin","kevin");
//        CreateGameParam param = new CreateGameParam("cool",true,true,true);
//        GetGameModelParam param = new GetGameModelParam(0);
//        param.setName("cool");
//        System.out.println(dd.getGame(param));
//        dd.createGame(param);
    }
}
