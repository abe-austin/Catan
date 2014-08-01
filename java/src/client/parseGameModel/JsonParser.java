package client.parseGameModel;

import org.json.JSONObject;


public class JsonParser {
	
	JSONObject jsonObject;
	public JsonParser(String jsonString) {
		jsonObject = new JSONObject(jsonString);
		
	}
}
