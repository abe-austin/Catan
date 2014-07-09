package client.serverProxy;

import org.apache.http.HttpResponse;

/**
 * Connects to the server through HTTP and rends requests/receive response
 * 
 * @author Brent
 * 
 */
public interface Server {	
	public HttpResponse doPost(String url, String json);
	public HttpResponse doGet(String url);
}