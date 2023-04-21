package quiz2;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ShoppingCart extends Remote {
    /**
     * Adds an item to the shopping cart
     * 
     * @param item The item to add
     * @throws RemoteException If there is an error during the remote call
     */
    public void addItem(String item) throws RemoteException;

    /**
     * Removes an item from the shopping cart
     * 
     * @param item The item to remove
     * @throws RemoteException If there is an error during the remote call
     */
    public void removeItem(String item) throws RemoteException;

    /**
     * Gets the list of items in the shopping cart
     * 
     * @return The list of items in the shopping cart
     * @throws RemoteException If there is an error during the remote call
     */
    public String[] getItems() throws RemoteException;
}
