package client.serverProxy;

import javax.xml.ws.Response;

public interface Server {
	public Response doPost(String url, String json);
	public Response doGet(String url);
}