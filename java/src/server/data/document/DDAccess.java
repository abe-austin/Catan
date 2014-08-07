package server.data.document;

import client.data.GameInfo;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import game.GameModel;
import java.net.UnknownHostException;
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
    private DB db = null;
    
    protected DBCollection startTransaction(String type) {
        try {
            if(db == null) {
                MongoClient mongoClient = new MongoClient();
                db = mongoClient.getDB("catan");
            }                        
            return db.getCollection(type);            
        } catch (UnknownHostException e) {}
        return null;
     }
       
    @Override
    public User createUser(RegisterUserParam param) {
        return new DDUser(startTransaction("user")).createUser(param);
    }

    @Override
    public User getUser(LoginUserParam param) {
        return new DDUser(startTransaction("user")).getUser(param);
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
    public List<GameInfo> getAllGames(User user) {
        return null;
    }    
    
    // FOR TESTING PURPOSES
    public static void main(String[] args) {
        DDAccess dd = new DDAccess();
        RegisterUserParam param = new RegisterUserParam("kevin", "kevin");
//        LoginUserParam param = new LoginUserParam("kevin","kevin");
//        CreateGameParam param = new CreateGameParam("cool",true,true,true);
//        GetGameModelParam param = new GetGameModelParam(0);
//        param.setName("coo");
//        System.out.println(dd.getGame(param));
//        dd.createGame(param);
        System.out.println(dd.createUser(param));
    }
}
