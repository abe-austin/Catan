package server;

/**
 *
 * @author Kevin MacMaster
 */
public class AllGamesHandler implements IHandler {

    @Override
    public void handle(String command, Object Json) {
        if(!command.contains("/games/"))
            return;
        
        switch(command) {
            
        }
    }
    
}
