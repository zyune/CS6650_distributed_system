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
            String inputLine, outputLine = "";

            while ((inputLine = in.readLine()) != null) {
                // outputLine = new StringBuffer(inputLine).reverse().toString();
                // ;
                StringBuffer output = new StringBuffer(outputLine);
                for (int i = 0; i < inputLine.length(); i++) {
                    char c = inputLine.charAt(i);
                    if (Character.isUpperCase(c)) {
                        output.append(Character.toLowerCase(c));
                    } else if (Character.isLowerCase(c)) {
                        output.append(Character.toUpperCase(c));
                    } else {
                        output.append(c);
                    }
                }
                output.reverse();
                System.out.println(output);
                out.println(output);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
