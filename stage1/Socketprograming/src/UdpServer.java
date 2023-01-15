
import java.io.*;
import java.net.*;

public class UdpServer {
    public static void main(String[] args) {
        DatagramSocket aSocket = null;
        if (args.length < 1) {
            System.out.println("Usage: java UDPserver<port number>");
            System.exit(1);
        }
        try {
            int socket_no = Integer.valueOf(args[0]).intValue();
            aSocket = new DatagramSocket(socket_no);
            byte[] buffer = new byte[1000];
            while (true) {
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                String str = new String(request.getData());
                str = new StringBuffer(str).reverse().toString().toUpperCase();
                System.out.println(str);
                DatagramPacket reply = new DatagramPacket(str.getBytes(), str.length(), request.getAddress(),
                        request.getPort());
                aSocket.send(reply);

            }
        } catch (SocketException e) {
            // TODO: handle exception
            System.out.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        } finally {
            if (aSocket != null)
                aSocket.close();
        }
    }
}
