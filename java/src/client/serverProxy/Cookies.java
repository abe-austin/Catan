package client.serverProxy;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import com.google.gson.Gson;

public class Cookies {
	Map<String,String> cookies;

	public Cookies() {
		super();
		cookies = new HashMap<String, String>();
	}
	
	public void resetCookies() {
		cookies = new HashMap<String, String>();
	}

	public void addCookie(String key, String value) {
		cookies.put(key, value);
	}
	
	public Map<String, String> getCookies() {
		return cookies;
	}

	public void setCookies(Map<String, String> cookies) {
		this.cookies = cookies;
	}
	
	public int getUserId() {
		for(String key : cookies.keySet()) {
			String cookie = cookies.get(key);
			if(key.equals("catan.user")) {
				String test = URLDecoder.decode(cookie);
				test = test.substring(test.indexOf("\"playerID\":")+11, test.length()-1);
				int id = Integer.parseInt(test);
				return id;
			}
		}
		return -1;
	}
}
