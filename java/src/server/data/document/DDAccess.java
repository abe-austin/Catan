package server.data.document;

import client.data.GameInfo;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import game.GameModel;
import java.io.File;
import java.util.ArrayList;
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
    public User createUser(User user) {
        Object next = findMatch(USER_PATH, user.getUsername().getUsername());
        
        if(next != null) {            
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
    public GameModel createGame(GameModel game) {
        Object next = findMatch(GAME_PATH, game.getGameName());
        
        if(next != null) {                        
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
        ArrayList<GameModel> games = new ArrayList<>();
        
        for(File file : new File(GAME_PATH).listFiles()) {
            games.add((GameModel)new XStream(new DomDriver()).fromXML(file));
        }
        
        return games;
    }    
    
    @Override
    public List<User> getAllUsers() {
        ArrayList<User> users = new ArrayList<>();
        
        for(File file : new File(USER_PATH).listFiles()) {
            users.add((User)new XStream(new DomDriver()).fromXML(file));
        }
        
        return users;
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
