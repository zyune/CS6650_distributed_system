import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TcpClient {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: java TcpClient <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try (
                Socket socket = new Socket(hostName, portNumber);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()));) {
            Scanner scanner = new Scanner(System.in);
            String inputLine, outputLine;
            while (true) {
                System.out.print("Enter message: ");
                inputLine = scanner.nextLine();
                out.println(inputLine);
                outputLine = in.readLine();
                System.out.println("Server: " + outputLine);
            }
        } catch (UnknownHostException e) {
            System.err.println("Unknown host " + hostName);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to " + hostName);
            System.exit(1);
        }
    }
}