## implement a TCP server that does not lose connection, with java thread

it is possible to implement a TCP server that does not shut down after a client sends a message. The server can keep listening for new connections and continue to communicate with multiple clients simultaneously.
Here's an example of a simple TCP server that listens for new connections and echoes back any messages received from clients without shutting down:

```java
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


```

This is the TcpServer class, it creates a ServerSocket and binds it to the specified port number. The server enters an infinite loop that listens for new connections using the accept() method. Once a connection is established, a new TcpServerThread is created and started to handle communication with the client.

```java
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
                    socket.getInputStream()));
        ) {
            String inputLine, outputLine;
            while ((inputLine = in.readLine()) != null) {
                outputLine = inputLine;
                out.println(outputLine);
            }
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

This is the TcpServerThread class, it handles communication with the client using a PrintWriter to send data and a BufferedReader to receive data. The thread enters an infinite loop that reads data from the client using the readLine() method and echoes it back using the println() method. The thread continues to run and communicate with the client until the client disconnects or sends an end-of-stream signal.

On the client side, you can use the same TcpClient code that I provided earlier, but keep in mind that the client will need to run indefinitely as well, in order to keep the connection open and continue sending and receiving data.

```java
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
                new InputStreamReader(socket.getInputStream()));
        ) {
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


```
