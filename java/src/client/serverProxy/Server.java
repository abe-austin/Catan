package client.serverProxy;

public interface Server {
	public Response doPost(String url, String json);
	public Response doGet(String url);
}