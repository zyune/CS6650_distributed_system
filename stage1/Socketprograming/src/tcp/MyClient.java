package tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.*;

public class MyClient {
    public static void main(String[] args) throws IOException {
        Socket s1 = new Socket("localhost", 1254);
        OutputStream s1out = s1.getOutputStream();
        DataOutputStream dos = new DataOutputStream(s1out);
        dos.writeUTF("network");
        dos.close();
        s1out.close();
        s1.close();

    }
}
