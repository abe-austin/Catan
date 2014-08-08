package server;

import shared.communication.ServerResponse;

/**
 *
 * @author Kevin MacMaster
 */
public interface IHandler {
    
    /**
     * Takes a Json Object and handles command if possible
     * 
     * @param command to perform
     * @param Json object to use in handling command
     * @return Response object
     * @post change to the server/game models if possible
     */
    public ServerResponse handle(String command, Object Json);
    public void applyCommand(String command, Object Json);
}
