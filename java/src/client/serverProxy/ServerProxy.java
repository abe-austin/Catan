package client.serverProxy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

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
	public String doPost(String urlString, String json) {

        try{
   		 	// verify our client code
        	URL url = new URL(urlString);
    	    URLConnection conn = url.openConnection();
            conn.setDoOutput(true);
            
	        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	        wr.write(json);
	        wr.flush();

	        //get response
		    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		    StringBuilder sb = new StringBuilder();
		    String resultAsString = null;
		    while((resultAsString = in.readLine()) != null){
		    	sb.append(resultAsString);
		    }
		    return sb.toString();
        }
        catch(IOException e){
        	return null;
        }		
	}

	/**
	 * sends an http get to the given url then returns the http response
	 * 
	 * @pre 		url is valid
	 * @param url	the <code>String</code> of the url
	 * @return		the <code>Response</code> received from the server
	 */
	@Override
	public String doGet(String url) {
		// TODO Auto-generated method stub
		return null;
	}

}