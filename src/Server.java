import java.io.IOException;
/**
 * Server class only invoke method ofServerThreadManager's class
 * which will initiate the server connection.
 *
 * @author Mohammed Abeer Rahman
 * @version 1.0
 * @since 2019-04-28 (9:00 pm)
 */
public class Server {
    public static void main(String[] args) throws IOException{
        ServerThreadManager STM = new ServerThreadManager();
        STM.startThreads();
    }
}
