package tcp;

import java.io.*;
import java.net.*;

public class MyServer {
    // public static String reverseAndCapString() {
    // return
    // };

    public static void main(String[] args) throws IOException {
        ServerSocket s = new ServerSocket(1254);
        Socket s1 = s.accept();// wait and accept aa connection
        InputStream s1In = s1.getInputStream();
        DataInputStream dis = new DataInputStream(s1In);
        String st = new String(dis.readUTF());
        System.out.print(st);
        dis.close();
        s1In.close();
        s1.close();
    }
}
