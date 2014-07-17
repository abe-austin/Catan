package client.serverProxy;

import java.util.HashMap;
import java.util.Map;

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
	
}
