
package ds;

/**
 *
 * @author chaosprince
 */

import java.rmi.*;
public interface ServerI extends Remote {
    /**
     * login interface function to login using the username
     * usernames are to be an Admin and clients
     * @param name is username
     * @throws RemoteException
     */
    public void login(String name)throws RemoteException;
    /**
     * 
     * @param name product name
     * @param quantity how many products did the user buy
     * @throws RemoteException 
     */

    public void buyProduct(String name,int quantity)throws RemoteException;
    
}
