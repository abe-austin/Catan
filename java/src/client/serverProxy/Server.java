package client.serverProxy;

import javax.xml.ws.Response;

/**
 * Connects to the server through HTTP and rends requests/receive response
 * 
 * @author brentroberts
 * 
 */
public interface Server {	
	public Response doPost(String url, String json);
	public Response doGet(String url);
}