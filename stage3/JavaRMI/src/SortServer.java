import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class SortServer implements SortInterface {

    public SortServer() {
        super();
    }

    @Override
    public List<Integer> sort(List<Integer> list) throws RemoteException {
        Collections.sort(list);
        return list;
    }

    public static void main(String[] args) {
        try {
            SortServer obj = new SortServer();
            SortInterface stub = (SortInterface) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.bind("Sort", stub);
            System.out.println("Sort Server ready.");
        } catch (Exception e) {
            System.out.println("Sort Server exception: " + e.toString());
        }
    }
}
