package tcpLong;

import java.io.*;
import java.net.*;

class TcpServerThread extends Thread {
    private Socket socket = null;

    public TcpServerThread(Socket socket) {
        super("TcpServerThread");
        this.socket = socket;
    }

    public void run() {
        try (
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(
                                socket.getInputStream()));) {
            String inputLine, outputLine;
            while ((inputLine = in.readLine()) != null) {
                outputLine = new StringBuffer(inputLine).reverse().toString().toUpperCase();
                ;
                System.out.println(outputLine);
                out.println(outputLine);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
