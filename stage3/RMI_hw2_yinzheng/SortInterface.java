import java.rmi.*;
import java.util.List;

public interface SortInterface extends Remote {
    List<Integer> sort(List<Integer> list) throws RemoteException;
}