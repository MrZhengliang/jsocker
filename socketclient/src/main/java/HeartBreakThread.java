import java.io.IOException;
import java.net.Socket;

/**
 * Created by xx on 2017/6/2.
 */
public class HeartBreakThread implements Runnable{

    private Socket client;
    private boolean keepAlive = true;

    public HeartBreakThread(Socket request){
        this.client = request;
    }



    public void run() {
        while(keepAlive){
            try {
                SocketUtil.writeStr2Stream("Heart break",client.getOutputStream());
                // the sleeping time is less than server's settimeout time.
                Thread.sleep(3000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("HeartBreaking end.");
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public boolean isKeepAlive() {
        return keepAlive;
    }

    public void setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
    }
}
