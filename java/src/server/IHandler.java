package server;

import shared.communication.ServerResponse;

/**
 *
 * @author Kevin
 */
public interface IHandler {
    public Object handle(String command, Object Json);
}
