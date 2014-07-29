package server.main;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.util.logging.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;

import server.*;
import shared.communication.*;


public class CatanServer {
	private static int SERVER_PORT_NUMBER = 8080;
	private static final int MAX_WAITING_CONNECTIONS = 10;
	private ServerController controller;
	private Gson gson;

	static {
		try {
			initLog();
		}
		catch (IOException e) {
			System.out.println("Could not initialize log: " + e.getMessage());
		}
	}

	private static void initLog() throws IOException {

		Level logLevel = Level.FINE;


		Handler consoleHandler = new ConsoleHandler();
		consoleHandler.setLevel(logLevel);
		consoleHandler.setFormatter(new SimpleFormatter());

		FileHandler fileHandler = new FileHandler("log.txt", false);
		fileHandler.setLevel(logLevel);
		fileHandler.setFormatter(new SimpleFormatter());
	}

	private HttpServer server;

	private CatanServer() {
		controller = new ServerController();
		gson = new Gson();
		return;
	}

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
	
	private com.sun.net.httpserver.HttpHandler handler = new com.sun.net.httpserver.HttpHandler() {	
		@Override
		public void handle(com.sun.net.httpserver.HttpExchange exchange) throws IOException {
			byte[] response = new byte[256];
			
			String requestBody = getBody(exchange);
	        	        
	        ServerResponse serverResponse = controller.handleCommand(exchange.getRequestURI().toString().substring(0),
	        		requestBody);
	        
	        response = gson.toJson(serverResponse.getBody()).getBytes("UTF-8");

	        exchange.sendResponseHeaders(serverResponse.getCode(), response.length);
	        exchange.getResponseBody().write(response);
	        exchange.close();	
		}
	};
	
	private String getBody(HttpExchange exchange) {
        @SuppressWarnings("resource")
		java.util.Scanner s = new java.util.Scanner(exchange.getRequestBody()).useDelimiter("\\A");
        String command = s.hasNext() ? s.next() : "";
        s.close();
        return command;
	}
	
	public static void main(String[] args) {
		if(args.length == 0)
			SERVER_PORT_NUMBER = 8081;
		else
			SERVER_PORT_NUMBER = Integer.parseInt(args[0]);

		new CatanServer().run();
	}

}