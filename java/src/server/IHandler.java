package server;

import shared.communication.ServerResponse;

/**
 *
 * @author Kevin
 */
public interface IHandler {
    public ServerResponse handle(String command, Object Json);
}
