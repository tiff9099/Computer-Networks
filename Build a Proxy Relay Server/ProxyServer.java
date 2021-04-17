import java.net.*;
import java.io.*;
import java.util.concurrent.*; //necessary for executor
 
/**
 * This simple server will listen to port 8080 and processes each request in a seperate thread.
 * @author Tiffany Taghvaiee
 */

public class  ProxyServer
{
    public static final int DEFAULT_PORT = 8080;

    /*create thread pool (:
     * thread pool uses a fixed number of threads in a shared unbounded queue
     * threads will stay in queue until a thread becomes available
     */
    private static final Executor ex = Executors.newCachedThreadPool();

    public static void main(String[] args) throws IOException {
        ServerSocket sock = null;

        try {
            // define socket
            sock = new ServerSocket(DEFAULT_PORT);

            while (true) {
                //listen for connections
                //deal with connection in separate thread
                Runnable task = new Connection(sock.accept());
                ex.execute(task);
            }
        }
        catch (IOException ioe) {
            System.err.println(ioe);
        }
        finally {
            if (sock != null)
                sock.close();
        }
    }
}
