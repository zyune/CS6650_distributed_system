
/* MultiThreadMathServer.java: A program extending MathServer which
allows concurrent client requests and opens a new thread for each socket
connection. */
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadMathServer
        extends MathServer implements Runnable {
    public void run() {
        execute();
    }

    public static void main(String[] args) throws Exception {
        int port = 10000;
        if (args.length == 1) {
            try {
                port = Integer.parseInt(args[0]);
            } catch (Exception e) {
            }
        }
        ServerSocket serverSocket = new ServerSocket(port);
        while (true) {
            // waiting for client connection
            Socket socket = serverSocket.accept();
            socket.setSoTimeout(14000);
            MultiThreadMathServer server = new MultiThreadMathServer();
            server.setMathService(new PlainMathService());
            server.setSocket(socket);
            // start a new server thread...
            new Thread(server).start();
        }
    }
}