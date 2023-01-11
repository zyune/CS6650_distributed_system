
/**
 * SimpleServer
 */
import java.net.*;
import java.io.*;

public class SimpleServer {
    public static void main(String[] args) throws IOException {
        // int port = 8978;
        ServerSocket s = new ServerSocket(1254);
        Socket s1 = s.accept();// wait and accept aa connection
        // get a communication stream associated with the socket
        OutputStream s1out = s1.getOutputStream();
        DataOutputStream dos = new DataOutputStream(s1out);
        dos.writeUTF("Hi there");// send a string
        // close teh connection, but not the server socket
        dos.close();
        s1out.close();
        s1.close();

    }

}
