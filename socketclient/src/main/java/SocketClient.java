import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xx on 2017/6/2.
 */
public class SocketClient {
    public static void main(String[] args) {

        // thread executor
        ExecutorService executor = Executors.newCachedThreadPool();

        // short connection
        executor.execute(new ReqestThread(0,false));

        // long connection
        executor.execute(new ReqestThread(1,true));

    }
}
