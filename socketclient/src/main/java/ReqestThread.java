import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by xx on 2017/6/2.
 */
public class ReqestThread implements Runnable{

    private int id;

    private boolean isLongConnection;
    // keep connection online or not. default value : false.
    public ReqestThread(int id){
        this.id = id;
    }

    public ReqestThread(int id,boolean isLongConnection){
        this.id = id;
        this.isLongConnection = isLongConnection;
    }




    public void run() {
        Socket request = null;
        try {

            // connect to socket server
            request = new Socket("127.0.0.1" , 5000);

            // heart break
            HeartBreakThread heartBreak = new HeartBreakThread(request);

            //if keep online, run heart break thread.
            if(isLongConnection) {
                // long  connection
                Executors.newCachedThreadPool().execute(heartBreak);
            }

            for (int counter = 0; counter < 3; counter++) {
                // write response info
                SocketUtil.writeStr2Stream("RequestID["+id+"] this is client,Times [" + counter +"]",request.getOutputStream());
                // get info from request when getting a socket request
                String result = SocketUtil.readStrFromStream(request.getInputStream());
                System.out.println(SocketUtil.getNowTime() + ":ReqestID["+id+"],get return msg ["+result+"].");
                if(counter<1){
                    Thread.sleep(2000);
                }else{
                    Thread.sleep(6000);
                }
            }

            //if keep online, run heart break thread.
            if(isLongConnection){
                //stop heart breaking.
                heartBreak.setKeepAlive(false);
            }

            //sleep 3s to make sure heart break thread stop rightly.
            //Thread.sleep(3000);
            if(isLongConnection){
                System.out.println("long connect Client end");
            }
            System.out.println("short connect Client end");

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLongConnection() {
        return isLongConnection;
    }

    public void setLongConnection(boolean longConnection) {
        isLongConnection = longConnection;
    }
}
