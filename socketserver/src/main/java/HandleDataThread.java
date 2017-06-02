import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * 处理请求线程，这里用于写具体的业务逻辑，本例中只做了简单的实现
 * Created by xx on 2017/6/2.
 */
public class HandleDataThread implements Runnable {

    // request from client
    private Socket request;

    // request id
    private int requestID;

    public HandleDataThread(Socket request, int requestID) {
        this.request = request;
        this.requestID = requestID;
    }

    public void run() {
        try {
            //set timeout:4 sec
            request.setSoTimeout(4000);

            while(true){
                String reqStr = "";
                try{
                    reqStr = SocketUtil.readStrFromStream(request.getInputStream());
                }catch (SocketTimeoutException ste){
                    break;
                }
                System.out.println(SocketUtil.getNowTime()
                        + " : request msg [" + reqStr + "].");

                if("Heart break".equals(reqStr)){
                    SocketUtil.writeStr2Stream("hello client ,this is server,requestID:"+requestID,request.getOutputStream());
                }else{
                    SocketUtil.writeStr2Stream("server has got your msg .",request.getOutputStream());
                }
                Thread.sleep(1000);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if(request != null){
                try {
                    request.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
