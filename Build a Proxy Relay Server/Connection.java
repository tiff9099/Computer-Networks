import java.net.*;
import java.io.*;
/**
 * This simple connection class connects clients to Handler class.
 * @author Tiffany Taghvaiee
 */

public class Connection implements Runnable {

	private Socket client;
	private static Handler handler = new Handler();

	//constructor
	public Connection(Socket client) {
		this.client = client;
	}

	//connect client 
	public void run(){
		try {
			handler.handleRequest(client); //socket connection to handler
		}
		catch (IOException ioe) {
			System.err.println(ioe);
			System.exit(0);
		}
	}
}
