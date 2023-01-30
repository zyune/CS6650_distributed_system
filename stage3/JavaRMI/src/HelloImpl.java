import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class HelloImpl extends UnicastRemoteObject implements Hello {
    public HelloImpl() throws RemoteException {
    }

    public String sayHello() {
        return "Hello, World!";
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            Hello hello = new HelloImpl();
            Naming.rebind("rmi://10.17.4.102:1099/Hello", hello);
            System.out.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}