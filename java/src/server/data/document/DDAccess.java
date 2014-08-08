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
    public void createUser(User user) {
        saveFile(USER_PATH, user.getUsername().getUsername(), user);
    }

    @Override
    public User getUser(LoginUserParam param) {
        return (User)findMatch(USER_PATH, param.getUsername(), param.getPassword());
    }

    @Override
    public void createGame(GameModel game) {        
        saveFile(GAME_PATH, "game" + game.getGameId(), game);             
    }

    @Override
    public GameModel getGame(GetGameModelParam param) {
        return null; //(GameModel)findMatch(GAME_PATH, param.getName());
    }

    @Override
    public void addCommand(Command command, int id) {
        saveFile(CMD_PATH, "game" + String.valueOf(id) + "_command" + String.valueOf(
                command.getCommandId()), command);
    }

    @Override
    public List<Command> getCommands(int id) {
        ArrayList<Command> commands = new ArrayList<>();
        
        for(File file : new File(CMD_PATH).listFiles()) {
            if(file.getName().matches("game" + String.valueOf(id) + ".*"))
                commands.add((Command)new XStream(new DomDriver()).fromXML(file));
        }
        
        return commands;
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
    
    @Override
    public void updateGame(GameModel game) {
        createGame(game);
        
        for(File file : new File(CMD_PATH).listFiles()) {
            if(file.getName().matches("game" + String.valueOf(game.getGameId()) + ".*"))
                file.delete();
        }
    }
    
    // FOR TESTING PURPOSES
    public static void main(String[] args) {
        DDAccess dd = new DDAccess();
        User user = new User(new Username("kevin"), new Password("kevin"), 1);
        dd.createUser(user);
        LoginUserParam param = new LoginUserParam("kevin", "kevin");
        System.out.println(dd.getUser(param));
    }
}
