package tcpLong;

import java.io.*;
import java.net.*;

public class TcpServer {
    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.err.println("Usage: java TcpServer <port number>");
            System.exit(1);
        }
        int portNumber = Integer.parseInt(args[0]);
        boolean listening = true;
        System.out.println("listening on port" + portNumber);
        try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
            while (listening) {
                new TcpServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port " + portNumber);
            System.exit(-1);
        }
    }
}