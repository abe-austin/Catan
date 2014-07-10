package client.serverProxy;

/**
 * Connects to the server through HTTP and rends requests/receive response
 * 
 * @author Brent
 * 
 */
public interface Server {	
	public Object doPost(String url, Object payLoad);
	public Object doGet(String url);
}