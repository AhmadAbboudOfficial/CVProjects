
package ds;

/**
 *
 * @author chaosprince
 */

import java.rmi.*;
import java.rmi.server.UnicastRemoteObject;

public class ServerC extends UnicastRemoteObject implements ServerI{
    
    public ServerC() throws Exception{
        super();
    }
    
    @Override
    public void login(String name){
        if(name.toLowerCase().equals("admin")){
            new AdminForm().setVisible(true);
        }else{
            new WelcomeForm(name).setVisible(true);
        }
    }
    //where's these lines 
    
    @Override
    public void buyProduct(String name,int quantity){
        
    }
}