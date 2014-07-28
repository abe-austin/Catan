package server;

/**
 *
 * @author Kevin
 */
public class MovesHandler implements IHandler {

    @Override
    public void handle(String command, Object Json) {
        if(!command.contains("/moves/"))
            return;
        
        switch(command) {
            
        }
    }
    
}
