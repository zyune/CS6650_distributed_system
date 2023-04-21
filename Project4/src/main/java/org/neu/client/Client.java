package org.neu.client;

import lombok.extern.slf4j.Slf4j;
import org.neu.api.PaxosAPI;
import org.neu.protocol.KVP;
import org.neu.protocol.Type;
import org.neu.server.ServerDriver;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.security.InvalidParameterException;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Client.
 */
@Slf4j
public class Client {

    // store servers gotten from the config file
    private static final Map<Integer, Map.Entry<String, Integer>> serverList = new HashMap<>();

    // store failed request
    private static final Queue<KVP> failedMessage = new LinkedBlockingQueue<>();

    // the thread to retry with failed requests
    private static final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    public static void main(String[] args) {
        // start failure monitor thread
        monitor();
        KVP packet = null;
        try {
            log.info("Client start ...");
            // environment loading
            log.info("Loading configuration file ...");
            InputStream resource = ServerDriver.class.getClassLoader().getResourceAsStream("serverConfig.yml");
            if (resource == null) {
                throw new FileNotFoundException();
            }
            Yaml yaml = new Yaml();
            Map<String, String> data = yaml.load(resource);
            resource.close();
            // set timeout
            System.setProperty("sun.rmi.transport.tcp.responseTimeout", "2000");
            // construct server list
            parseServerList(data);
            // get prepopulate data
            Map<String, String> prepopulate = prepopulate();
            List<Map.Entry<String, String>> dataList = new ArrayList<>(prepopulate.entrySet());
            // pre populating
            Random random = new Random();
            log.info("Pre-populating ... ");
            for (int i = 0; i < 5; i++) {
                int serverNo = random.nextInt(5) + 1;
                PaxosAPI api = connect(serverNo);
                packet = new KVP(Type.PUT, dataList.get(i).getKey(), dataList.get(i).getValue());
                log.info("Sent PUT request to server No. " + serverNo + ": key: " + dataList.get(i).getKey()
                        + ", value: " + dataList.get(i).getValue());
                log.info("Receive response from server No. " + serverNo + ": " + api.put(packet));
            }
            log.info("Pre-population done");
            userInterface();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Incorrect format of serverConfig.yml");
        } catch (RemoteException e) {
            log.error("Failed to process the request, the system will retry the request with other available servers");
            // add to failure queue
            failedMessage.add(packet);

        } catch (NotBoundException e) {
            System.out.println("Cannot connect to the given server, reason: " + e.getMessage());
        } catch (IOException e) {
            log.error("Error in local IO operation");
        }
    }

    /**
     * Connect to the server with given server number.
     *
     * @param serverNum server number
     * @return remote api of the server
     * @throws RemoteException   thrown when cannot establish connection
     * @throws NotBoundException the remote api stub is not found
     */
    public static PaxosAPI connect(int serverNum) throws RemoteException, NotBoundException {
        Map.Entry<String, Integer> entry = serverList.get(serverNum);
        Registry registry = LocateRegistry.getRegistry(entry.getKey(), entry.getValue());
        return (PaxosAPI) registry.lookup("PaxosAPI");
    }

    /**
     * Parse server config file to separate the hostname and port.
     *
     * @param data the server configuration
     * @throws NumberFormatException thrown if invalid format at file
     */
    public static void parseServerList(Map<String, String> data) throws NumberFormatException {
        data.forEach((key, value) -> serverList.put(Integer.parseInt(String.valueOf(key.charAt(key.length() - 1))),
                Map.entry(value.split(":")[0], Integer.parseInt(value.split(":")[1]))));
    }

    /**
     * Pre-populate a map with 5 entries with autogenerate strings.
     *
     * @return the map with pre-populated values
     */
    public static Map<String, String> prepopulate() {
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 5; i++) {
            map.put(autoGenerateString(), autoGenerateString());
        }
        return map;
    }

    /**
     * Autogenerate a string with length of 5.
     *
     * @return autogenerated string
     */
    public static String autoGenerateString() {
        String range = "abcdefghijklmnopqrstuvwxyz1234567890";
        Random random = new Random();
        char[] buffer = new char[5];
        for (int i = 0; i < buffer.length; i++) {
            buffer[i] = range.charAt(random.nextInt(range.length()));
        }
        return new String(buffer);
    }

    /**
     * user interface
     *
     * @throws IOException error in system read in
     */
    public static void userInterface() throws IOException, NotBoundException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        PaxosAPI api;
        KVP packet = null;
        // user interaction
        while (true) {
            log.info("We have " + serverList.keySet().size() + " servers are ready for you: "
                    + Arrays.toString(serverList.keySet().toArray()));
            log.info("Please specify one server to use");
            log.info("Please choose a server or quit to exit");
            String serverId = reader.readLine();
            if (serverId.equalsIgnoreCase("quit")) {
                break;
            }
            int id;
            try {
                id = Integer.parseInt(serverId);
                if (id < 1 || id > 5) {
                    throw new InvalidParameterException();
                }
                String prefix = "Receive response from server No. " + id;
                api = connect(id);
                log.info("Please input: get, put, delete to use service");
                String input = reader.readLine();
                // remove potential space
                input = input.replaceAll("\\s", "");
                // check input and handle
                if ("get".equalsIgnoreCase(input)) {
                    log.info("Please input a key: ");
                    String key = reader.readLine();
                    log.info("Sent Get request: key = " + key + " to server No. " + id);
                    log.info(prefix + ": " + api.get(new KVP(Type.GET, key, null)));
                } else if ("put".equalsIgnoreCase(input)) {
                    log.info("Please input a key: ");
                    String key = reader.readLine();
                    log.info("Please input a value: ");
                    String value = reader.readLine();
                    log.info("Sent Put request: key = " + key + " value = " + value + " to server No. " + id);
                    packet = new KVP(Type.PUT, key, value);
                    log.info(prefix + ": " + api.put(packet));
                } else if ("delete".equalsIgnoreCase(input)) {
                    log.info("Please input a key: ");
                    String key = reader.readLine();
                    log.info("Sent Delete request: key = " + key + " to server No. " + id);
                    packet = new KVP(Type.DELETE, key, null);
                    log.info(prefix + ": " + api.delete(packet));
                } else {
                    throw new InvalidParameterException();
                }
            } catch (InvalidParameterException | NumberFormatException e) {
                log.error("Invalid input, please check your input and try again");
            } catch (RemoteException e) {
                log.error(
                        "Failed to process the request, the system will retry the request with another available server");
                // add to failure queue
                failedMessage.add(packet);
            }
        }
        // on close
        reader.close();
        log.info("Client exited ...");
        System.exit(0);
    }

    /**
     * Monitor the failure queue and resend failed requests to other servers.
     */
    public static void monitor() {
        Random random = new Random();
        executorService.scheduleAtFixedRate(() -> {
            // if a failed request present
            while (!failedMessage.isEmpty()) {
                KVP packet = failedMessage.poll();
                // choose another server to retry
                int i = random.nextInt(serverList.size()) + 1;
                try {
                    PaxosAPI api = connect(i);
                    log.info("Retry to send the request: " + packet + " to server No. " + i);
                    if (Type.PUT.equals(packet.getType())) {
                        log.info("Receive response from server No. " + i + ": " + api.put(packet));
                    } else if (Type.DELETE.equals(packet.getType())) {
                        log.info("Receive response from server No. " + i + ": " + api.delete(packet));
                    }
                } catch (RemoteException | NotBoundException e) {
                    log.error("Server No. " + i + " is unreachable, retrying with other servers");
                    // add the request back to the queue if failed again
                    failedMessage.add(packet);
                }
            }
        }, 0, 1000, TimeUnit.MILLISECONDS);
    }

}
