import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SortClient {

    private SortClient() {
    }

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the number of integers to be sorted:");
            int n = sc.nextInt();
            System.out.println("Enter " + n + " integers separated by space:");
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                list.add(sc.nextInt());
            }
            Registry registry = LocateRegistry.getRegistry(1099);
            SortInterface sort = (SortInterface) registry.lookup("Sort");
            System.out.println("Unsorted list: " + list);
            List<Integer> sortedList = sort.sort(list);
            System.out.println("Sorted list: " + sortedList);
        } catch (RemoteException | NotBoundException e) {
            System.out.println("Sort Client exception: " + e.toString());
        }
    }
}
