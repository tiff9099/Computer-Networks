/**
 * Create a server that listens for connections, and stores each socket connection in an HashMap. 
 * 
 * The server awaits client connections listening to a port specified in the protocol.
 *@authors - Zhouling Shen & Tiffany Taghvaiee

*/

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.*;

public class Server {
	
	public static int DEFAULT_PORT = 10000; 
	//a HashMap to keep the list of clients
	public static HashMap<String, BufferedOutputStream> pendingClients = new HashMap<String, BufferedOutputStream>();
	private static final Executor exec = Executors.newCachedThreadPool();
	static ServerSocket server;

	public static void main(String[] args) throws IOException
	{
		try {
			// establish the socket
			server = new ServerSocket(DEFAULT_PORT);
			
			while (true) {
				/**
				 * now listen for connections
				 * and service the connection in a separate thread.
				 */
				Runnable task = new Connection(server.accept(),pendingClients);
				exec.execute(task);
			}
		}

		catch (IOException ioe) { 
			System.err.println(ioe.getMessage()); 
		}

		finally {
			if (server != null)
				server.close();
		}
	}
}
