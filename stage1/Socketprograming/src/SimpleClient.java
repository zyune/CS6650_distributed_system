import java.io.*;
import java.net.*;

public class SimpleClient {
    public static void main(String[] args) throws IOException {
        // open your connection to a server at port 1254
        Socket s1 = new Socket("localhost", 1254);
        // get an input file handle from the socket and read the input
        InputStream s1In = s1.getInputStream();
        DataInputStream dis = new DataInputStream(s1In);
        String st = new String(dis.readUTF());
        System.out.print(st);
        // when done, jus tclose the connection and exit
        dis.close();
        s1In.close();
        s1.close();

    }
}
