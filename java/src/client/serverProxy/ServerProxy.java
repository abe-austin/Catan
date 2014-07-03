package client.serverProxy;

import javax.xml.ws.Response;

/**
 * Communicates with the server through http
 * 
 * @author Brent
 *
 */
public class ServerProxy implements Server{

	/**
	 * empty constructor
	 */
	public ServerProxy(){}
	
	/**
	 * sends an http post to the given url and passes the given JSON as the body
	 * of the request then returns the http response
	 * 
	 * @pre 		url is valid
	 * @pre 		json is valid
	 * @post 		request was executed on server
	 * @param url	the <code>String</code> of the url
	 * @param json 	the <code>String</code> holding the json to be passed to server
	 * @return		the <code>Response</code> received from the server
	 */
	@Override
	public Response doPost(String url, String json) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * sends an http get to the given url then returns the http response
	 * 
	 * @pre 		url is valid
	 * @param url	the <code>String</code> of the url
	 * @return		the <code>Response</code> received from the server
	 */
	@Override
	public Response doGet(String url) {
		// TODO Auto-generated method stub
		return null;
	}

}