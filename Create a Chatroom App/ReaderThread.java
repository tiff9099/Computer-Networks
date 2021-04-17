/**
 * This thread is passed a socket that it reads from. Whenever it gets input
 * it writes it to the ChatScreen text area using the displayMessage() method.
 * @authors - Zhouling Shen & Tiffany Taghvaiee
 */

import java.io.*;
import java.net.*;
import javax.swing.*;

public class ReaderThread implements Runnable
{
	Socket server;
	BufferedReader fromServer;
	ChatScreen screen;

	public ReaderThread(Socket server, ChatScreen screen) {
		this.server = server;
		this.screen = screen;
	}

	public void run() {
		try {
			fromServer = new BufferedReader(new InputStreamReader(server.getInputStream()));

			while (true) {
				String message = fromServer.readLine();
				String text="";
				String[] delims = message.split("\\s+");
				int indexfirst=message.indexOf(" ");
				message=message.substring(indexfirst);

				if(delims[0].equals("PUB")) {
					text=message;
				}
				else if(delims[0].equals("LOGOFF")){
					text=message+" has exited the room";	
				}
				else if(delims[0].equals("JOIN")) {
					text=message+" has joined the room";
				}

				// now display it on the display area
				screen.displayMessage(text); //in the chatscreen
			}
		}
		catch (IOException ioe) { 
			System.out.println(ioe); 
		}

	}
}
