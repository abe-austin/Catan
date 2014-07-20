package client.serverProxy;

import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import shared.communication.ServerResponse;

import com.google.gson.Gson;

/**
 * Communicates with the server through http
 * 
 * @author Brent
 *
 */
public class ServerProxy implements Server{

	Cookies cookies;
	/**
	 * empty constructor
	 */
	public ServerProxy() {
		cookies = new Cookies();
	}
	
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
	public ServerResponse doPost(String url, Object requestOb) {
		Gson gson = new Gson();
		String json = gson.toJson(requestOb);
		
		Object responseOb = null;
		ServerResponse serverResponse = null;
		try {
			HttpClient httpclient = HttpClients.createDefault();
			HttpPost httppost = new HttpPost("http://localhost:8081" + url);
	
			httppost.setEntity(new StringEntity(json, "utf-8"));
			this.sendCookies(httppost);
			
			//Execute and get the response.
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity entity = response.getEntity();
			
			//set cookie
			this.setCookies(response.getAllHeaders());

			responseOb = EntityUtils.toString(entity);
			serverResponse = new ServerResponse(response.getStatusLine().getStatusCode(),
					responseOb);
		}
		catch(Exception e) {
			System.out.println(e);
			serverResponse = new ServerResponse(521, "Server connection failed");
		}
		return serverResponse;
	}

	/**
	 * sends an http get to the given url then returns the http response
	 * 
	 * @pre 		url is valid
	 * @param url	the <code>String</code> of the url
	 * @return		the <code>Response</code> received from the server
	 */
	@Override
	public ServerResponse doGet(String url) {
		
		Object responseOb = null;
		ServerResponse serverResponse = null;

		try {
			HttpClient httpclient = HttpClients.createDefault();
			HttpGet httpget = new HttpGet("http://localhost:8081" + url);
			this.sendCookies(httpget);
	
			//Execute and get the response.
			HttpResponse response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			
			this.setCookies(response.getAllHeaders());
			
			responseOb = (Object)EntityUtils.toString(entity);
			
			serverResponse = new ServerResponse(response.getStatusLine().getStatusCode(),
					responseOb);
	
		}
		catch(Exception e) {
			System.out.println(e);
			serverResponse = new ServerResponse(521, "Server connection failed");
		}
		return serverResponse;
	}
	
	private void setCookies(Header[] headers) {
		for(Header header : headers) {
			if(header.getName().equals("Set-cookie")) {
				String cookie = header.getValue().toString();
				String key = cookie.substring(0, cookie.indexOf("="));
				cookie = cookie.substring(cookie.indexOf("=")+1, cookie.length());
				if(key.equals("catan.user")) {
					StringBuffer sb = new StringBuffer();
					cookie = cookie.substring(cookie.indexOf("name"), cookie.length());
					cookie = sb.append("%7B%22").append(cookie).toString();
				}
				cookie = cookie.substring(0, cookie.indexOf(";"));
				cookies.addCookie(key, cookie);
			}
		}
	}
	
	private void sendCookies(HttpPost httppost) {
		Map<String, String> cookieMap = cookies.getCookies();
		String allCookies = "";
		for(String key : cookieMap.keySet()) {
			String cookie = cookieMap.get(key);
			allCookies += key + "=" + cookie + "; ";
		}
		if(!allCookies.equals("")) {
			allCookies = allCookies.substring(0, allCookies.length()-2);
		}
		httppost.setHeader("Cookie", allCookies);
	}
	
	private void sendCookies(HttpGet httppost) {
		Map<String, String> cookieMap = cookies.getCookies();
		String allCookies = "";
		for(String key : cookieMap.keySet()) {
			String cookie = cookieMap.get(key);
			allCookies += key + "=" + cookie + "; ";
		}
		if(!allCookies.equals("")) {
			allCookies = allCookies.substring(0, allCookies.length()-2);
		}		
		httppost.setHeader("Cookie", allCookies);
	}
	
	public Cookies getCookies() {
		return cookies;
	}
}