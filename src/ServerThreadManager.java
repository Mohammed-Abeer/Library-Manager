import java.net.*;
import java.io.*;

/**
 * ServerThreadManager class will connect server and start the thread
 * to handle multiple clients.
 *
 * @author Mohammed Abeer Rahman
 * @version 1.0
 * @since 2019-04-28 (9:00 pm)
 */
public class ServerThreadManager {
    public void startThreads() throws IOException {
        ServerSocket ss = new ServerSocket(5000);
        Socket s = null;

        while(true) {
            try {
                s = ss.accept();  /*Server is connected*/

                ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
                ObjectOutputStream ous = new ObjectOutputStream(s.getOutputStream());

                ServerManager serverManager = new ServerManager(s, ois, ous);

                Thread t = new Thread(serverManager);
                t.start();  /*Now threading is started*/
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}