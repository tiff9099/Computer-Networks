import java.util.*;
import java.net.*;

public class TestMap
{
    public static void main(String[] args) {
        // create a mapping of user names to socket connections

        java.util.Map<String, Socket> users = new HashMap<String,Socket>();

        // these are just empty socket connections
        // just to demonstrate how to use the Map
        Socket s1 = new Socket();
        Socket s2 = new Socket();
        Socket s3 = new Socket();
        Socket s4 = new Socket();

        // some user names
        String u1 = "Joon";
        String u2 = "Jin";
        String u3 = "Suga";
        String u4 = "Hope";

        // map user names to their socket connections
        users.put(u1,s1);
        users.put(u2,s2);
        users.put(u3,s3);
        users.put(u4,s4);

        Collection<Socket> connections = users.values();

        // now iterate over the different socket connections
        Iterator<Socket> itr = connections.iterator();

        while (itr.hasNext()) {
            System.out.println(itr.next());
        }

        // to obtain just one connection for a given user
        Socket user = users.get("u3");
        System.out.println(user);
    }
}
