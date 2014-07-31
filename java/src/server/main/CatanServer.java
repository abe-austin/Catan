package server.main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.logging.*;

import com.google.gson.JsonObject;
import com.sun.net.httpserver.*;

import server.*;
import shared.communication.*;


/**
 * @author brentroberts
 */
public class CatanServer {
	private static int SERVER_PORT_NUMBER = 8080;
	private static final int MAX_WAITING_CONNECTIONS = 10;
	private ServerController controller;
	private HttpServer server;

	static {
		try {
			initLog();
		}
		catch (IOException e) {
			System.out.println("Could not initialize log: " + e.getMessage());
		}
	}

	/**
	 * creates a log and sets the level
	 * 
	 * @throws IOException
	 */
	private static void initLog() throws IOException {

		Level logLevel = Level.FINE;


		Handler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(logLevel);
		consoleHandler.setFormatter(new SimpleFormatter());

		FileHandler fileHandler = new FileHandler("log.txt", false);
		fileHandler.setLevel(logLevel);
		fileHandler.setFormatter(new SimpleFormatter());
	}

	private CatanServer() {
		controller = new ServerController();
	}

	
	/**
	 * runs the server
	 * 
	 * @pre <code>SERVER_PORT_NUMBER</code> is set to a valid port
	 */
	private void run() {

		try {
			server = HttpServer.create(new InetSocketAddress(SERVER_PORT_NUMBER),
											MAX_WAITING_CONNECTIONS);
		} 
		catch (IOException e) {

			System.out.println("Could not create HTTP server: " + e.getMessage());
			//e.printStackTrace();		
			return;
		}

		server.setExecutor(null);
		server.createContext("/", handler);
		server.start();
	}
	
	/**
	 * Processes the http request and sends the request info to the handlers to be handled
	 * 
	 */
	private com.sun.net.httpserver.HttpHandler handler = new com.sun.net.httpserver.HttpHandler() {	
		@Override
		public void handle(com.sun.net.httpserver.HttpExchange exchange) throws IOException {
			byte[] response = new byte[256];
			
			String requestBody = getBody(exchange);
			
			CookieObject cookieObject = createCookieObject(exchange);
			
	        ServerResponse serverResponse = controller.handleCommand(exchange.getRequestURI().toString().substring(0),
	        		requestBody, cookieObject);
	        
	        response = JsonUtils.convertToJson(serverResponse.getBody()).getBytes("UTF-8");

	        if(serverResponse.getCookie() != null) {
		        exchange.getResponseHeaders().set("Set-cookie", serverResponse.getCookie());
	        }
	        exchange.sendResponseHeaders(serverResponse.getCode(), response.length);
	        exchange.getResponseBody().write(response);
	        exchange.close();
		}
		
		public CookieObject createCookieObject(HttpExchange exchange) {

			Map<String, List<String>> requestHeaders = exchange.getRequestHeaders();
			List<String> cookies = requestHeaders.get("Cookie");
			CookieObject cookieObject = new CookieObject();
			
			try {
				for (String cookie : cookies) {
					StringBuilder sb = new StringBuilder(cookie);
					sb.delete(0, 10);
					String edited = sb.toString();
					String decoded = URLDecoder.decode(edited);

					String[] temp = decoded.split(",");
					String username = temp[0].substring(temp[0].indexOf(":") + 2, temp[0].length() - 1);
					String password = temp[1].substring(temp[1].indexOf(":") + 2, temp[1].length() - 1);
					int id = Integer.parseInt(temp[2].substring(temp[2].indexOf(":") + 1, temp[2].length() - 1));
					
					if(temp.length == 4) {
						int gameID = Integer.parseInt(temp[3].substring(temp[3].indexOf(":") + 1,temp[3].length() - 1));
						cookieObject.setGameID(gameID);
					}

					cookieObject.setUsername(username);
					cookieObject.setPassword(password);
					cookieObject.setID(id);
				}
				
			} catch (Exception e) {
//				e.printStackTrace();
			}
			return cookieObject;
		}
	};
	
	/**
	 * gets and returns the command from the http request
	 * 
	 * @param exchange
	 * @return the command from the request
	 */
	private String getBody(HttpExchange exchange) {
        @SuppressWarnings("resource")
		java.util.Scanner s = new java.util.Scanner(exchange.getRequestBody()).useDelimiter("\\A");
        String command = s.hasNext() ? s.next() : "";
        s.close();
        return command;
	}
	
	/**
	 * creates and runs the server
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length == 0)
			SERVER_PORT_NUMBER = 8081;
		else
			SERVER_PORT_NUMBER = Integer.parseInt(args[0]);

		new CatanServer().run();
	}

}