/**
 * The WebServer class will call the run method in this class
 *
 * @authors - Zhouling Shen & Tiffany Taghvaiee
 */
import java.net.*;
import java.io.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.concurrent.*;
import java.util.*;


public class Connection implements Runnable
{
	public static final int PORT = 10000;
	private final Socket client;
	private BufferedOutputStream toClient;
	private final HashMap<String, BufferedOutputStream> pendingClients;

	public Connection(Socket client, HashMap<String, BufferedOutputStream> pendingClients) {
		this.client = client;
		this.pendingClients = pendingClients;
	}

	public void run() {
		System.out.println("We have a connection!");
		//from client
		InputStreamReader fromReader = null;
		//from client
		BufferedReader reader = null;
		String fromString;

		try {
			//transfer input stream to string 
			fromReader =  new InputStreamReader(client.getInputStream());//passing my InputStream from client
			reader = new BufferedReader(fromReader);//passing above obtained InputStreaReader
			fromString = reader.readLine();//read lines from the reader, until right now, I have the input as a string 

			toClient = new BufferedOutputStream(client.getOutputStream());
			int spaceIndex = fromString.indexOf(" ");

			String username = fromString.substring(spaceIndex+1);
			pendingClients.put(username,toClient);

			Collection<BufferedOutputStream> connect = pendingClients.values();
			Iterator<BufferedOutputStream> itr = connect.iterator();
			while(itr.hasNext()){

				BufferedOutputStream output =itr.next();

				output.write(("JOIN "+username+"\r\n").getBytes());
				output.flush();
			}	

			String text;
			String message="";

			while ( (text = reader.readLine()) != null) {
				String[] delims = text.split("\\s+");
				if(delims[0].equals("PUB")) {
					int indexfirst=text.indexOf(" ");
					message=text.substring(indexfirst+1);
					int indexsecond=text.indexOf(" ");
					message.substring(indexsecond+1);

					Collection<BufferedOutputStream> connections = pendingClients.values();
					Iterator<BufferedOutputStream> iterator = connections.iterator();

					while(iterator.hasNext()){

						BufferedOutputStream output =iterator.next();

						output.write(("PUB "+message+"\r\n").getBytes());
						output.flush();
					}	 
				}//end if

				else if(delims[0].equals("LOGOFF")) {
					pendingClients.remove(username);
					
					Collection<BufferedOutputStream> connections = pendingClients.values();
					Iterator<BufferedOutputStream> iterator = connections.iterator();

					while(iterator.hasNext()){

						BufferedOutputStream output =iterator.next();

						output.write(("LOGOFF "+username+"\r\n").getBytes());
						output.flush();
					}
				}

			}
		}//end try

		catch (java.io.IOException ioe) {
			System.out.println(ioe);
		}

		finally { // close all streams and socket
			if (toClient != null) {
				try {
					toClient.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (fromReader != null) {

				try {
					fromReader.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (reader != null) {

				try {
					reader.close();
				} 
				catch (IOException e) {
					e.printStackTrace();
				}			
			}
		}
	}
}
