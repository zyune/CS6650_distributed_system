package quiz2;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class ShoppingCartImpl extends UnicastRemoteObject implements ShoppingCart {
    private static final long serialVersionUID = 1L;
    private String[] items = new String[] {};

    public ShoppingCartImpl() throws RemoteException {
        super();
    }

    @Override
    public void addItem(String item) throws RemoteException {
        // Add the item to the items list
        int length = items.length;
        String[] newItems = new String[length + 1];
        System.arraycopy(items, 0, newItems, 0, length);
        newItems[length] = item;
        items = newItems;
    }

    @Override
    public void removeItem(String item) throws RemoteException {
        // Remove the item from the items list
        int length = items.length;
        for (int i = 0; i < length; i++) {
            if (items[i].equals(item)) {
                String[] newItems = new String[length - 1];
                System.arraycopy(items, 0, newItems, 0, i);
                System.arraycopy(items, i + 1, newItems, i, length - i - 1);
                items = newItems;
                break;
            }
        }
    }

    @Override
    public String[] getItems() throws RemoteException {
        return items;
    }

    public static void main(String[] args) {
        try {
            // Start the RMI registry
            LocateRegistry.createRegistry(1099);

            // Register the shopping cart service
            ShoppingCartImpl shoppingCart = new ShoppingCartImpl();
            Naming.rebind("ShoppingCart", shoppingCart);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
