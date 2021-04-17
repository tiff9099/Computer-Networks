import java.net.*;
import java.io.*;

/**
 * This simple handler handles HTTP requests.
 * @author Tiffany Taghvaiee
 */
public class Handler{

	public static final int PORT = 80;
	public static final int BUFFER = 256;

	//receive a request from a client, send an HTTP request to server
	//return to client
	public void handleRequest(Socket client) throws IOException{

		BufferedReader from = null; //from client
		DataOutputStream to = null;
		InputStream input;
		OutputStream output;
		Socket sock; 
		byte[] buffer;
		int bytes;
		InetAddress host;
		String httpReq1;
		String httpReq2;
		String httpReq3;
		String httpReq4;
		String req; //client request
		String[] tempString;
		String clientString;
		String string;
		String text = " ";

		try {
			//buffer reads from socket
			from = new BufferedReader(new InputStreamReader(client.getInputStream()));
			req  = from.readLine();

			//parse requests from client
			tempString = req.split(" ", 3); //split strings into words
			clientString = tempString[1];
			tempString = clientString.split("/",3);
			string = tempString[1];
			if(tempString.length == 3)
				text = tempString[2];

			httpReq1 = "GET /" + text + " HTTP/1.1\r\n";
			httpReq2 = "Host: "+ string +"\r\n";
			httpReq3 = "Connection: close\r\n\r\n";
			httpReq4 = httpReq1 + httpReq2 + httpReq3;

			//obtain IP address. open socket
			host = InetAddress.getByName(string);

			//Open socket w/IP address + port
			sock = new Socket(host, PORT);

			//open output stream
			to = new DataOutputStream(sock.getOutputStream());

			//httpRequest to socket 
			to.writeBytes(httpReq4);

			//Open input and output streams
			input = sock.getInputStream();
			output = client.getOutputStream();

			//write to client
			buffer = new byte[BUFFER];
			while ((bytes = input.read(buffer)) != -1)
				output.write(buffer, 0, bytes);

			//close output stream to
			to.close();

		} 
		catch(UnknownHostException exception){
			System.err.println(exception);
			System.exit(0); //exit
		} 
		catch(IOException ioe){
			System.err.println(ioe);
			System.exit(0);
		}
		//close all streams
		finally {
			if (from != null)
				from.close();
			if (to != null)
				to.close();
		}
	}
}
