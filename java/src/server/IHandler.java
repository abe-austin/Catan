package server;

/**
 *
 * @author Kevin
 */
public interface IHandler {
    public void handle(String command, Object Json);
}
