import java.io.*;
import java.util.Date;

/**
 * Created by xx on 2017/6/2.
 */
public class SocketUtil {


    public static void writeStr2Stream(String str, OutputStream out) throws IOException {

        System.out.println(SocketUtil.getNowTime() + ": prepared to write [" + str + "].");
        // add buffered writer
        // add buffered writer
        BufferedOutputStream writer = new BufferedOutputStream(out);
        try {
            writer.write(str.getBytes());
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex);
            throw ex;
        }
    }

    // read
    public static String readStrFromStream(InputStream in) {

        StringBuffer sbf = new StringBuffer(0);

        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        // read 1024 bytes per time
        char[] chars = new char[2048];
        int len;
        try {

            while ((len = reader.read(chars)) != -1) {
                // if the length of array is 1M
                if (len == 2048) {
                    //then append all chars of the array
                    sbf.append(chars);
                } else {
                    // if the length of array is less then 1M
                    for (int i = 0; i < len; i++) {
                        //then append the valid chars
                        sbf.append(chars[i]);
                    }
                    break;
                }
            }


        } catch (IOException e) {
            System.out.println(e);
            try {
                throw e;
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        //System.out.println("end reading string from stream");
        return sbf.toString();
    }

    public static String getNowTime() {
        return new Date().toString();
    }
}
