import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xx on 2017/6/2.
 */
public class SocketServer {
    public static void main(String[] args) {

        ServerSocket server = null;
        ExecutorService executor = Executors.newCachedThreadPool();

        try {
            // new a socket server
            server = new ServerSocket(5000);

            // add listen 10 accept request
            for (int counter = 0; counter < 10; counter++) {
                // start to listen, this step will be blocked
                Socket request = server.accept();

                // when getting a request ,server will start a thread to handle the request.
                // and then keep going to listen.
                executor.execute(new HandleDataThread(request,counter));
            }

            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
