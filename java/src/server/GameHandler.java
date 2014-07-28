package server;

/**
 *
 * @author Kevin
 */
public class GameHandler implements IHandler {

    @Override
    public void handle(String command, Object Json) {
        if(!command.contains("/game/"))
            return;
        
        switch(command) {
            
        }
    }
    
}
