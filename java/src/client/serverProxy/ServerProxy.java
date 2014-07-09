package client.serverProxy;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

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
	public HttpResponse doPost(String url, String json) {
		
		HttpResponse response = null;
		try {
			HttpClient httpclient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost("http://localhost:8081" + url);
	
			httppost.setEntity(new StringEntity(json, "utf-8"));
	
			//Execute and get the response.
			response = httpclient.execute(httppost);
//			HttpEntity entity = response.getEntity();
	
//			if (entity != null) {
//				System.out.println(EntityUtils.toString(entity));
//				System.out.println(response.getStatusLine().getStatusCode());
//			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return response;
	}

	/**
	 * sends an http get to the given url then returns the http response
	 * 
	 * @pre 		url is valid
	 * @param url	the <code>String</code> of the url
	 * @return		the <code>Response</code> received from the server
	 */
	@Override
	public HttpResponse doGet(String url) {
		
		HttpResponse response = null;
		try {
			HttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet("http://localhost:8081" + url);
	
			//Execute and get the response.
			response = httpclient.execute(httpget);
//			HttpEntity entity = response.getEntity();
	
//			if (entity != null) {
//				System.out.println(EntityUtils.toString(entity));
//				System.out.println(response.getStatusLine().getStatusCode());
//			}
		}
		catch(Exception e) {
			System.out.println(e);
		}
		return response;
	}

}