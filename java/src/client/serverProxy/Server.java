package client.serverProxy;

/**
 * Connects to the server through HTTP and rends requests/receive response
 * 
 * @author Brent
 * 
 */
public interface Server {	
	public String doPost(String url, String json);
	public String doGet(String url);
}