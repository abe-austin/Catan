package client.serverProxy;

import shared.communication.ServerResponse;

/**
 * Connects to the server through HTTP and rends requests/receive response
 * 
 * @author Brent
 * 
 */
public interface Server {	
	public ServerResponse doPost(String url, Object payLoad);
	public ServerResponse doGet(String url);
	public Cookies getCookies();
}