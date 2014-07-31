package server.main;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.logging.*;

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
			
			Map<String, List<String>> requestHeaders = exchange.getRequestHeaders();
			List<String> cookies = requestHeaders.get("Cookie");
			System.out.println("here");
			for(String string : cookies) {
				System.err.println(string);
			}
			
	        ServerResponse serverResponse = controller.handleCommand(exchange.getRequestURI().toString().substring(0),
	        		requestBody);
	        
	        response = JsonUtils.convertToJson(serverResponse.getBody()).getBytes("UTF-8");

	        exchange.sendResponseHeaders(serverResponse.getCode(), response.length);
	        exchange.getResponseBody().write(response);
	        exchange.close();	
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